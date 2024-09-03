package bg.fmi.javaweb.sportstournamentorganizer.security;

import bg.fmi.javaweb.sportstournamentorganizer.model.users.*;
import bg.fmi.javaweb.sportstournamentorganizer.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.support.CustomSQLErrorCodesTranslation;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final FollowerRepository followerRepository;
    private final ManagerRepository managerRepository;
    private final ModeratorRepository moderatorRepository;
    private final PlayerRepository playerRepository;

    public CustomUserDetailsService(FollowerRepository followerRepository, ManagerRepository managerRepository, ModeratorRepository moderatorRepository, PlayerRepository playerRepository) {
        this.followerRepository = followerRepository;
        this.managerRepository = managerRepository;
        this.moderatorRepository = moderatorRepository;
        this.playerRepository = playerRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;

        Optional<Follower> follower = followerRepository.findByUsername(username);
        if(follower.isPresent()) {
            user = follower.get();
        }

        Optional<Manager> manager = managerRepository.findByUsername(username);
        if(user == null) {
            if(manager.isPresent()) {
                user = manager.get();
            }
        }

        Optional<Moderator> moderator = moderatorRepository.findByUsername(username);
        if(user == null) {
            if(moderator.isPresent()) {
                user = moderator.get();
            }
        }

        Optional<Player> player = playerRepository.findByUsername(username);
        if(user == null) {
            if(player.isPresent()) {
                user = player.get();
            }
        }

        if(user == null) {
            throw new UsernameNotFoundException("The provided user was not found: " + username);
        }

        CustomUserDetails customUserDetails = new CustomUserDetails(user);

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), customUserDetails.getAuthorities());
    }
}
