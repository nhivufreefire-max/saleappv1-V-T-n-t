package com.example.saleappv1.service;

import com.example.saleappv1.model.Category;
import com.example.saleappv1.model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private List<Product> products = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();

    public ProductService() {
        loadData();
    }

    private void loadData() {
        try {
            InputStream productsStream = new ClassPathResource("data/products.json").getInputStream();
            InputStream categoriesStream = new ClassPathResource("data/categories.json").getInputStream();

            products = objectMapper.readValue(productsStream, new TypeReference<List<Product>>() {});
            categories = objectMapper.readValue(categoriesStream, new TypeReference<List<Category>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Không thể đọc dữ liệu JSON", e);
        }
    }

    public List<Product> getAll() {
        return new ArrayList<>(products);
    }

    public Product findById(Integer id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Category> getCategories() {
        return new ArrayList<>(categories);
    }

    public String getCategoryName(Integer categoryId) {
        return categories.stream()
                .filter(c -> c.getId().equals(categoryId))
                .map(Category::getName)
                .findFirst()
                .orElse("Không xác định");
    }

    public List<Product> filter(Integer categoryId, String keyword, Double fromPrice, Double toPrice) {
        return products.stream()
                .filter(p -> categoryId == null || p.getCategoryId().equals(categoryId))
                .filter(p -> keyword == null || keyword.isBlank()
                        || p.getName().toLowerCase().contains(keyword.toLowerCase()))
                .filter(p -> fromPrice == null || p.getPrice() >= fromPrice)
                .filter(p -> toPrice == null || p.getPrice() <= toPrice)
                .toList();
    }
}
