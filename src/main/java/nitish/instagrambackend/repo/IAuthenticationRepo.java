package nitish.instagrambackend.repo;

import nitish.instagrambackend.model.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthenticationRepo extends JpaRepository<AuthenticationToken,Long> {
    AuthenticationToken findFirstByTokenValue(String tokenValue);
}
