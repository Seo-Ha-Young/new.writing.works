package writing.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import writing.board.dto.PostWrittenDTO;
import writing.board.entity.PostWritten;
import writing.board.repository.PostWrittenRepository;

import java.util.List;

@Service
public class PostService {
    private final PostWrittenRepository postWrittenRepository;

    @Autowired
    public PostService(PostWrittenRepository postWrittenRepository) {
        this.postWrittenRepository = postWrittenRepository;
    }

    public void savePost(PostWrittenDTO postWrittenDTO) {
        PostWritten postWritten = PostWritten.builder()
                .image_no(postWrittenDTO.getImage_no())
                .post_name(postWrittenDTO.getPost_name())
                .writer(postWrittenDTO.getWriter())
                .post_content(postWrittenDTO.getPost_content())
                .build();
        postWrittenRepository.save(postWritten);
    }

    public List<PostWritten> getAllPosts() {
        return postWrittenRepository.findAll();
    }

}