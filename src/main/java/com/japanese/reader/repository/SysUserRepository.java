package com.japanese.reader.repository;

import com.japanese.reader.dto.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SysUserRepository extends JpaRepository<SysUser, Long> {

    @Query("SELECT u FROM SysUser u WHERE u.isDeleted = 0 " +
           "AND (:keyword = '' OR u.username LIKE CONCAT('%', :keyword, '%') OR u.realName LIKE CONCAT('%', :keyword, '%')) " +
           "AND (:status = '' OR u.status = :status)")
    Page<SysUser> search(@Param("keyword") String keyword, @Param("status") String status, Pageable pageable);

    boolean existsByUsernameAndIsDeleted(String username, int isDeleted);

    long countByStatusAndIsDeleted(String status, int isDeleted);
}
