package writing.board.service;

import writing.board.dto.ReviewDTO;
import writing.board.entity.Review;

import java.util.List;

public interface ReviewService {
    List<ReviewDTO> getListOfPost(Long post_no);

    default ReviewDTO entityToDto(Review review) {
        ReviewDTO reviewDTO = ReviewDTO.builder()
                .no(review.getNo())
                .id(review.getId())
                .review_content(review.getReview_content())
                .post_no(review.getPost_no())
                .regDate(review.getRegDate()).build();
        return reviewDTO;
    }
}
