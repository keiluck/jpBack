package com.japanese.reader.repository;

import com.japanese.reader.dto.MobileFavorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 移动端收藏 Repository
 */
@Repository
public interface MobileFavoriteRepository extends JpaRepository<MobileFavorite, Long> {

    List<MobileFavorite> findByUserIdAndTargetType(Long userId, String targetType);

    List<MobileFavorite> findByUserId(Long userId);

    Optional<MobileFavorite> findByUserIdAndTargetIdAndTargetType(Long userId, Long targetId, String targetType);

    boolean existsByUserIdAndTargetIdAndTargetType(Long userId, Long targetId, String targetType);

    void deleteByUserIdAndTargetIdAndTargetType(Long userId, Long targetId, String targetType);
}
