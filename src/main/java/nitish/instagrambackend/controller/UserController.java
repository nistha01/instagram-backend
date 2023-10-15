package nitish.instagrambackend.controller;

import jakarta.validation.Valid;
import nitish.instagrambackend.model.Post;
import nitish.instagrambackend.model.User;
import nitish.instagrambackend.service.AuthenticationService;
import nitish.instagrambackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authenticationService;


    @PostMapping("user/signup")
    public String userSignUp(@Valid @RequestBody User newUser)
    {

        return userService.userSignUp(newUser);
    }



    //sign in
    @PostMapping("user/signIn/{email}/{password}")
    public String userSignIn(@PathVariable String email, @PathVariable String password)
    {
        return userService.userSignIn(email,password);
    }


    //sign out
    @DeleteMapping("user/signOut")
    public String userSignOut(@RequestParam String email, @RequestParam String token)
    {
        return userService.userSignOut(email,token);
    }



    @PostMapping("InstPost")
    public String createInstaPost(@RequestParam String email,@RequestParam String tokenValue, @RequestBody Post instaPost)
    {
        return userService.createInstaPost(email,tokenValue,instaPost);
    }


    @DeleteMapping("InstaPost/{postId}")
    public String deleteInstaPost(@RequestParam String email, @RequestParam String tokenValue, @PathVariable Long postId)
    {
        return userService.deleteInstaPost(email,tokenValue,postId);
    }

}
