package br.edu.unichristus.service;

import br.edu.unichristus.domain.dto.category.CategoryDTO;
import br.edu.unichristus.domain.model.Category;
import br.edu.unichristus.exception.CommonsException;
import br.edu.unichristus.repository.CategoryRepository;
import br.edu.unichristus.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public CategoryDTO save(CategoryDTO categoryDTO){
        try {
            var categoryEntity = MapperUtil.parseObject(categoryDTO, Category.class);
            var savedCategory = repository.save(categoryEntity);
            return MapperUtil.parseObject(savedCategory, CategoryDTO.class);

        } catch (DataIntegrityViolationException ex) { // trata exceção que viola as regras do model da entidade
            throw new CommonsException(
                    HttpStatus.BAD_REQUEST,
                    "unichristus.category.save.dataintegrity",
                    "Erro ao tentar salvar categoria no banco. Alguma regra do banco foi violada durante o processo."
            );

        } catch (Exception ex) { // trata exceção em geral
            throw new CommonsException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "unichristus.category.save.unexpected",
                    "Erro inesperado ao salvar categoria."
            );
        }
    }

    public List<CategoryDTO> findAll(){
        try {
            var listCategories = repository.findAll();
            return MapperUtil.parseListObjects(listCategories, CategoryDTO.class);

        } catch (Exception ex) {
            throw new CommonsException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "unichristus.category.findall.unexpected",
                    "Erro inesperado ao encontrar categorias."
            );
        }

    }

    public Category findById(Long id){
        var categoryEntity = repository.findById(id);

        try {
            if(categoryEntity.isEmpty()){// trata exceção de não encontrar o id procurado
                throw new CommonsException(HttpStatus.NOT_FOUND,
                        "unichristus.category.findbyid.notfound",
                        "Categoria não encontrada!");
            }
            return repository.findById(id).get();

        } catch (Exception ex) { // trata exceção generica
            throw new CommonsException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "unichristus.category.findbyid.unexpected",
                    "Erro inesperado ao encontrar id da categoria."
            );
        }
    }

    public void delete(Long id){
        var categoryEntity = repository.findById(id);
        try {
            if (categoryEntity.isEmpty()) { // trata exceção de não encontrar o id a ser deletado
                throw new CommonsException(HttpStatus.NOT_FOUND,
                        "unichristus.category.delete.notfound",
                        "Categoria não encontrada!");
            }
            repository.deleteById(id);

        } catch (Exception ex) {
            throw new CommonsException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "unichristus.category.delete.unexpected",
                    "Erro inesperado ao deletar categoria."
            );
        }

    }
}
