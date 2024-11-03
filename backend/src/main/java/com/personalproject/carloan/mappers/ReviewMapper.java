package com.personalproject.carloan.mappers;

import com.personalproject.carloan.dtos.ReviewDTO;
import com.personalproject.carloan.dtos.ShowReviewToVehicle;
import com.personalproject.carloan.entities.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    Review toReview(ReviewDTO reviewDto);

    ReviewDTO toReviewDto(Review review);

    ShowReviewToVehicle toReviewToVehicle(Review review);

}
