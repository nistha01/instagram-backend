package nitish.instagrambackend.repo;

import nitish.instagrambackend.model.Likes;
import nitish.instagrambackend.model.Post;
import nitish.instagrambackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILikeRepo extends JpaRepository<Likes,Long> {
   

    List<Likes> findByInstaPost(Post post);

    List<Likes> findByInstaPostAndLiker(Post instaPostToBeUnLiked, User potentialLiker);
}
