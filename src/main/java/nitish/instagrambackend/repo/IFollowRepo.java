package nitish.instagrambackend.repo;

import nitish.instagrambackend.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFollowRepo extends JpaRepository<Follow,Long> {
}
