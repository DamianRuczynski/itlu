package itlu.itlu.repository;

import itlu.itlu.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {

    @Query(value = "SELECT * FROM worker WHERE id_team =:teamId", nativeQuery = true)
    List<Worker> findAllWorkersByTeamId(@Param("teamId") Long teamId);

    @Modifying
    @Query(value = "UPDATE worker w SET w.id_team = null WHERE w.id =:workerId", nativeQuery = true)
    int deleteWorkerFromTeam(@Param("workerId") Long id);

    @Modifying
    @Query(value = "UPDATE worker w SET w.id_team =:teamId WHERE w.id =:workerId", nativeQuery = true)
    void updateWorker(@Param("workerId") Long id,
                      @Param("teamId") Long teamId);

    @Query(value = "SELECT * FROM worker WHERE id_team is null", nativeQuery = true)
    List<Worker> findNotAssignmentEmployees();
}
