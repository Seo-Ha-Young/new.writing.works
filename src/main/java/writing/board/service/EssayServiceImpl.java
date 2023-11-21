package writing.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import writing.board.dto.EssayDTO;
import writing.board.entity.Essay;
import writing.board.repository.EssayRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
@RequiredArgsConstructor
public class EssayServiceImpl implements EssayService{
    private final EssayRepository essayRepository;

    @Override
    @Transactional
    public Long register(EssayDTO essayDTO) {
        Map<String, Object> entityMap = dtoToEntity(essayDTO);
        Essay essay = (Essay) entityMap.get("essay");
        essayRepository.save(essay);
        return essay.getNo();
    }


}
