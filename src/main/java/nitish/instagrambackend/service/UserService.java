package nitish.instagrambackend.service;

import nitish.instagrambackend.model.AuthenticationToken;
import nitish.instagrambackend.model.Post;
import nitish.instagrambackend.model.User;
import nitish.instagrambackend.repo.IUserRepo;
import nitish.instagrambackend.service.emailUtility.EmailService;
import nitish.instagrambackend.service.hashingUtility.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserService {
    @Autowired
    IUserRepo userRepo;
    @Autowired
    PostService postService;
    @Autowired
    LikeService likeService;
    @Autowired
    CommentService commentService;
    @Autowired
    FollowService followService;
    @Autowired
    AuthenticationService authenticationService;

    public String userSignUp(User newUser) {

        //check if already exist -> Not allowed : try logging in

        String newEmail = newUser.getUserEmail();

        User existingUser = userRepo.findFirstByUserEmail(newEmail);

        if(existingUser != null)
        {
            return "email already in use";
        }

        // passwords are encrypted before we store it in the table

        String signUpPassword = newUser.getUserPassword();

        try {
            String encryptedPassword = PasswordEncryptor.encrypt(signUpPassword);

            newUser.setUserPassword(encryptedPassword);


            // patient table - save patient
            userRepo.save(newUser);
            return "Insta user registered";

        } catch (NoSuchAlgorithmException e) {

            return "Internal Server issues while saving password, try again later!!!";
        }

    }


    public String userSignIn(String email, String password) {

        //check if the email is there in your tables
        //sign in only possible if this person ever signed up


        User existingUser = userRepo.findFirstByUserEmail(email);

        if(existingUser == null)
        {
            return "Not a valid email, Please sign up first !!!";
        }

        //password should be matched

        try {
            String encryptedPassword = PasswordEncryptor.encrypt(password);

            if(existingUser.getUserPassword().equals(encryptedPassword))
            {
                // return a token for this sign in
                AuthenticationToken token  = new AuthenticationToken(existingUser);

                if(EmailService.sendEmail(email,"otp after login", token.getTokenValue())) {
                    authenticationService.createToken(token);
                    return "check email for otp/token!!!";
                }
                else {
                    return "error while generating token!!!";
                }

            }
            else {
                //password was wrong!!!
                return "Invalid Credentials!!!";
            }

        } catch (NoSuchAlgorithmException e) {

            return "Internal Server issues while saving password, try again later!!!";
        }
    }

    public String userSignOut(String email,String tokenValue) {
        if(authenticationService.authenticate(email,tokenValue)) {
            authenticationService.deleteToken(tokenValue);
            return "Sign Out successful!!";
        }
        else {
            return "Un Authenticated access!!!";
        }
    }

    public String createInstaPost(String email, String tokenValue, Post instaPost) {

        if(authenticationService.authenticate(email,tokenValue)) {

            User existingUser = userRepo.findFirstByUserEmail(email);
            instaPost.setPostOwner(existingUser);

            postService.createInstaPost(instaPost);
            return instaPost.getPostType() + " posted!!";

        }
        else {
            return "Un Authenticated access!!!";
        }
    }

    public String deleteInstaPost(String email, String tokenValue, Long postId) {

        if(authenticationService.authenticate(email,tokenValue)) {

            Post instaPost =  postService.getPostById(postId);
            String  postOwnerEmail =  instaPost.getPostOwner().getUserEmail();

            if(email.equals(postOwnerEmail))
            {


                //finally delete the insta post
                postService.removeById(postId);
                return "post removed!!";

            }
            else {
                return "Un authorized access!!";
            }


        }
        else {
            return "Un Authenticated access!!!";
        }
    }
}
