package com.rest.books.bootrestbooks.Services.Impl;

import com.rest.books.bootrestbooks.Entities.Category;
import com.rest.books.bootrestbooks.Exceptions.ResourceNotFoundException;
import com.rest.books.bootrestbooks.Payloads.CategoryDto;
import com.rest.books.bootrestbooks.Repositories.CategoryRepo;
import com.rest.books.bootrestbooks.Services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category cat = this.modelMapper.map(categoryDto, Category.class);
        Category addedCat = categoryRepo.save(cat);
        return this.modelMapper.map(addedCat, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category cat =this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));

        cat.setCategoryName(categoryDto.getCategoryName());


        Category updatedCat = this.categoryRepo.save(cat);
        return this.modelMapper.map(updatedCat, CategoryDto.class);

    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> allCategories = this.categoryRepo.findAll();
        List<CategoryDto> categoriesDto= allCategories.stream().map((cat)->this.modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
        return categoriesDto;
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category id", categoryId));
        return this.modelMapper.map(cat, CategoryDto.class);

    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category Id",categoryId));
        this.categoryRepo.delete(cat);
    }
}
