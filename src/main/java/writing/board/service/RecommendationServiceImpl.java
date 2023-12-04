package writing.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import writing.board.dto.RecommendationDTO;
import writing.board.entity.Recommendation;
import writing.board.repository.RecommendationRepository;

import java.util.List;
import java.util.Map;
@Service
@Log4j2
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService{
  private final RecommendationRepository repository;
    @Override
    public Long register(RecommendationDTO recommendationDTO) {
        Map<String, Object> entityMap = dtoToEntity(recommendationDTO);
        Recommendation recommendation = (Recommendation) entityMap.get("recommendation");
        repository.save(recommendation);
        return recommendation.getNo();
    }

    @Override
    public RecommendationDTO find_no(Long no) {
        List<Object[]> result = repository.getRecommendationWithAll(no);
        Long _no = (Long) result.get(0)[0];
        String id = (String) result.get(0)[2];
        Long post_no = (Long) result.get(0)[3];
        Boolean push = (Boolean) result.get(0)[4];
        RecommendationDTO dto= RecommendationDTO.builder()
                .no(_no).id(id).post_no(post_no).push(push)
                .build();
        return dto;
    }
}
