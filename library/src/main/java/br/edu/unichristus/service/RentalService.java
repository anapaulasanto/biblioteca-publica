package br.edu.unichristus.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.edu.unichristus.domain.dto.rental.RentalDTO;
import br.edu.unichristus.domain.model.Rental;
import br.edu.unichristus.exception.CommonsException;
import br.edu.unichristus.repository.BookRepository;
import br.edu.unichristus.repository.RentalRepository;
import br.edu.unichristus.repository.UserRepository;
import br.edu.unichristus.utils.MapperUtil;

@Service
public class RentalService {
    @Autowired
    private RentalRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    // Listar rentals de um mesmo livro
    public List<RentalDTO> findRentalsByBookId(Long bookId) {
        var book = bookRepository.findById(bookId);

        if (book.isEmpty()) { // se nao encontrar o livro fornecido
            throw new CommonsException(HttpStatus.NOT_FOUND,
                    "unichristus.rental.bookid.findrentalsbybookid.notfound",
                    "Livro não encontrado!");
        }

        List<Rental> rentals = repository.findByBookId(bookId);

        if (rentals.isEmpty()) { // se encontrar o livro fornecido, nas nao existir aluguel pro ele
            throw new CommonsException(HttpStatus.NOT_FOUND,
                    "unichristus.rental.findrentalsbybookid.notfound",
                    "Aluguel não encontrado para o livro fornecido!");
        }
        return MapperUtil.parseListObjects(rentals, RentalDTO.class);
    }

    // Listar rentals de um mesmo user
    public List<RentalDTO> findRentalsByUserId(Long userId) {
        var user = userRepository.findById(userId);

        if (user.isEmpty()) { // se nao encontrar o usuario fornecido
            throw new CommonsException(HttpStatus.NOT_FOUND,
                    "unichristus.rental.userid.findrentalsbyuserid.notfound",
                    "Usuário não encontrado!");
        }

        List<Rental> rentals = repository.findByUserId(userId);

        if (rentals.isEmpty()) { // se encontrar o usuario fornecido, nas nao existir aluguel pro ele
            throw new CommonsException(HttpStatus.NOT_FOUND,
                    "unichristus.rental.findrentalsbyuserid.notfound",
                    "Aluguel não encontrado para o usuário fornecido!");
        }
        return MapperUtil.parseListObjects(rentals, RentalDTO.class);
    }

    public RentalDTO save(RentalDTO rentalDTO) {
        if (rentalDTO.getRentalDate() == null) {
            throw new CommonsException(HttpStatus.BAD_REQUEST,
                    "unichristus.rental.rentaldate.badrequest",
                    "Data do aluguel é um campo obrigatório");
        }

        if (rentalDTO.getUserId() == null || rentalDTO.getBookId() == null) {
            throw new CommonsException(HttpStatus.BAD_REQUEST,
                    "unichristus.rental.user.book.badrequest",
                    "Usuário e livro são obrigatórios!");
        }

        var user = userRepository.findById(rentalDTO.getUserId());
        var book = bookRepository.findById(rentalDTO.getBookId());

        if (user.isEmpty()) {
            throw new CommonsException(HttpStatus.NOT_FOUND,
                    "unichristus.rental.user.notfound",
                    "Usuário não encontrado!");

        } else if (book.isEmpty()) {
            throw new CommonsException(HttpStatus.NOT_FOUND,
                    "unichristus.rental.book.notfound",
                    "Livro não encontrado!");
        }

        var rentalEntity = MapperUtil.parseObject(rentalDTO, Rental.class);
        rentalEntity.setUser(user.get());
        rentalEntity.setBook(book.get());

        var savedRental = repository.save(rentalEntity);
        return MapperUtil.parseObject(savedRental, RentalDTO.class);
    }

    public RentalDTO update(Long id, RentalDTO rentalDTO) {
        Optional<Rental> existingRentalOpt = repository.findById(id);

        if (existingRentalOpt.isEmpty()) {
            throw new CommonsException(HttpStatus.NOT_FOUND,
                    "unichristus.rental.update.notfound",
                    "Aluguel não encontrado para o ID informado.");
        }
        Rental existingRental = existingRentalOpt.get();

        if (rentalDTO.getRentalDate() != null)
            existingRental.setRentalDate(rentalDTO.getRentalDate());

        if (rentalDTO.getReturnDate() != null)
            existingRental.setReturnDate(rentalDTO.getReturnDate());

        if (rentalDTO.getStatus() != null)
            existingRental.setStatus(rentalDTO.getStatus());

        if (rentalDTO.getNotes() != null)
            existingRental.setNotes(rentalDTO.getNotes());

        if (rentalDTO.getUserId() != null) {
            var user = userRepository.findById(rentalDTO.getUserId());
            if (user.isEmpty()) {
                throw new CommonsException(HttpStatus.NOT_FOUND,
                        "unichristus.rental.user.notfound",
                        "Usuário não encontrado!");
            }
            existingRental.setUser(user.get());
        }
        if (rentalDTO.getBookId() != null) {
            var book = bookRepository.findById(rentalDTO.getBookId());
            if (book.isEmpty()) {
                throw new CommonsException(HttpStatus.NOT_FOUND,
                        "unichristus.rental.book.notfound",
                        "Livro não encontrado!");
            }
            existingRental.setBook(book.get());
        }

        Rental updatedRental = repository.save(existingRental);
        return MapperUtil.parseObject(updatedRental, RentalDTO.class);
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
