package no.ntnu.WebTek.AppDevbackend.controllers;

import no.ntnu.WebTek.AppDevbackend.model.Product;
import no.ntnu.WebTek.AppDevbackend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST API controller serving endpoints for products
 */
@RestController
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Gets all products in the database
     *
     * @return List of all products
     */
    @GetMapping("/api/product/getAll")
    public List<Product> getAll() {
        return productService.getAllProducts();
    }
}
