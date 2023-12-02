package writing.board.service;


import writing.board.dto.MemberDTO;

public interface MemberService {
    public Long saveMember(MemberDTO memberDTO);
}
