package writing.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import writing.board.dto.ReviewDTO;
import writing.board.service.ReviewService;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("reviews")
public class ReviewController {
    private final ReviewService reviewService;
    @GetMapping("/{no}/all")
    public ResponseEntity<List<ReviewDTO>> getList(@PathVariable("no") Long no) {
        log.info("-----------------------list---------------------");
        log.info("no : "+no);
        List<ReviewDTO> reviewDTOList = reviewService.getListOfPost(no);
        return new ResponseEntity<>(reviewDTOList, HttpStatus.OK);
    }
}
