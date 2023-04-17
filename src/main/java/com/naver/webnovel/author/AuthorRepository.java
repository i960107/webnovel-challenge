package com.naver.webnovel.author;

import com.naver.webnovel.base.Status;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByIdxAndStatus(Long authorIdx, Status status);

}
