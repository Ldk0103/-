package net.fullstack10.demo.repository;

import net.fullstack10.demo.domain.BbsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BbsRepository extends JpaRepository<BbsEntity, Long> {
    @Query(value = "SELECT NOW()", nativeQuery = true)
    public String getNow();

    @Query(value="SELECT COUNT(*) FROM tbl_board", nativeQuery = true)
    public long getTotalCount();
}
