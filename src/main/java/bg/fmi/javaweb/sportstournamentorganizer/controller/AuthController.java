package bg.fmi.javaweb.sportstournamentorganizer.controller;

import bg.fmi.javaweb.sportstournamentorganizer.dto.AuthResponseDto;
import bg.fmi.javaweb.sportstournamentorganizer.dto.LoginDto;
import bg.fmi.javaweb.sportstournamentorganizer.dto.RegisterDto;
import bg.fmi.javaweb.sportstournamentorganizer.exception.*;
import bg.fmi.javaweb.sportstournamentorganizer.model.users.Role;
import bg.fmi.javaweb.sportstournamentorganizer.security.JWTGenerator;
import bg.fmi.javaweb.sportstournamentorganizer.service.FollowerService;
import bg.fmi.javaweb.sportstournamentorganizer.service.ManagerService;
import bg.fmi.javaweb.sportstournamentorganizer.service.ModeratorService;
import bg.fmi.javaweb.sportstournamentorganizer.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private ManagerService managerService;
    private FollowerService followerService;
    private PlayerService playerService;
    private ModeratorService moderatorService;
    private PasswordEncoder passwordEncoder;

    private JWTGenerator jwtGenerator;

    public AuthController(AuthenticationManager authenticationManager,
                          ManagerService managerService,
                          FollowerService followerService,
                          PlayerService playerService,
                          ModeratorService moderatorService,
                          PasswordEncoder passwordEncoder,
                          JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.managerService = managerService;
        this.followerService = followerService;
        this.playerService = playerService;
        this.moderatorService = moderatorService;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        String username = registerDto.username();
        String email = registerDto.email();

        validateUsername(username);
        validateEmail(email);

        switch (registerDto.role()) {
            case Role.FOLLOWER -> followerService.createFollower(registerDto, passwordEncoder);
            case Role.MANAGER -> managerService.createManager(registerDto, passwordEncoder);

            case Role.MODERATOR -> moderatorService.createModerator(registerDto, passwordEncoder);
            case Role.PLAYER -> playerService.createPlayer(registerDto, passwordEncoder);
        }

        return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto) {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.username(), loginDto.password()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String role = authentication.getAuthorities().stream().findFirst().map(GrantedAuthority::getAuthority).orElse("ADMIN");

            System.out.println(role.getClass());

            String token = jwtGenerator.provideToken(authentication, role);

            System.out.println("Generated token: " + token);
            System.out.println(jwtGenerator.validateToken(token));

            return new ResponseEntity<>(new AuthResponseDto(token, "Bearer "), HttpStatus.OK);
    }

    private void validateUsername(String username) {

        if (followerService.existsByUsername(username)) {
            throw new FollowerAlreadyExistsException(username);
        }

        if (managerService.existsByUsername(username)) {
            throw new ManagerAlreadyExistsException(username);
        }

        if (moderatorService.existsByUsername(username)) {
            throw new ModeratorAlreadyExistsException(username);
        }

        if (playerService.existsByUsername(username)) {
            throw new PlayerAlreadyExistsException(username);
        }
    }

    private void validateEmail(String email) {
        if (followerService.existsByEmail(email)) {
            throw new FollowerAlreadyExistsException(email);
        }

        if (managerService.existsByEmail(email)) {
            throw new ManagerAlreadyExistsException(email);
        }

        if (moderatorService.existsByEmail(email)) {
            throw new ModeratorAlreadyExistsException(email);
        }

        if (playerService.existsByEmail(email)) {
            throw new PlayerAlreadyExistsException(email);
        }
    }
}