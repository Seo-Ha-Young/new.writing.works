package writing.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import writing.board.dto.PageRequestDTO;
import writing.board.dto.PageResultDTO;
import writing.board.dto.PostWrittenDTO;
import writing.board.entity.PostWritten;
import writing.board.repository.MemberRepository;
import writing.board.repository.PostWrittenRepository;

import java.util.List;
import java.util.function.Function;

@Service
@Log4j2

public class PostServiceImpl implements PostService {
    private final PostWrittenRepository postWrittenRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public PostServiceImpl(PostWrittenRepository postWrittenRepository, MemberRepository memberRepository) {
        this.postWrittenRepository = postWrittenRepository;
        this.memberRepository = memberRepository;
    }
    private PostWrittenDTO entityToDto(PostWritten postWritten) {
        return PostWrittenDTO.builder()
                .no(postWritten.getNo())
                .post_name(postWritten.getPost_name())
                .post_content(postWritten.getPost_content())
                .writer(postWritten.getWriter())
                .build();
    }

    @Override
    public PageResultDTO<PostWrittenDTO, PostWritten> getList(PageRequestDTO requestDTO) {
        // requestDTO로부터 Pageable을 생성하고, 이를 통해 페이징 처리된 게시물 목록을 가져옵니다.
        Pageable pageable = requestDTO.getPageable(Sort.by("no").descending());
        Page<PostWritten> result = postWrittenRepository.findAll(pageable);

        // 각각의 결과를 PostWrittenDTO로 변환하는 함수를 정의합니다.
        Function<PostWritten, PostWrittenDTO> fn = this::entityToDto;

        // PageResultDTO에 페이징 처리된 결과와 변환 함수를 담아 반환합니다.
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public PostWrittenDTO read(Long no) {
        // 게시물 번호에 해당하는 PostWritten 엔티티를 가져옵니다.
        PostWritten postWritten = postWrittenRepository.findById(no).orElse(null);

        if (postWritten != null) {
            // PostWritten 엔티티를 PostWrittenDTO로 변환합니다.
            return entityToDto(postWritten);
        } else {
            // 해당 번호의 게시물이 없을 경우, 예외 처리 또는 null 또는 에러 메시지를 반환할 수 있습니다.
            return null;
        }
    }

    public void savePost(PostWrittenDTO postWrittenDTO, String currentUserNickname, Long imageNo, String postName) {
        PostWritten postWritten = PostWritten.builder()
                .post_name(postName)
                .post_content(postWrittenDTO.getPost_content())
                .writer(currentUserNickname)
                .image_no(imageNo)
                .build();
        postWrittenRepository.save(postWritten);
    }

    public List<PostWritten> getAllPosts() {
        return postWrittenRepository.findAll();
    }
}
