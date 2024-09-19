package com.personalproject.carloan.services;

import com.personalproject.carloan.dtos.ReviewDTO;
import com.personalproject.carloan.entities.Review;
import com.personalproject.carloan.entities.User;
import com.personalproject.carloan.entities.Vehicle;
import com.personalproject.carloan.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private AuthenticationService authenticationService;

    @Transactional(readOnly = true)
    public List<ReviewDTO> findAll(){
        List<Review> reviews = repository.findAll();
        return reviews.stream().map(x -> new ReviewDTO(x)).collect(Collectors.toList());
    }

    public ReviewDTO insert(ReviewDTO dto, User user, Vehicle vehicle){
        Review entity = new Review();

        entity.setText(dto.getText());
        entity.setMoment(Instant.now());
        entity.setAuthor(user);
        entity.setQuantityStars(5);
        entity.setVehicle(vehicle);

        repository.save(entity);

        return new ReviewDTO(entity);
    }

}
