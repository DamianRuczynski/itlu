package itlu.itlu.repository;

import itlu.itlu.model.Meets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetsRepository extends JpaRepository<Meets, Long> {
}
