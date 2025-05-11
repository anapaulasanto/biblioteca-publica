package br.edu.unichristus.controller;

import br.edu.unichristus.domain.dto.book.BookDTO;
import br.edu.unichristus.domain.dto.category.CategoryDTO;
import br.edu.unichristus.domain.model.Category;
import br.edu.unichristus.service.BookService.BookService;
import br.edu.unichristus.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService service;

    @Operation(summary = "Cadastra uma nova categoria | role: [ADMIN]", tags = "Category")
    @PostMapping
    public CategoryDTO save(@RequestBody CategoryDTO category){
        return service.save(category);
    }

    @Operation(summary = "Atualiza uma categoria existente | role: [ADMIN]", tags = "Category")
    @PutMapping
    public CategoryDTO update(@RequestBody CategoryDTO category){
        return service.save(category);
    }

    @Operation(summary = "Retorna todas as categorias | role: [ADMIN, USER]", tags = "Category")
    @GetMapping("/all")
    public List<CategoryDTO> findAll(){
        return service.findAll();
    }

    @Operation(summary = "Retorna uma categoria por ID | role: [ADMIN, USER]", tags = "Category")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Categoria encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/{id}")
    public Category findById(@PathVariable Long id){
        return service.findById(id);
    }

    @Operation(summary = "Exclui uma categoria por ID | role: [ADMIN]", tags = "Category")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Categoria excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @Autowired
    private BookService bookService;

    @Operation(summary = "Retorna livros de uma categoria | role: [ADMIN, USER]", tags = "Category")
    @GetMapping("/{id}/books")
    public List<BookDTO> getBooksByCategory(@PathVariable("id") Long categoryId) {
        return bookService.findBooksByCategoryId(categoryId);
    }
}
