package app.repository;

import app.entity.Peak;
import app.entity.Root;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeakRepository extends CrudRepository<Peak, String> {
    List<Peak> findByRoot(@Param("root") Root root);
}
