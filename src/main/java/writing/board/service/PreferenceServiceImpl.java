package writing.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import writing.board.dto.PreferenceDTO;
import writing.board.entity.Preference;
import writing.board.repository.PreferenceRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class PreferenceServiceImpl implements PreferenceService {
  private final PreferenceRepository repository;
    @Override
    public Long register(PreferenceDTO testsDTO) {
        Map<String, Object> entityMap = dtoToEntity(testsDTO);
        Preference preference = (Preference) entityMap.get("preference");
        repository.save(preference);
        return preference.getNo();
    }

    @Override
    public PreferenceDTO find_no(Long no) {
        List<Object[]> result = repository.getTestWithAll(no);
        Long _no = (Long) result.get(0)[0];
        String id = (String) result.get(0)[2];
        Long post_no = (Long) result.get(0)[3];
        Long good = (Long) result.get(0)[4];
        Long bad = (Long) result.get(0)[5];
        PreferenceDTO dto= PreferenceDTO.builder()
                .no(_no).id(id).post_no(post_no).good(good).bad(bad)
                .build();
        return dto;
    }

    @Override
    public List<PreferenceDTO> find_post_no(Long post_no) {
        List<Preference> preferences = repository.getTestWithPost_No(post_no);
        return preferences.stream().map(preference -> entityToDto(preference, post_no)).collect(Collectors.toList());

    }
}
