package itlu.itlu.repository;

import itlu.itlu.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query(value = "SELECT * FROM project WHERE id_customer =:customerId", nativeQuery = true)
    List<Project> findProjectByCustomerId(@Param("customerId") Long id);

    @Query(value = "SELECT * FROM project WHERE id_team =:teamId", nativeQuery = true)
    List<Project> findProjectByTeamId(@Param("teamId") Long id);
}
