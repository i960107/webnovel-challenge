package com.naver.webnovel.user;

import com.naver.webnovel.base.Status;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByIdAndStatus(String id, Status status);
    boolean existsByPhoneAndStatus(String phone, Status status);

}
