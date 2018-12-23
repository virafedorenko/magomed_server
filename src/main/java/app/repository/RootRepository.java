package app.repository;

import app.entity.Root;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RootRepository extends CrudRepository<Root, String> {
}
