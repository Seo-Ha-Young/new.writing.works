package writing.board.service;

import writing.board.dto.ReviewDTO;
import writing.board.entity.Review;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ReviewService {
    List<ReviewDTO> getListOfPost(Long post_no);
    Long register(ReviewDTO reviewDTO);
    default ReviewDTO entityToDto(Review review) {
        ReviewDTO reviewDTO = ReviewDTO.builder()
                .no(review.getNo())
                .id(review.getId())
                .review_content(review.getReview_content())
                .post_no(review.getPost_no())
                .regDate(review.getRegDate()).build();
        return reviewDTO;
    }
    default Map<String, Object> dtoToEntity(ReviewDTO reviewDTO) {
        Map<String, Object> entityMap = new HashMap<>();
        Review review = Review.builder()
                .review_content(reviewDTO.getReview_content())
                .id(reviewDTO.getReview_content())
                .post_no(reviewDTO.getPost_no()).build();
        entityMap.put("review", review);
        return entityMap;
    }


}
