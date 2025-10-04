package RMS.com.example.RMS.menu.web.controller;

import RMS.com.example.RMS.common.ApiResponse;
import RMS.com.example.RMS.menu.domain.service.CategoryService;
import RMS.com.example.RMS.menu.web.request.CategoryRequest;
import RMS.com.example.RMS.menu.web.response.CategoryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@CrossOrigin
@Slf4j
public class CategoryController {

    @Autowired
    CategoryService service;

    @GetMapping("/getAll")
    public ApiResponse<List<CategoryResponse>> getAll() throws Exception {

        return ApiResponse.<List<CategoryResponse>>builder()
                .result(service.getAll())
                .build();
    }
    @PostMapping("/create")
    public ApiResponse<String> createCategory(@RequestBody CategoryRequest request) throws Exception {

        return ApiResponse.<String>builder()
                .result(service.createCategory(request))
                .build();
    }

    @PostMapping("/update/{categoryId}")
    public ApiResponse<CategoryResponse> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryRequest request) throws Exception {

        return ApiResponse.<CategoryResponse>builder()
                .result(service.updateCategory(categoryId, request))
                .build();
    }

    @PostMapping("/delete/{categoryId}")
    public ApiResponse<String> deleteCategory(@PathVariable Long categoryId) throws Exception {

        return ApiResponse.<String>builder()
                .result(service.deleteCategory(categoryId))
                .build();
    }
}
