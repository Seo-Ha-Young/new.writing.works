package writing.board.service;

import writing.board.dto.ReviewDTO;
import writing.board.entity.Member;
import writing.board.entity.PostWritten;
import writing.board.entity.Review;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ReviewService {
    List<ReviewDTO> getListOfPost(Long post_no);
    Long register(ReviewDTO reviewDTO);
    void modify(ReviewDTO reviewDTO);
    void remove(Long reviewNo);
    default ReviewDTO entityToDto(Review review) {
        ReviewDTO reviewDTO = ReviewDTO.builder()
                .no(review.getNo())
                .member_no(review.getMember_no())
                .review_content(review.getReview_content())
                .post_no(review.getPost_no())
                .regDate(review.getRegDate()).build();
        return reviewDTO;
    }
    default Map<String, Object> dtoToEntity(ReviewDTO reviewDTO) {
        Map<String, Object> entityMap = new HashMap<>();
        Review review = Review.builder()
                .review_content(reviewDTO.getReview_content())
                .member_no(reviewDTO.getMember_no())
                .post_no(reviewDTO.getPost_no()).build();
        entityMap.put("review", review);
        return entityMap;
    }



}
