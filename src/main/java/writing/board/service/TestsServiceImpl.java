package writing.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import writing.board.dto.RecommendationDTO;
import writing.board.dto.TestsDTO;
import writing.board.entity.Recommendation;
import writing.board.entity.Tests;
import writing.board.repository.RecommendationRepository;
import writing.board.repository.TestsRepository;

import java.util.List;
import java.util.Map;

@Service
@Log4j2
@RequiredArgsConstructor
public class TestsServiceImpl implements TestsService{
  private final TestsRepository repository;
    @Override
    public Long register(TestsDTO testsDTO) {
        Map<String, Object> entityMap = dtoToEntity(testsDTO);
        Tests tests = (Tests) entityMap.get("tests");
        repository.save(tests);
        return tests.getNo();
    }

    @Override
    public TestsDTO find_no(Long no) {
        List<Object[]> result = repository.getTestWithAll(no);
        Long _no = (Long) result.get(0)[0];
        String id = (String) result.get(0)[2];
        Long post_no = (Long) result.get(0)[3];
        Boolean good = (Boolean) result.get(0)[4];
        Boolean bad = (Boolean) result.get(0)[5];
        TestsDTO dto= TestsDTO.builder()
                .no(_no).id(id).post_no(post_no).good(good).bad(bad)
                .build();
        return dto;
    }
}
