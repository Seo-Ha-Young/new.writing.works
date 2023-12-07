package writing.board.service;

import writing.board.dto.RecommendationDTO;
import writing.board.dto.TestsDTO;
import writing.board.entity.PostWritten;
import writing.board.entity.Recommendation;
import writing.board.entity.Tests;

import java.util.HashMap;
import java.util.Map;

public interface TestsService {
    Long register(TestsDTO testsDTO);

    TestsDTO find_no(Long no);

    default Map<String, Object> dtoToEntity(TestsDTO dto) {
        Map<String, Object> entityMap = new HashMap<>();
        Tests tests = Tests.builder()
                .id(dto.getId())
                .postWritten(PostWritten.builder().no(dto.getPost_no()).build())
                .good(dto.getGood())
                .bad(dto.getBad())
                .build();
        entityMap.put("tests", tests);
        return entityMap;
    }
}
