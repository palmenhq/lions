package lion.model.repository;

import lion.model.Lion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LionRepository extends MongoRepository<Lion, String> {
}
