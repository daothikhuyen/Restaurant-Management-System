package RMS.com.example.RMS.menu.web.controller;

import RMS.com.example.RMS.common.ApiResponse;
import RMS.com.example.RMS.menu.domain.service.ProductService;
import RMS.com.example.RMS.menu.web.request.ProductRequest;
import RMS.com.example.RMS.menu.web.response.ProductResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin
@Slf4j
public class ProductController {

    @Autowired
    ProductService service;

    @PostMapping("create")
    public ApiResponse<String> createProduct(@RequestBody ProductRequest request) throws Exception {

        return ApiResponse.<String>builder()
                .result(service.createProduct(request))
                .build();
    }

    @GetMapping("/category/{categoryId}")
    public ApiResponse<List<ProductResponse>> getAll(@PathVariable Long categoryId) throws Exception {

        return ApiResponse.<List<ProductResponse>>builder()
                .result(service.getByCategoryId(categoryId))
                .build();
    }

    @GetMapping("{productId}")
    public ApiResponse<ProductResponse> createProduct(@PathVariable Long productId) throws Exception {

        return ApiResponse.<ProductResponse>builder()
                .result(service.getProductById(productId))
                .build();
    }
}
