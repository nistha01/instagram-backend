package nitish.instagrambackend.repo;

import nitish.instagrambackend.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostRepo extends JpaRepository<Post,Long> {
}
