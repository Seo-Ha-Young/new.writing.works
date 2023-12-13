package writing.board.service;

import org.springframework.data.crossstore.ChangeSetPersister;
import writing.board.dto.PreferenceDTO;
import writing.board.entity.PostWritten;
import writing.board.entity.Preference;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface PreferenceService {

    Preference register(PreferenceDTO preferenceDTO) throws Exception;

    List<Preference> find_nickname(PreferenceDTO preferenceDTO);

    List<PreferenceDTO> find_post_no(Long no);

    void remove(String id);
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

    default PreferenceDTO entityToDto(Preference preference, Long post_no) {
        PreferenceDTO preferenceDTO = PreferenceDTO.builder()
                .no(preference.getNo())
                .id(preference.getId())
                .bad(preference.getBad())
                .good(preference.getGood())
                .post_no(post_no)
                .build();
        return preferenceDTO;
    }


}
