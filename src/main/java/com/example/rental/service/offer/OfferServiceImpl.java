package com.example.rental.service.offer;

import com.example.rental.dto.rental.OfferDto;
import com.example.rental.exception.OfferException;
import com.example.rental.model.Motor;
import com.example.rental.model.Offer;
import com.example.rental.model.User;
import com.example.rental.repository.motor.MotorRepository;
import com.example.rental.repository.offer.OfferRepository;
import com.example.rental.repository.offer.OfferRepositoryUtil;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class OfferServiceImpl implements OfferService {
    @Autowired
    OfferRepository offerRepository;
    @Autowired
    OfferRepositoryUtil offerRepositoryUtil;
    @Autowired
    MotorRepository motorRepository;

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
        List<Offer> list = offerRepositoryUtil.findByVehicleAndDateBetween(motor.get(), startDate, endDate);
        if (!list.isEmpty()) {
            throw new OfferException("Vehicle is busy this time.");
        }
        Offer offer = new Offer(motor.get().getId(),startDate, endDate);
        offer.setUserId(user.getId());
        offer.setSessionId(sessionId);
        offer.setPrice(offerDto.getPrice());
        return offerRepository.save(offer);
    }

    @Override
    public List<Offer> getOffer(String motorId) {
        Optional<Motor> motor = motorRepository.findById(motorId);
        return motor.map(value -> offerRepository.findAllByVehicleId(value.getId())).orElse(Collections.emptyList());
    }

    @Override
    public List<String> test(long start, long end) {
        return offerRepositoryUtil.findIdsByDateBetween(new Date(start), new Date(end));
    }
}
