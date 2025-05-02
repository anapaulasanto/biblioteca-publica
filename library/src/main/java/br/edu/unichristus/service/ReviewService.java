package br.edu.unichristus.service;

import br.edu.unichristus.domain.dto.book.BookDTO;
import br.edu.unichristus.domain.dto.review.ReviewDTO;
import br.edu.unichristus.domain.model.Book;
import br.edu.unichristus.domain.model.Review;
import br.edu.unichristus.domain.model.User;
import br.edu.unichristus.exception.CommonsException;
import br.edu.unichristus.repository.ReviewRepository;
import br.edu.unichristus.repository.BookRepository;
import br.edu.unichristus.repository.UserRepository;
import br.edu.unichristus.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository repository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    //Listar reviews de um mesmo livro
    public List<ReviewDTO> findReviewsByBookId(Long bookId) {
        List<Review> reviews = repository.findByBookId(bookId);
        return MapperUtil.parseListObjects(reviews, ReviewDTO.class);
    }

    //Listar reviews de um mesmo usuário
    public List<ReviewDTO> findReviewsByUserId(Long userId) {
        List<Review> reviews = repository.findByUserId(userId);
        return MapperUtil.parseListObjects(reviews, ReviewDTO.class);
    }


    public ReviewDTO save(ReviewDTO reviewDTO) {
        // Verificar se os IDs não são nulos antes de fazer as consultas
        if (reviewDTO.getBookId() == null || reviewDTO.getUserId() == null) {
            throw new CommonsException(HttpStatus.BAD_REQUEST,
                    "unichristus.review.invaliddata", "Livro ou Usuário não especificado.");
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
        review.setReviewDate(reviewDTO.getReviewDate() != null ? reviewDTO.getReviewDate() : LocalDate.now()); // Adicionando data atual se não for fornecida
        review.setBook(book);
        review.setUser(user);

        Review savedReview = repository.save(review);

        // Criando o DTO e preenchendo reviewerName corretamente
        ReviewDTO responseDTO = new ReviewDTO();
        responseDTO.setId(savedReview.getId());
        responseDTO.setComment(savedReview.getComment());
        responseDTO.setRating(savedReview.getRating());
        responseDTO.setReviewDate(savedReview.getReviewDate());
        responseDTO.setBookId(savedReview.getBook().getId());
        responseDTO.setUserId(savedReview.getUser().getId());
        responseDTO.setReviewerName(savedReview.getUser().getName()); // Preenchendo o reviewerName

        return responseDTO;
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

    public Review findById(Long id){
        var reviewEntity = repository.findById(id);

        if(reviewEntity.isEmpty()){
            throw new CommonsException(HttpStatus.NOT_FOUND,
                    "unichristus.review.findbyid.notfound",
                    "Avaliação não encontrada!");
        }
        return repository.findById(id).get();
    }

    public void delete(Long id){
        var reviewEntity = repository.findById(id);

        if(reviewEntity.isEmpty()){
            throw new CommonsException(HttpStatus.NOT_FOUND,
                    "unichristus.review.delete.notfound",
                    "Avaliação não encontrada!");
        }
        repository.deleteById(id);
    }


}
