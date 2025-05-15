package br.edu.unichristus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.edu.unichristus.domain.dto.category.CategoryDTO;
import br.edu.unichristus.domain.model.Category;
import br.edu.unichristus.exception.CommonsException;
import br.edu.unichristus.repository.CategoryRepository;
import br.edu.unichristus.utils.MapperUtil;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public CategoryDTO save(CategoryDTO categoryDTO) {
        if (repository.findByCategoryCode(categoryDTO.getCategoryCode()).isPresent()) {
            throw new CommonsException(HttpStatus.CONFLICT,
                    "unichristus.category.categorycode.conflict",
                    "Categoria já existente para o código informado.");
        }

        if (categoryDTO.getCategoryName() == null) {
            throw new CommonsException(HttpStatus.BAD_REQUEST,
                    "unichristus.category.categorycode.badrequest",
                    "Nome da categoria é um campo obrigatório.");
        }

        var categoryEntity = MapperUtil.parseObject(categoryDTO, Category.class);
        var savedCategory = repository.save(categoryEntity);
        return MapperUtil.parseObject(savedCategory, CategoryDTO.class);
    }

    public CategoryDTO update(Long id, CategoryDTO categoryDTO) {
        var existingCategory = repository.findById(id)
                .orElseThrow(() -> new CommonsException(HttpStatus.NOT_FOUND,
                        "unichristus.category.update.notfound",
                        "Categoria para atualização não encontrada."));

        var existingCategoryWithSameCode = repository.findByCategoryCode(categoryDTO.getCategoryCode());
        if (existingCategoryWithSameCode.isPresent()
                && !existingCategoryWithSameCode.get().getId().equals(id)) {
            throw new CommonsException(HttpStatus.CONFLICT,
                    "unichristus.category.update.conflict",
                    "Já existe outra categoria com o mesmo código.");
        }

        if (categoryDTO.getCategoryName() == null) {
            throw new CommonsException(HttpStatus.BAD_REQUEST,
                    "unichristus.category.categoryname.badrequest",
                    "Nome da categoria é um campo obrigatório.");
        }

        existingCategory.setCategoryName(categoryDTO.getCategoryName());
        existingCategory.setDescription(categoryDTO.getDescription());
        existingCategory.setCategoryCode(categoryDTO.getCategoryCode());
        existingCategory.setCategoryPopularity(categoryDTO.getCategoryPopularity());

        var updated = repository.save(existingCategory);
        return MapperUtil.parseObject(updated, CategoryDTO.class);
    }

    public List<CategoryDTO> findAll() {
        var listCategories = repository.findAll();
        return MapperUtil.parseListObjects(listCategories, CategoryDTO.class);
    }

    public Category findById(Long id) {
        var categoryEntity = repository.findById(id);

        if (categoryEntity.isEmpty()) {
            throw new CommonsException(HttpStatus.NOT_FOUND,
                    "unichristus.category.findbyid.notfound",
                    "Categoria não encontrada!");
        }
        return repository.findById(id).get();
    }

    public void delete(Long id) {
        var categoryEntity = repository.findById(id);

        if (categoryEntity.isEmpty()) {
            throw new CommonsException(HttpStatus.NOT_FOUND,
                    "unichristus.category.delete.notfound",
                    "Categoria não encontrada!");
        }
        repository.deleteById(id);
    }
}
