package no.ntnu.WebTek.AppDevbackend.services;

import no.ntnu.WebTek.AppDevbackend.model.Product;
import no.ntnu.WebTek.AppDevbackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Business logic for Products
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Gets all the products from the database
     *
     * @return all the products in the database
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

}
