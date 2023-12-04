package nitish.instagrambackend.repo;

import nitish.instagrambackend.model.Comment;
import nitish.instagrambackend.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICommentRepo extends JpaRepository<Comment,Long> {
    List<Comment> findByInstaPost(Post post);
}
