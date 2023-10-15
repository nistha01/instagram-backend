package nitish.instagrambackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nitish.instagrambackend.model.enums.PostType;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    private String postContent;
    private String postCaption;
    private String postLocation;
    private PostType postType;

    private LocalDateTime postCreatedTimeStamp;

    @ManyToOne
    @JoinColumn(name = "fk_owner_user_id")
    private User postOwner;
}
