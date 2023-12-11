package writing.board.service;

import writing.board.dto.PageRequestDTO;
import writing.board.dto.PageResultDTO;
import writing.board.dto.PostWrittenDTO;
import writing.board.entity.PostWritten;

public interface BoardService {
    PageResultDTO<PostWrittenDTO, Object[]> getList(PageRequestDTO requestDTO);

    PostWrittenDTO read(Long no);
    default PostWrittenDTO entitiesToDTO(PostWritten postWritten, Long badCnt, Long goodCnt){
        PostWrittenDTO postWrittenDTO = PostWrittenDTO.builder()
                .no(postWritten.getNo())
                .post_name(postWritten.getPost_name())
                .post_content(postWritten.getPost_content())
                .regDate(postWritten.getRegDate())
                .writer(postWritten.getWriter())
                .image_no((postWritten.getImage_no()))
                .build();
        postWrittenDTO.setGoodCnt(goodCnt.intValue());
        postWrittenDTO.setBadCnt(badCnt.intValue());

        return postWrittenDTO;
    }
}

