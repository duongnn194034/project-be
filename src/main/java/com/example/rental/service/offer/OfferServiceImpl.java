package com.example.rental.service.offer;

import com.example.rental.common.PriceComparator;
import com.example.rental.dto.offer.OfferDto;
import com.example.rental.dto.offer.OfferResponseDto;
import com.example.rental.dto.user.MiniUserDto;
import com.example.rental.dto.user.UserPriceDto;
import com.example.rental.enums.Status;
import com.example.rental.exception.OfferException;
import com.example.rental.model.motor.Motor;
import com.example.rental.model.offer.Offer;
import com.example.rental.model.user.User;
import com.example.rental.model.motor.Vehicle;
import com.example.rental.repository.motor.MotorRepository;
import com.example.rental.repository.offer.OfferRepository;
import com.example.rental.repository.offer.OfferRepositoryUtil;
import com.example.rental.repository.user.UserRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class OfferServiceImpl implements OfferService {
    @Autowired
    OfferRepository offerRepository;
    @Autowired
    OfferRepositoryUtil offerRepositoryUtil;
    @Autowired
    MotorRepository motorRepository;
    @Autowired
    UserRepository userRepository;

    @Value("${BASE_URL}")
    private String baseURL;

    @Value("${STRIPE_SECRET_KEY}")
    private String apiKey;

    SessionCreateParams.LineItem.PriceData createPriceData(OfferDto offerDto) {
        return SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("VND")
                .setUnitAmount((long) offerDto.getPrice())
                .setProductData(
                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                .setName(offerDto.getUserId())
                                .build())
                .build();
    }

    // build each product in the stripe checkout page
    SessionCreateParams.LineItem createSessionLineItem(OfferDto offerDto) {
        return SessionCreateParams.LineItem.builder()
                // set price for each product
                .setPriceData(createPriceData(offerDto))
                .setQuantity(1L)
                .build();
    }

    public Session createSession(OfferDto offerDto) throws StripeException {

        // supply success and failure url for stripe
        String successURL = baseURL + "payment/success";
        String failedURL = baseURL + "payment/failed";

        // set the private key
        Stripe.apiKey = apiKey;

        SessionCreateParams.LineItem sessionItems = createSessionLineItem(offerDto);

        // build the session param
        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setCancelUrl(failedURL)
                .addLineItem(sessionItems)
                .setSuccessUrl(successURL)
                .setCurrency("VND")
                .build();
        return Session.create(params);
    }

    @Override
    public Offer save(User user, OfferDto offerDto, String sessionId) {
        Optional<Motor> motor = motorRepository.findById(offerDto.getVehicleId());
        if (motor.isEmpty()) {
            throw new OfferException("Motor id is not found.");
        }
        long span = offerDto.getEnd() - offerDto.getStart();
        if (span < motor.get().toMinDur() || (motor.get().toMaxDur() > 0 && span > motor.get().toMaxDur())) {
            throw new OfferException("Duration length is not valid.");
        }
        Date startDate = new Date(offerDto.getStart());
        Date endDate = new Date(offerDto.getEnd());
        List<Offer> list = offerRepositoryUtil.findByVehicleAndDateBetween(motor.get().getId(), startDate, endDate);
        if (!list.isEmpty()) {
            throw new OfferException("Vehicle is busy this time.");
        }
        Offer offer = new Offer(motor.get().getId(),startDate, endDate);
        offer.setUserId(user.getId());
        offer.setUserName(user.getFullName());
        offer.setSessionId(sessionId);
        offer.setPrice(offerDto.getPrice());
        offer.setStatus(Status.BOOKING);
        return offerRepository.save(offer);
    }

    @Override
    public List<OfferResponseDto> getOffer(String motorId) {
        List<Offer> offerList = offerRepository.findAllByVehicleId(motorId);
        List<OfferResponseDto> offerLists = new ArrayList<>();
        for (Offer offer : offerList) {
            Optional<User> owner = userRepository.findById(offer.getUserId());
            if (owner.isEmpty()) {
                continue;
            }
            OfferResponseDto offerResponseDto = new OfferResponseDto(offer.getId(), offer.getStartTime(),
                    offer.getEndTime(), offer.getStatus(), offer.getPrice(), offer.getCreatedDate());
            MiniUserDto userDto = new MiniUserDto(owner.get().getFullName(), owner.get().getEmail(),
                    owner.get().getPhoneNumber(), owner.get().getAvatarUrl());
            offerResponseDto.setUser(userDto);
            offerLists.add(offerResponseDto);
        }
        return offerLists;
    }

    @Override
    public OfferResponseDto changeOfferStatus(String offerId, String userId, Status status) {
        Optional<Offer> offer = offerRepository.findById(offerId);
        if (offer.isEmpty()) {
            throw new OfferException("OfferId is not exist");
        }
        Optional<Motor> motor = motorRepository.findById(offer.get().getVehicleId());
        if (motor.isEmpty()) {
            throw new RuntimeException("Motor does not exist.");
        }  else if (!offer.get().getUserId().equals(userId) && !userId.equals(motor.get().getOwnerId())) {
            throw new OfferException("Unauthorized User");
        }
        offer.get().setStatus(status);
        offerRepository.save(offer.get());
        return new OfferResponseDto(offer.get().getId(), motor.get(), offer.get().getStartTime(), offer.get().getEndTime(),
                offer.get().getStatus(), offer.get().getPrice(), offer.get().getCreatedDate());
    }

    @Override
    public OfferResponseDto getOfferById(String id, String userId) {
        Optional<Offer> offer = offerRepository.findById(id);
        if (offer.isEmpty()) {
            throw new OfferException("Offer does not exist");
        } else if (!offer.get().getUserId().equals(userId)) {
            throw new OfferException("Forbidden");
        }
        Optional<Motor> motor = motorRepository.findById(offer.get().getVehicleId());
        if (motor.isEmpty()) {
            throw new RuntimeException("Motor does not exist.");
        }
        return new OfferResponseDto(offer.get().getId(), motor.get(), offer.get().getStartTime(), offer.get().getEndTime(),
                offer.get().getStatus(), offer.get().getPrice(), offer.get().getCreatedDate());
    }

    @Override
    public List<OfferResponseDto> getOfferByUserId(String userId) {
        List<Offer> offerList = offerRepository.findAllByUserId(userId);
        List<OfferResponseDto> offerLists = new ArrayList<>();
        for (Offer offer : offerList) {
            Optional<Motor> motor = motorRepository.findById(offer.getVehicleId());
            if (motor.isEmpty()) {
                continue;
            }
            OfferResponseDto offerResponseDto = new OfferResponseDto(offer.getId(), motor.get(), offer.getStartTime(),
                    offer.getEndTime(), offer.getStatus(), offer.getPrice(), offer.getCreatedDate());
            offerLists.add(offerResponseDto);
        }
        return offerLists;
    }

    @Override
    public List<OfferResponseDto> getOfferVehicleByUserId(String userId) {
        List<Motor> motors = motorRepository.findByOwnerId(userId);
        List<String> motorIds = motors.stream().map(Vehicle::getId).collect(Collectors.toList());
        List<Offer> offers = offerRepositoryUtil.findByVehiclesAndDateBetween(motorIds);
        List<OfferResponseDto> offerResponseDtos = new ArrayList<>();
        for (int i = 0; i < offers.size(); i++) {
            OfferResponseDto offerResponseDto = new OfferResponseDto(offers.get(i).getId(), motors.get(i),
                    offers.get(i).getStartTime(), offers.get(i).getEndTime(), offers.get(i).getStatus(),
                    offers.get(i).getPrice(), offers.get(i).getCreatedDate());
            offerResponseDto.setUserName(offers.get(i).getUserName());
            offerResponseDtos.add(offerResponseDto);
        }
        return offerResponseDtos;
    }

    @Override
    public List<UserPriceDto> statistic() {
        List<User> users = userRepository.findAll();
        List<UserPriceDto> list = new ArrayList<>();
        for (User user : users) {
            List<OfferResponseDto> dtos = getOfferVehicleByUserId(user.getId());
            int num = dtos.size();
            double income = dtos.stream().mapToDouble(OfferResponseDto::calcIncome).sum();
            UserPriceDto userPriceDto = new UserPriceDto();
            userPriceDto.setUserId(user.getId());
            userPriceDto.setFullName(user.getFullName());
            userPriceDto.setEmail(user.getEmail());
            userPriceDto.setPhoneNumber(user.getPhoneNumber());
            userPriceDto.setIncome(income);
            userPriceDto.setOfferNum(num);
            if (user.getBank() != null) {
                userPriceDto.setBank(user.getBank().getBankName());
                userPriceDto.setBankNumber(user.getBank().getAccountNumber());
            }
            list.add(userPriceDto);
        }
        list.sort(new PriceComparator());
        return list;
    }
}
