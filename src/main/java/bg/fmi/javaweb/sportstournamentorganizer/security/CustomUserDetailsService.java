package bg.fmi.javaweb.sportstournamentorganizer.security;

import bg.fmi.javaweb.sportstournamentorganizer.model.users.User;
import bg.fmi.javaweb.sportstournamentorganizer.repository.UserRepository;
import org.springframework.jdbc.support.CustomSQLErrorCodesTranslation;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("The provided user was not found: " + username));

        CustomUserDetails customUserDetails = new CustomUserDetails(user);

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), customUserDetails.getAuthorities());
    }
}
