package nitish.instagrambackend.repo;

import nitish.instagrambackend.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILikeRepo extends JpaRepository<Likes,Long> {
}
