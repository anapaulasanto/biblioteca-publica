package br.edu.unichristus.service;

import br.edu.unichristus.domain.dto.category.CategoryDTO;
import br.edu.unichristus.domain.model.Category;
import br.edu.unichristus.exception.CommonsException;
import br.edu.unichristus.repository.CategoryRepository;
import br.edu.unichristus.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public CategoryDTO save(CategoryDTO categoryDTO){
        var categoryEntity = MapperUtil.parseObject(categoryDTO, Category.class);
        var savedCategory = repository.save(categoryEntity);
        return MapperUtil.parseObject(savedCategory, CategoryDTO.class);
    }

    public List<CategoryDTO> findAll(){
        var listCategories = repository.findAll();
        return MapperUtil.parseListObjects(listCategories, CategoryDTO.class);
    }

    public Category findById(Long id){
        var categoryEntity = repository.findById(id);
        if(categoryEntity.isEmpty()){
            throw new CommonsException(HttpStatus.NOT_FOUND,
                    "unichristus.category.findbyid.notfound",
                    "Categoria não encontrada!");
        }

        return repository.findById(id).get();
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

}
