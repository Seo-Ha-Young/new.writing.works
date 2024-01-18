package writing.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import writing.board.entity.PostWritten;

public interface SearchRepository {
    PostWritten search1();

    Page<Object[]> searchPage(String type, String keyword, Pageable pageable);
}
