package writing.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import writing.board.dto.PostWrittenDTO;
import writing.board.dto.ReviewDTO;
import writing.board.entity.PostWritten;
import writing.board.entity.Review;
import writing.board.repository.ReviewRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
    private  final ReviewRepository reviewRepository;
    @Override
    public List<ReviewDTO> getListOfPost(Long post_no) {
        log.info("정보"+post_no);
        List<Review> result = reviewRepository.findByPostWritten(post_no);
        log.info("정보2"+result.stream().map(review -> entityToDto(review)).collect(Collectors.toList()));
        return result.stream().map(review -> entityToDto(review)).collect(Collectors.toList());

    }

    @Override
    public Long register(ReviewDTO reviewDTO) {
        Map<String, Object> entityMap = dtoToEntity(reviewDTO);
        Review review = (Review) entityMap.get("review");
        reviewRepository.save(review);
        return review.getPost_no();
    }
}
