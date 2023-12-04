package nitish.instagrambackend.repo;

import nitish.instagrambackend.model.Follow;
import nitish.instagrambackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IFollowRepo extends JpaRepository<Follow,Long> {
    List<Follow> findByCurrentUserAndCurrentUserFollower(User target, User follower);
}
