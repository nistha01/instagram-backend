package nitish.instagrambackend.repo;

import nitish.instagrambackend.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentRepo extends JpaRepository<Comment,Long> {
}
