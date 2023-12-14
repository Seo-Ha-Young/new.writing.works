package writing.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import writing.board.dto.PostWrittenDTO;
import writing.board.dto.ReviewDTO;
import writing.board.entity.Member;
import writing.board.entity.PostWritten;
import writing.board.entity.Review;
import writing.board.repository.MemberRepository;
import writing.board.repository.ReviewRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
    private  final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    @Override
    public List<ReviewDTO> getListOfPost(Long post_no) {
        log.info("정보"+post_no);
        List<Review> result = reviewRepository.findByPostWritten(post_no);
        log.info("정보2"+result.stream().map(review -> entityToDto(review)).collect(Collectors.toList()));
        List<ReviewDTO> reviewDTOList = result.stream().map(review -> entityToDto(review)).collect(Collectors.toList());
        reviewDTOList.stream().map(reviewDTO -> {
            Long member_no = reviewDTO.getMember_no();
            log.info("member_no : "+member_no);
            String nickname = memberRepository.findByMember_no(member_no).getNickname();
            log.info("nickname : "+nickname);
            reviewDTO.setNickname(nickname);
            log.info("reviewDTO : "+reviewDTO);
                    return reviewDTO;
                }
        ).collect(Collectors.toList());
        log.info("reviewDTOList : "+reviewDTOList);
        return reviewDTOList;

    }

    @Override
    public Long register(ReviewDTO reviewDTO) {
        log.info("reviewDTO = "+reviewDTO);
        Map<String, Object> entityMap = dtoToEntity(reviewDTO);
        Review review = (Review) entityMap.get("review");
        log.info("review : "+review.getMember_no()+review.getReview_content()+review.getPost_no());
        reviewRepository.save(review);
        return review.getNo();
    }

    @Override
    public void modify(ReviewDTO reviewDTO) {
        Optional<Review> result = reviewRepository.findById(reviewDTO.getNo());
        if(result.isPresent()) {
            Review review = result.get();
            review.changeContent(reviewDTO.getReview_content());
            reviewRepository.save(review);
        }
    }

    @Override
    public void remove(Long reviewNo) {
        reviewRepository.deleteById(reviewNo);
    }
}
