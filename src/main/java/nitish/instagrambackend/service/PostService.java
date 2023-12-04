package nitish.instagrambackend.service;

import jakarta.transaction.Transactional;
import nitish.instagrambackend.model.Likes;
import nitish.instagrambackend.model.Post;
import nitish.instagrambackend.repo.IPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import nitish.instagrambackend.service.LikeService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {
    @Autowired
    IPostRepo postRepo;
    @Autowired
    LikeService likeService;
    @Autowired
    CommentService commentService;


    public void createInstaPost(Post instaPost) {
        instaPost.setPostCreatedTimeStamp(LocalDateTime.now());
        postRepo.save(instaPost);
    }

    public Post getPostById(Long postId) {
        return  postRepo.findById(postId).orElseThrow();

    }
    @Transactional
    public void removeById(Long postId) {

        //delete all likes
        Post post=postRepo.findById(postId).orElse(null);
        if(post!=null){
            List<Likes> allLikesOnPost=likeService.getAllLikesOnPostByPostId(post);
            likeService.deleteAllLikesByPostId(allLikesOnPost);

        }


        // delete all comments
        commentService.clearCommentsByPost(post);

        //finally delete post
        postRepo.deleteById(postId);

    }
}
