package writing.board.service;

import writing.board.dto.PageRequestDTO;
import writing.board.dto.PageResultDTO;
import writing.board.dto.PostWrittenDTO;
import writing.board.entity.PostWritten;
public interface PostService {

    PageResultDTO<PostWrittenDTO, PostWritten> getList(PageRequestDTO pageRequestDTO);

    PostWrittenDTO read(Long no);

    PostWritten savePost(PostWrittenDTO writeDTO, String currentUserNickname, Long imageNo, String postContent);
    default PostWrittenDTO entitiesToDTO(PostWritten postWritten) {
        PostWrittenDTO postWrittenDTO = PostWrittenDTO.builder()
                .no(postWritten.getNo())
                .post_name(postWritten.getPost_name())
                .post_content(postWritten.getPost_content())
                .regDate(postWritten.getRegDate())
                .writer(postWritten.getWriter())
                .image_no(postWritten.getImage_no())
                .build();

        return postWrittenDTO;
    }
    default PostWritten dtoToEntity(PostWrittenDTO postWrittenDTO) {
        PostWritten postWritten = PostWritten.builder()
                .post_content(postWrittenDTO.getPost_content())
                .post_name(postWrittenDTO.getPost_name())
                .image_no(postWrittenDTO.getImage_no())
                .writer(postWrittenDTO.getWriter())
                .build();

        return postWritten;
    }


}
