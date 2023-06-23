package SpotifyPlaylist.user.repository;


import SpotifyPlaylist.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// JpaRepository를 상속해 @Repository라는 어노테이션이 없어도 IoC가 된다.
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // select * from user where username = username
    public User findByUsername(String username);
}
