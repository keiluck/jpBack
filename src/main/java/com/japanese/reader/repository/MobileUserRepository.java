package com.japanese.reader.repository;

import com.japanese.reader.dto.MobileUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 移动端用户 Repository
 */
@Repository
public interface MobileUserRepository extends JpaRepository<MobileUser, Long> {

    Optional<MobileUser> findByUsername(String username);

    Optional<MobileUser> findByPhone(String phone);

    boolean existsByUsername(String username);

    boolean existsByPhone(String phone);
}
