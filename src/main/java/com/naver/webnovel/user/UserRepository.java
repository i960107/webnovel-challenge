package com.naver.webnovel.user;

import com.naver.webnovel.base.Status;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByIdAndStatus(String id, Status status);
    Optional<User> findByPhoneAndStatus(String phone, Status status);

}
