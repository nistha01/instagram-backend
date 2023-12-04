package nitish.instagrambackend.service;

import nitish.instagrambackend.model.Likes;
import nitish.instagrambackend.model.Post;
import nitish.instagrambackend.model.User;
import nitish.instagrambackend.repo.ILikeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {
    @Autowired
    ILikeRepo iLikeRepo;
    public void deleteAllLikesByPostId(List<Likes> allLikesOnPost) {
        iLikeRepo.deleteAll(allLikesOnPost);
    }

    public List<Likes> getAllLikesOnPostByPostId(Post post) {
      return  iLikeRepo.findByInstaPost(post);
    }

    public void likePost( User liker,Long postId,Likes likes) {
        //set liker
        likes.setLiker(liker);
        //se which post

        iLikeRepo.save(likes);
    }

    public String removeLikesByLikerAndPost(User potentialLiker, Post instaPostToBeUnLiked) {
        List<Likes> likes =  iLikeRepo.findByInstaPostAndLiker(instaPostToBeUnLiked,potentialLiker);

        if(!likes.isEmpty())
        {
            iLikeRepo.deleteAll(likes);
            return "Un-liked";
        }
        {
            return "Not liked yet, cannot dislike!!";
        }
    }

    public String getLikesForPost(Post instaPost) {
        List<Likes> likes  = iLikeRepo.findByInstaPost(instaPost);

        return String.valueOf(likes.size());
    }

    public boolean isAlreadyLiked(Post instaPostToBeLiked, User liker) {
        List<Likes> likes =  iLikeRepo.findByInstaPostAndLiker(instaPostToBeLiked,liker);

        return (likes!=null && likes.size()!=0);
    }

    public void addLike(Likes newLike) {
        iLikeRepo.save(newLike);
    }
}
