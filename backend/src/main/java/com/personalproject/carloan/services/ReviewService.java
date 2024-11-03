package com.personalproject.carloan.services;

import com.personalproject.carloan.dtos.ReviewDTO;
import com.personalproject.carloan.entities.Review;
import com.personalproject.carloan.entities.User;
import com.personalproject.carloan.entities.Vehicle;
import com.personalproject.carloan.mappers.ReviewMapper;
import com.personalproject.carloan.repositories.ReviewRepository;
import com.personalproject.carloan.repositories.VehicleRepository;
import com.personalproject.carloan.services.exceptions.DatabaseException;
import com.personalproject.carloan.services.exceptions.ResourcesNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private AuthenticationService authenticationService;

    private final ReviewMapper reviewMapper;

    public ReviewService(ReviewMapper reviewMapper){
        this.reviewMapper = reviewMapper;
    }

    @Transactional(readOnly = true)
    public List<ReviewDTO> findAll(){
        List<Review> reviews = repository.findAll();
        return reviews.stream().map(reviewMapper::toReviewDto).collect(Collectors.toList());
    }

    @Transactional
    public List<ReviewDTO> findAllByVehicle(Long vehicleId){
        try {
            Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new ResourcesNotFoundException("Vehicle id not found"));

            if (vehicle != null) {
                List<Review> reviews = repository.findReviewByVehicle(vehicle.getId());

                List<ReviewDTO> reviewsDto = new ArrayList<>();
                for (Review r : reviews) {
                    ReviewDTO reviewDTO = reviewMapper.toReviewDto(r);
                    reviewsDto.add(reviewDTO);
                }

                return reviewsDto;
            }

            return null;
        } catch (ResourcesNotFoundException e){
            throw new DatabaseException("Vehicle not found");
        }
    }

    public ReviewDTO insert(ReviewDTO dto, User user, Vehicle vehicle){
        Review entity = new Review();

        entity.setText(dto.getText());
        entity.setMoment(Instant.now());
        entity.setAuthor(user);
        entity.setQuantityStars(5);
        entity.setVehicle(vehicle);

        repository.save(entity);

        return reviewMapper.toReviewDto(entity);
    }

}
