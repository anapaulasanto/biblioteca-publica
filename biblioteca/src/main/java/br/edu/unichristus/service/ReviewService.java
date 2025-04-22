package br.edu.unichristus.service;

import br.edu.unichristus.domain.dto.review.ReviewDTO;
import br.edu.unichristus.domain.model.Review;
import br.edu.unichristus.exception.CommonsException;
import br.edu.unichristus.repository.ReviewRepository;
import br.edu.unichristus.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository repository;

    public ReviewDTO save(ReviewDTO review){
        var reviewEntity = MapperUtil.parseObject(review, Review.class);
        var savedReview = repository.save(reviewEntity);
        return MapperUtil.parseObject(savedReview, ReviewDTO.class);
    }

    public List<ReviewDTO> findAll(){
        var listReviews = repository.findAll();
        return MapperUtil.parseListObjects(listReviews, ReviewDTO.class);
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
        repository.deleteById(id);
    }


}
