package com.personalproject.carloan.mappers;

import com.personalproject.carloan.dtos.ReviewDTO;
import com.personalproject.carloan.dtos.ShowReviewToVehicle;
import com.personalproject.carloan.entities.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    Review toReview(ReviewDTO reviewDto);

    @Mapping(target = "authorId", source = "authorId")
    @Mapping(target = "vehicleId", source = "vehicleId")
    ReviewDTO toReviewDto(Review review, Long authorId, Long vehicleId);

    ShowReviewToVehicle toReviewToVehicle(Review review);

}
