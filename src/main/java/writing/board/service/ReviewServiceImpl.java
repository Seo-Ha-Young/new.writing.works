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
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
    private  final ReviewRepository reviewRepository;
    @Override
    public List<ReviewDTO> getListOfPost(Long no) {
            PostWrittenDTO postWritten = PostWrittenDTO.builder().no(no).build();
            log.info("정보"+postWritten);
            List<Review> result = reviewRepository.findByPostWritten(postWritten);
            return result.stream().map(review -> entityToDto(review)).collect(Collectors.toList());

    }
}
