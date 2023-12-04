package writing.board.service;

import writing.board.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    List<ReviewDTO> getListOfPost(Long no);
}
