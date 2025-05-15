package br.edu.unichristus.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.edu.unichristus.domain.dto.review.ReviewDTO;
import br.edu.unichristus.domain.model.Book;
import br.edu.unichristus.domain.model.Review;
import br.edu.unichristus.domain.model.User;
import br.edu.unichristus.exception.CommonsException;
import br.edu.unichristus.repository.BookRepository;
import br.edu.unichristus.repository.ReviewRepository;
import br.edu.unichristus.repository.UserRepository;
import br.edu.unichristus.utils.MapperUtil;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository repository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    // Listar reviews de um mesmo livro
    public List<ReviewDTO> findReviewsByBookId(Long bookId) {
        var book = bookRepository.findById(bookId);

        if (book.isEmpty()) { // se não encontrar o livro
            throw new CommonsException(HttpStatus.NOT_FOUND,
                    "unichristus.review.book.findreviewsbybookid.notfound", "Livro não encontrado.");
        }

        List<Review> reviews = repository.findByBookId(bookId);

        if (reviews.isEmpty()) { // se encontrar o livro, mas não encontrar avaliaçao pra ele
            throw new CommonsException(HttpStatus.NOT_FOUND,
                    "unichristus.review.findreviewsbybookid.notfound",
                    "Avaliação não encontrada para o livro fornecido");
        }
        return MapperUtil.parseListObjects(reviews, ReviewDTO.class);
    }

    // Listar reviews de um mesmo usuário
    public List<ReviewDTO> findReviewsByUserId(Long userId) {
        var user = userRepository.findById(userId);

        if (user.isEmpty()) { // se não encontrar o usuario
            throw new CommonsException(HttpStatus.NOT_FOUND,
                    "unichristus.review.user.findreviewsbyuserid.notfound", "Usuário não encontrado.");
        }

        List<Review> reviews = repository.findByUserId(userId);

        if (reviews.isEmpty()) { // se encontrar o usuario, mas não encontrar avaliaçao pra ele
            throw new CommonsException(HttpStatus.NOT_FOUND,
                    "unichristus.review.findreviewsbyuserid.notfound",
                    "Avaliação não encontrada para o usuário fornecido");
        }
        return MapperUtil.parseListObjects(reviews, ReviewDTO.class);
    }

    public ReviewDTO save(ReviewDTO reviewDTO) {
        Double rating = reviewDTO.getRating();
        String ratingStr = String.valueOf(rating);

        if (ratingStr == null) {
            throw new CommonsException(
                    HttpStatus.BAD_REQUEST,
                    "unichristus.review.rating.badrequest",
                    "Nota da avaliação é um campo obrigatório."
            );
        }

        // Verificar se os IDs não são nulos antes de fazer as consultas
        if (reviewDTO.getBookId() == null || reviewDTO.getUserId() == null) {
            throw new CommonsException(HttpStatus.BAD_REQUEST,
                    "unichristus.review.badrequest", "Livro ou Usuário não especificado.");
        }

        // Buscar o livro associado
        Book book = bookRepository.findById(reviewDTO.getBookId())
                .orElseThrow(() -> new CommonsException(HttpStatus.NOT_FOUND,
                        "unichristus.book.findbyid.notfound", "Livro não encontrado"));

        // Buscar o usuário associado
        User user = userRepository.findById(reviewDTO.getUserId())
                .orElseThrow(() -> new CommonsException(HttpStatus.NOT_FOUND,
                        "unichristus.user.findbyid.notfound", "Usuário não encontrado"));

        // Criar e salvar a Review
        Review review = new Review();
        review.setComment(reviewDTO.getComment());
        review.setRating(reviewDTO.getRating());
        review.setReviewDate(reviewDTO.getReviewDate() != null ? reviewDTO.getReviewDate() : LocalDate.now());
        review.setBook(book);
        review.setUser(user);

        Review savedReview = repository.save(review);

        ReviewDTO responseDTO = new ReviewDTO();
        responseDTO.setId(savedReview.getId());
        responseDTO.setComment(savedReview.getComment());
        responseDTO.setRating(savedReview.getRating());
        responseDTO.setReviewDate(savedReview.getReviewDate());
        responseDTO.setBookId(savedReview.getBook().getId());
        responseDTO.setUserId(savedReview.getUser().getId());
        responseDTO.setReviewerName(savedReview.getUser().getName());

        return responseDTO;
    }

    public ReviewDTO update(Long id, ReviewDTO dto) {
        Review existingReview = repository.findById(id)
                .orElseThrow(() -> new CommonsException(HttpStatus.NOT_FOUND,
                        "unichristus.review.update.notfound", "Avaliação não encontrada."));

        // Atualiza os campos permitidos
        existingReview.setRating(dto.getRating());
        existingReview.setComment(dto.getComment());
        existingReview.setReviewDate(dto.getReviewDate() != null ? dto.getReviewDate() : LocalDate.now());

        // Atualiza livro, se necessário
        if (dto.getBookId() != null) {
            Book book = bookRepository.findById(dto.getBookId())
                    .orElseThrow(() -> new CommonsException(HttpStatus.NOT_FOUND,
                            "unichristus.book.findbyid.notfound", "Livro não encontrado."));
            existingReview.setBook(book);
        }

        // Atualiza usuário, se necessário
        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new CommonsException(HttpStatus.NOT_FOUND,
                            "unichristus.user.findbyid.notfound", "Usuário não encontrado."));
            existingReview.setUser(user);
        }

        Review updated = repository.save(existingReview);

        // Retorno como DTO
        ReviewDTO updatedDTO = new ReviewDTO();
        updatedDTO.setId(updated.getId());
        updatedDTO.setRating(updated.getRating());
        updatedDTO.setComment(updated.getComment());
        updatedDTO.setReviewDate(updated.getReviewDate());
        updatedDTO.setBookId(updated.getBook().getId());
        updatedDTO.setUserId(updated.getUser().getId());
        updatedDTO.setReviewerName(updated.getUser().getName());

        return updatedDTO;
    }

    public List<ReviewDTO> findByBookId(Long bookId) {
        var book = bookRepository.findById(bookId);

        if (book.isEmpty()) { // se o livro não existir
            throw new CommonsException(HttpStatus.NOT_FOUND,
                    "unichristus.review.book.findbybookid.notfound",
                    "Livro não encontrado!");
        }
        var reviews = repository.findByBookId(bookId);

        if (reviews.isEmpty()) { // se existir o livro, mas não existir avaliação pra ele
            throw new CommonsException(HttpStatus.NOT_FOUND,
                    "unichristus.review.findbybookid.notfound",
                    "Avaliação não encontrada para o livro fornecido!");
        }
        return MapperUtil.parseListObjects(reviews, ReviewDTO.class);
    }

    public List<ReviewDTO> findAll() {
        var listReviews = repository.findAll();
        return listReviews.stream().map(r -> {
            ReviewDTO dto = new ReviewDTO();
            dto.setId(r.getId());
            dto.setComment(r.getComment());
            dto.setRating(r.getRating());
            dto.setBookId(r.getBook().getId());
            dto.setUserId(r.getUser().getId());
            return dto;
        }).toList();
    }

    public Review findById(Long id) {
        var reviewEntity = repository.findById(id);

        if (reviewEntity.isEmpty()) {
            throw new CommonsException(HttpStatus.NOT_FOUND,
                    "unichristus.review.findbyid.notfound",
                    "Avaliação não encontrada!");
        }
        return repository.findById(id).get();
    }

    public void delete(Long id) {
        var reviewEntity = repository.findById(id);

        if (reviewEntity.isEmpty()) {
            throw new CommonsException(HttpStatus.NOT_FOUND,
                    "unichristus.review.delete.notfound",
                    "Avaliação não encontrada!");
        }
        repository.deleteById(id);
    }

}
