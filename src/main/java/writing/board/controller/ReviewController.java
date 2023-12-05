package writing.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import writing.board.dto.ReviewDTO;
import writing.board.service.ReviewService;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    @GetMapping("/{post_no}/all")
    public ResponseEntity<List<ReviewDTO>> getList(@PathVariable("post_no") Long no) {
        log.info("-----------------------list---------------------");
        log.info("no : "+no);
        List<ReviewDTO> reviewDTOList = reviewService.getListOfPost(no);
        log.info("정보들 : "+reviewDTOList);
        return new ResponseEntity<>(reviewDTOList, HttpStatus.OK);
    }

    @PostMapping("/{post_no}")
    public ResponseEntity<Long> addReview(@RequestBody ReviewDTO reviewDTO) {
        log.info(("----------------------add Review--------------------"));
        log.info("reviewDTO "+reviewDTO);
        Long no = reviewService.register(reviewDTO);
        log.info("review_no : "+no);
        return new ResponseEntity<>(no, HttpStatus.OK);
    }

}
