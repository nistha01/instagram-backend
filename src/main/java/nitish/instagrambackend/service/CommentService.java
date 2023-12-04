package nitish.instagrambackend.service;

import nitish.instagrambackend.model.Comment;
import nitish.instagrambackend.model.Post;
import nitish.instagrambackend.repo.ICommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    ICommentRepo commentRepo;

    public void clearCommentsByPost(Post post) {
        List<Comment> commentsOfPost = commentRepo.findByInstaPost(post);
        commentRepo.deleteAll(commentsOfPost);
    }

    public void addComment(Comment newComment) {
        commentRepo.save(newComment);
    }

    public Comment findCommentById(Long commentId) {
        return commentRepo.findById(commentId).orElseThrow();
    }

    public void removeCommentById(Long commentId) {
        commentRepo.deleteById(commentId);
    }
}
