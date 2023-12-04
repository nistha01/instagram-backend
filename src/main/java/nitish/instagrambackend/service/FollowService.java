package nitish.instagrambackend.service;

import nitish.instagrambackend.model.Follow;
import nitish.instagrambackend.model.User;
import nitish.instagrambackend.repo.IFollowRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowService {
    IFollowRepo followRepo;
    public void startFollowing(User follower, User target) {
        Follow follow = new Follow(null,target,follower);
        followRepo.save(follow);
    }

    public boolean findByTargetAndFollower(User follower, User target) {
        List<Follow> follows =  followRepo.findByCurrentUserAndCurrentUserFollower(target,follower);

        return !follows.isEmpty();
    }
}
