package nitish.instagrambackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @ManyToOne
    @JoinColumn(name = "fk_post_id")
    private Post instaPost;

    @ManyToOne
    @JoinColumn(name = "fk_liker_id")
    private User liker;

    public void getLiker(User liker) {
    }
}
