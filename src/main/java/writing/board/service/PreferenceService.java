package writing.board.service;

import writing.board.dto.PreferenceDTO;
import writing.board.entity.PostWritten;
import writing.board.entity.Preference;

import java.util.HashMap;
import java.util.Map;

public interface PreferenceService {
    Long register(PreferenceDTO testsDTO);

    PreferenceDTO find_no(Long no);

    default Map<String, Object> dtoToEntity(PreferenceDTO dto) {
        Map<String, Object> entityMap = new HashMap<>();
        Preference preference = Preference.builder()
                .id(dto.getId())
                .postWritten(PostWritten.builder().no(dto.getPost_no()).build())
                .good(dto.getGood())
                .bad(dto.getBad())
                .build();
        entityMap.put("preference", preference);
        return entityMap;
    }
}
