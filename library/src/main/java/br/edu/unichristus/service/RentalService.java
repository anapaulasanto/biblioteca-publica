package br.edu.unichristus.service;

import br.edu.unichristus.domain.dto.rental.RentalDTO;
import br.edu.unichristus.domain.dto.review.ReviewDTO;
import br.edu.unichristus.domain.model.Rental;
import br.edu.unichristus.domain.model.Review;
import br.edu.unichristus.domain.model.User;
import br.edu.unichristus.exception.CommonsException;
import br.edu.unichristus.repository.BookRepository;
import br.edu.unichristus.repository.RentalRepository;
import br.edu.unichristus.repository.UserRepository;
import br.edu.unichristus.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalService {
    @Autowired
    private RentalRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    //Listar rentals de um mesmo livro
    public List<RentalDTO> findRentalsByBookId(Long bookId) {
        List<Rental> rentals = repository.findByBookId(bookId);
        return MapperUtil.parseListObjects(rentals, RentalDTO.class);
    }

    //Listar rentals de um mesmo user
    public List<RentalDTO> findRentalsByUserId(Long userId) {
        List<Rental> rentals = repository.findByUserId(userId);
        return MapperUtil.parseListObjects(rentals, RentalDTO.class);
    }

    public RentalDTO save(RentalDTO rentalDTO) {
        if (rentalDTO.getUserId() == null || rentalDTO.getBookId() == null) {
            throw new CommonsException(HttpStatus.BAD_REQUEST,
                    "unichristus.rental.user.book.notfound",
                    "Usuário e livro são obrigatórios!");
        }

        var user = userRepository.findById(rentalDTO.getUserId());
        var book = bookRepository.findById(rentalDTO.getBookId());

        if (user.isEmpty()) {
            throw new CommonsException(HttpStatus.BAD_REQUEST,
                    "unichristus.rental.user.notfound",
                    "Usuário não encontrado!");

        } else if (book.isEmpty()) {
            throw new CommonsException(HttpStatus.BAD_REQUEST,
                    "unichristus.rental.book.notfound",
                    "Livro não encontrado!");
        }

        var rentalEntity = MapperUtil.parseObject(rentalDTO, Rental.class);
        rentalEntity.setUser(user.get());
        rentalEntity.setBook(book.get());

        var savedRental = repository.save(rentalEntity);
        return MapperUtil.parseObject(savedRental, RentalDTO.class);
    }

    public List<RentalDTO> findAll() {
        var listRentals = repository.findAll();
        return MapperUtil.parseListObjects(listRentals, RentalDTO.class);
    }

    public Rental findById(Long id) {
        var rentalEntity = repository.findById(id);

        if (rentalEntity.isEmpty()) {
            throw new CommonsException(HttpStatus.NOT_FOUND,
                    "unichristus.rental.findbyid.notfound",
                    "Aluguel não encontrado!");
        }
        return repository.findById(id).get();
    }

    public void delete(Long id) {
        var rentalEntity = repository.findById(id);

        if (rentalEntity.isEmpty()) {
            throw new CommonsException(HttpStatus.NOT_FOUND,
                    "unichristus.rental.delete.notfound",
                    "Aluguel não encontrado!");
        }
        repository.deleteById(id);
    }

    public List<RentalDTO> findByUserId(Long userId) {
        var user = userRepository.findById(userId);

        if (user.isEmpty()) { // se o usuário não existir
            throw new CommonsException(HttpStatus.NOT_FOUND,
                    "unichristus.rental.user.findbyuserid.notfound",
                    "Usuário não encontrado!");
        }

        var rentals = repository.findByUserId(userId);

        if (rentals.isEmpty()) { // se existir o usuário, mas não existir aluguel pra ele
            throw new CommonsException(HttpStatus.NOT_FOUND,
                    "unichristus.rental.findbyuserid.notfound",
                    "Aluguel não encontrado para o usuário fornecido!");
        }
        return MapperUtil.parseListObjects(rentals, RentalDTO.class);
    }
}
