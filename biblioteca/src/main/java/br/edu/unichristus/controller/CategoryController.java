package br.edu.unichristus.controller;

import br.edu.unichristus.domain.dto.category.CategoryDTO;
import br.edu.unichristus.domain.model.Category;
import br.edu.unichristus.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService service;

    @PostMapping
    public CategoryDTO save(@RequestBody CategoryDTO category){
        return service.save(category);
    }

    @PutMapping
    public CategoryDTO update(@RequestBody CategoryDTO category){
        return service.save(category);
    }

    @GetMapping("/all")
    public List<CategoryDTO> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Category findById(@PathVariable Long id){
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
