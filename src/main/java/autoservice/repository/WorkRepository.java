package autoservice.repository;

import autoservice.model.Work;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long> {
    @Query("FROM Work w WHERE w.master.id = :id AND w.status = :status")
    List<Work> getAllUnpaidByMasterId(Long id, Work.Status status);
}
