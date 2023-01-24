package com.rest.books.bootrestbooks.controllers;


import com.rest.books.bootrestbooks.Exceptions.ApiResponse;
import com.rest.books.bootrestbooks.Payloads.CategoryDto;
import com.rest.books.bootrestbooks.Services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCat(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto createdCategory = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{catId}")
    public ResponseEntity<CategoryDto> updateCat(@Valid @PathVariable Integer catId, @RequestBody CategoryDto categoryDto) {
        CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto, catId);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @DeleteMapping("/{catId}")
    public ResponseEntity<ApiResponse> deleteCat(@PathVariable Integer catId) {
        this.categoryService.deleteCategory(catId);
        return new ResponseEntity<>(new ApiResponse("Category deleted Successfully!", true), HttpStatus.OK);
    }

    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDto> getCat(@PathVariable Integer catId) {
        CategoryDto gotCategory = this.categoryService.getCategory(catId);
        return new ResponseEntity<>(gotCategory, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCat() {
        List<CategoryDto> gotAllCategories = this.categoryService.getAllCategories();
        return ResponseEntity.ok(gotAllCategories);
    }
}
