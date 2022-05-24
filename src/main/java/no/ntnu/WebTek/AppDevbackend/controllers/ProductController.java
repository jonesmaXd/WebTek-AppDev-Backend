package no.ntnu.WebTek.AppDevbackend.controllers;

import no.ntnu.WebTek.AppDevbackend.model.Product;
import no.ntnu.WebTek.AppDevbackend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 */
@RestController
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     *
     */
    @GetMapping("/api/products")
    public List<Product> getAll() {
        return productService.getAll();
    }
}
