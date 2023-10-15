package nitish.instagrambackend.service;

import nitish.instagrambackend.model.Post;
import nitish.instagrambackend.repo.IPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    IPostRepo postRepo;


    public void createInstaPost(Post instaPost) {
        postRepo.save(instaPost);
    }

    public Post getPostById(Long postId) {
        return  postRepo.findById(postId).orElseThrow();

    }

    public void removeById(Long postId) {

        //delete all likes


        // delete all comments


        //finally delete post

    }
}
