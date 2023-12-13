package writing.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import writing.board.dto.PreferenceDTO;
import writing.board.entity.Member;
import writing.board.entity.PostWritten;
import writing.board.entity.Preference;
import writing.board.repository.MemberRepository;
import writing.board.repository.PostWrittenRepository;
import writing.board.repository.PreferenceRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class PreferenceServiceImpl implements PreferenceService {
  private final PreferenceRepository repository;
  private final MemberRepository memberRepository;
  private  final PostWrittenRepository postWrittenRepository;
    @Override
    public Preference register(PreferenceDTO testsDTO) throws Exception {
        Map<String, Object> entityMap = dtoToEntity(testsDTO);
        Preference preference = (Preference) entityMap.get("preference");
        log.info("추천 정보 "+preference);
        repository.save(preference);
        return preference;

    }


    @Override
    public List<Preference> find_nickname(PreferenceDTO preferenceDTO) {
        Map<String, Object> entityMap = dtoToEntity(preferenceDTO);
        Preference preference = (Preference) entityMap.get("preference");
        List<Preference> nickname = repository.getNicknameWithAll(preference.getId());
        log.info("닉네임 정보 "+nickname);
        return nickname;
    }


    @Override
    public List<PreferenceDTO> find_post_no(Long post_no) {
        List<Preference> preferences = repository.getTestWithPost_No(post_no);
        return preferences.stream().map(preference -> entityToDto(preference, post_no)).collect(Collectors.toList());

    }

    @Override
    public void remove(String id) {
        log.info("취소아이디:"+id);
        repository.deleteByUserId(id);
    }
}


//    @Override
//    public PreferenceDTO find_nickname(Long no) {
//        List<Object[]> result = repository.getTestWithAll(no);
//        Long _no = (Long) result.get(0)[0];
//        String id = (String) result.get(0)[2];
//        Long post_no = (Long) result.get(0)[3];
//        Long good = (Long) result.get(0)[4];
//        Long bad = (Long) result.get(0)[5];
//        PreferenceDTO dto= PreferenceDTO.builder()
//                .no(_no).id(id).post_no(post_no).good(good).bad(bad)
//                .build();
//        return dto;
//    }