package writing.board.service;

import writing.board.dto.MemberDTO;
import writing.board.dto.PreferenceDTO;
import writing.board.entity.Member;
import writing.board.entity.PostWritten;
import writing.board.entity.Preference;
import writing.board.repository.MemberRepository;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
public interface PreferenceService {

    Preference register(PreferenceDTO preferenceDTO) throws Exception;

    Preference find_nickname(PreferenceDTO preferenceDTO);

    List<PreferenceDTO> find_post_no(Long no);

    void remove(PreferenceDTO preferenceDTO);
    default Preference dtoToEntity(PreferenceDTO dto, Member member, PostWritten postWritten) {
        System.out.println("dto : "+dto);
        Preference preference = Preference.builder()
                .member(member)
                .postWritten(postWritten)
                .good(dto.getGood())
                .bad(dto.getBad())
                .build();
        System.out.println("dtoToEntity : "+preference);
        return preference;
    }

    default PreferenceDTO entityToDto(Preference preference, Long post_no) {
        PreferenceDTO preferenceDTO = PreferenceDTO.builder()
                .no(preference.getNo())
                .member_no(preference.getMember().getNo())
                .bad(preference.getBad())
                .good(preference.getGood())
                .post_no(post_no)
                .build();
        return preferenceDTO;
    }


}
