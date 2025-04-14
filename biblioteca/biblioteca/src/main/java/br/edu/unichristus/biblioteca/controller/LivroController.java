package br.edu.unichristus.biblioteca.controller;


import br.edu.unichristus.biblioteca.domain.dto.LivroLowDTO;
import br.edu.unichristus.biblioteca.service.LivroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("biblioteca-publica/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping("/buscar")
    public List<LivroLowDTO> buscarLivroPorTitulo(@RequestParam String titulo) {
        return livroService.buscarLivros(titulo);
    }
}
