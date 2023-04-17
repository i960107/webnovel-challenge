package com.naver.webnovel.user;

import com.naver.webnovel.base.Status;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByIdAndStatus(String id, Status status);

    boolean existsByPhoneAndStatus(String phone, Status status);

    Optional<User> findByIdxAndStatus(Long idx, Status status);

//    @Query("select u.idx from USER u where u.id=:id and u.password=:password and u.status=:status")
//    Optional<Long> findIdxByIdAndPasswordAndStatus(String id, String password, String status);

    @Query("select u.idx from User u where u.id=:id and u.password=:password and u.status=:status")
    Optional<Long> findIdxByIdAndPasswordAndStatus(String id, String password, Status status);

    @Query("select u.author.idx from User u where u.idx=:userIdx and u.author.idx=:authorIdx and u.status=:status")
    Optional<Long> findIdxByIdxAndAuthorIdxAndStatus(Long authorIdx, Long userIdx, Status status);
}
