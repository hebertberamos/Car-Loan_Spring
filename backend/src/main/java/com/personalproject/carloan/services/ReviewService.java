package com.personalproject.carloan.services;

import com.personalproject.carloan.dtos.ReviewDTO;
import com.personalproject.carloan.entities.Review;
import com.personalproject.carloan.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    @Transactional(readOnly = true)
    public List<ReviewDTO> findAll(){
        List<Review> reviews = repository.findAll();
        return reviews.stream().map(x -> new ReviewDTO(x)).collect(Collectors.toList());
    }

}
