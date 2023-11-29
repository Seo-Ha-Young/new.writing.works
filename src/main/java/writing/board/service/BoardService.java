package writing.board.service;

import writing.board.dto.ImageDTO;
import writing.board.dto.PageRequestDTO;
import writing.board.dto.PageResultDTO;
import writing.board.dto.PostWrittenDTO;
import writing.board.entity.Image;
import writing.board.entity.PostWritten;

import java.util.List;
import java.util.stream.Collectors;

public interface BoardService {
    PageResultDTO<PostWrittenDTO, PostWritten> getList(PageRequestDTO requestDTO);

    PostWrittenDTO read(Long no);
    default PostWrittenDTO entitiesToDTO(PostWritten postWritten){
        PostWrittenDTO postWrittenDTO = PostWrittenDTO.builder()
                .no(postWritten.getNo())
                .post_name(postWritten.getPost_name())
                .post_content(postWritten.getPost_content())
                .regDate(postWritten.getRegDate())
                .writer(postWritten.getWriter())
                .image_no((postWritten.getImage_no()))
                .build();

        return postWrittenDTO;
    }
}
