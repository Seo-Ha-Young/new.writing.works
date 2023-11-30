package writing.board.service;

import writing.board.dto.RecommendationDTO;
import writing.board.entity.Recommendation;

import java.util.HashMap;
import java.util.Map;

public interface RecommendationService {
    Long register(RecommendationDTO recommendationDTO);

    RecommendationDTO find_no(Long no);

    default Map<String, Object> dtoToEntity(RecommendationDTO dto) {
        Map<String, Object> entityMap = new HashMap<>();
        Recommendation recommendation = Recommendation.builder()
                .id(dto.getId())
                .post_no(dto.getPost_no())
                .push(dto.isPush())
                .build();
        entityMap.put("recommendation", recommendation);
        return entityMap;
    }
}
