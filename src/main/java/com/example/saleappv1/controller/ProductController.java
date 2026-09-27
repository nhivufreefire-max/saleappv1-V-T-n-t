package com.example.saleappv1.controller;

import com.example.saleappv1.model.Product;
import com.example.saleappv1.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String products(
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Double fromPrice,
            @RequestParam(required = false) Double toPrice,
            Model model) {

        model.addAttribute("products",
                productService.filter(categoryId, keyword, fromPrice, toPrice));
        model.addAttribute("categories", productService.getCategories());

        model.addAttribute("categoryId", categoryId);
        model.addAttribute("keyword", keyword);
        model.addAttribute("fromPrice", fromPrice);
        model.addAttribute("toPrice", toPrice);

        return "products";
    }

    @GetMapping("/products/{productId}")
    public String productDetail(@PathVariable Integer productId, Model model) {
        Product product = productService.findById(productId);

        if (product == null) {
            return "redirect:/products";
        }

        model.addAttribute("product", product);
        model.addAttribute("categoryName",
                productService.getCategoryName(product.getCategoryId()));

        return "product-detail";
    }
}
