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
  private  final  MemberRepository memberRepository;
  private final PostWrittenRepository writtenRepository;
    @Override
    public Preference register(PreferenceDTO preferenceDTO) throws Exception {
        Member member = memberRepository.findByMember_no(preferenceDTO.getMember_no());
        PostWritten post_no = writtenRepository.findPost_no(preferenceDTO.getPost_no());
        log.info("추가전 넘어온 정보 : "+preferenceDTO);
        Preference preference = dtoToEntity(preferenceDTO, member, post_no);
        log.info("추천 정보 "+preference);
        repository.save(preference);
        return preference;

    }


    @Override
    public Preference find_nickname(PreferenceDTO preferenceDTO) {
        Member member = memberRepository.findByMember_no(preferenceDTO.getMember_no());
        PostWritten post_no = writtenRepository.findPost_no(preferenceDTO.getPost_no());
        log.info("추천하는 회원 정보"+member.getNickname());
        Preference preference = dtoToEntity(preferenceDTO, member, post_no);
        Long member_no = preference.getMember().getNo();
        Long no = post_no.getNo();
        log.info("추천하는 번호 정보"+no);
        log.info("가져올 닉네임 정보"+member_no);
        Preference nicknames = repository.getNicknameWithAll(member_no, no);
        log.info("닉네임 정보 "+nicknames);
        return nicknames;
    }


    @Override
    public List<PreferenceDTO> find_post_no(Long post_no) {
        List<Preference> preferences = repository.getTestWithPost_No(post_no);
        return preferences.stream().map(preference -> entityToDto(preference, post_no)).collect(Collectors.toList());

    }

    @Override
    public void remove(PreferenceDTO preferenceDTO) {
        log.info("취소정보:"+preferenceDTO);
        Long post_no = preferenceDTO.getPost_no();
        Long member_no = memberRepository.findByMember_no(preferenceDTO.getMember_no()).getNo();
        repository.deleteByUserId(member_no, post_no);

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