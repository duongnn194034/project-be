package com.example.ecommerce.Service;

import com.example.ecommerce.Model.Brand;
import com.example.ecommerce.Repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BrandService {

    @Autowired
    BrandRepository brandRepository;

    public Optional<Brand> readBrand(long id) {
        return brandRepository.getBrandById(id);
    }

    public Optional<Brand> readBrand(String brandName) {
        return brandRepository.getBrandByBrandName(brandName);
    }

    public List<Brand> listBrand() {
        return brandRepository.findAll();
    }

    public void createBrand(Brand brand) {
        brandRepository.save(brand);
    }

    public void updateBrand(long brandID, Brand brand) {
        brandRepository.updateById(brandID, brand);
    }
}
