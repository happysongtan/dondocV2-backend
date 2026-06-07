package com.dondoc.controller;

import com.dondoc.dto.ApiResponse;
import com.dondoc.dto.CategoryResponse;
import com.dondoc.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;
    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> getCategories(
            @RequestHeader(value = "userId", required = false) Long userId
    ) {
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>(false, null, "인증 토큰 없음"));
        }

        List<CategoryResponse> categories = categoryService.getCategories();
        return ResponseEntity.ok(new ApiResponse<>(true, categories, "카테고리 조회 성공"));
    }
}
