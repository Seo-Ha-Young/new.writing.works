package writing.board.service;

import writing.board.dto.EssayDTO;
import writing.board.entity.Essay;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

public interface EssayService {
    Long register(EssayDTO essayDTO);

    default Map<String, Object> dtoToEntity(EssayDTO essayDTO) {
        Map<String, Object> entityMap = new HashMap<>();
        Essay essay = Essay.builder()
                .essay_content(essayDTO.getEssay_content())
                .book_name(essayDTO.getBook_name())
                .genre(essayDTO.getGenre())
                .writer(essayDTO.getWriter())
                .build();
        entityMap.put("essay", essay);

        return entityMap;
    }


}
