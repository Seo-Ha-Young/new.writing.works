package writing.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import writing.board.repository.PostWrittenRepository;

@Service
public class PostService {
    private final PostWrittenRepository postWrittenRepository;

    @Autowired
    public PostService(PostWrittenRepository postWrittenRepository) {
        this.postWrittenRepository = postWrittenRepository;
    }

    public void savePost(PostWrittenDTO postWrittenDTO) {
        // PostWrittenDTO를 Post 엔티티로 변환하여 저장하는 예시
        Post post = new Post();
        post.setContent(postWrittenDTO.getContent());
        // 작성자, 날짜 등의 정보도 필요한 경우에 설정

        postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

}
