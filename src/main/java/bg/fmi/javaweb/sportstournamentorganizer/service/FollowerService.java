package bg.fmi.javaweb.sportstournamentorganizer.service;

import bg.fmi.javaweb.sportstournamentorganizer.dto.FollowerInputDto;
import bg.fmi.javaweb.sportstournamentorganizer.dto.FollowerOutputDto;
import bg.fmi.javaweb.sportstournamentorganizer.dto.RegisterDto;
import bg.fmi.javaweb.sportstournamentorganizer.dto.TeamOutputDto;
import bg.fmi.javaweb.sportstournamentorganizer.exception.FollowerAlreadyExistsException;
import bg.fmi.javaweb.sportstournamentorganizer.exception.FollowerNotFoundException;
import bg.fmi.javaweb.sportstournamentorganizer.exception.TeamNotFoundException;
import bg.fmi.javaweb.sportstournamentorganizer.mapper.FollowerMapper;
import bg.fmi.javaweb.sportstournamentorganizer.mapper.TeamMapper;
import bg.fmi.javaweb.sportstournamentorganizer.model.users.Follower;
import bg.fmi.javaweb.sportstournamentorganizer.model.Team;
import bg.fmi.javaweb.sportstournamentorganizer.repository.FollowerRepository;
import bg.fmi.javaweb.sportstournamentorganizer.repository.TeamRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowerService {

    private final FollowerRepository followerRepository;

    private final FollowerMapper followerMapper;

    private final TeamRepository teamRepository;

    private final TeamMapper teamMapper;

    public FollowerService(FollowerRepository followerRepository, FollowerMapper followerMapper, TeamRepository teamRepository, TeamMapper teamMapper) {
        this.followerRepository = followerRepository;
        this.followerMapper = followerMapper;
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
    }

    public FollowerOutputDto createFollower(FollowerInputDto followerInputDto) {
        Follower follower = followerMapper.mapFromInputDto(followerInputDto);

        if (existsByEmail(follower.getEmail())) {
            throw new FollowerAlreadyExistsException(follower.getEmail());
        }

        if (existsByUsername(followerInputDto.username())) {
            throw new FollowerAlreadyExistsException(followerInputDto.username());
        }

        return followerMapper.mapToOutputDto(followerRepository.save(follower));
    }

    public void createFollower(RegisterDto registerDto, PasswordEncoder passwordEncoder) {
        Follower follower = new Follower();

        follower.setUsername(registerDto.username());
        follower.setEmail(registerDto.email());
        follower.setPassword(passwordEncoder.encode(registerDto.password()));

        followerRepository.save(follower);
    }

    @Transactional
    public FollowerOutputDto follow(String followerName, String teamName) {
        Follower follower = followerRepository.findByUsername(followerName)
                .orElseThrow(() -> new FollowerNotFoundException(followerName));

        Team team = teamRepository.findByTeamName(teamName)
                .orElseThrow(() -> new TeamNotFoundException(teamName));

        follower.getFollowedTeams().add(team);

        return followerMapper.mapToOutputDto(followerRepository.save(follower));
    }

    public List<TeamOutputDto> getFollowedTeams(Long id) {
        Follower follower = followerRepository.findById(id).
                orElseThrow(() -> new FollowerNotFoundException(id));

        return follower.getFollowedTeams().stream().map(team -> teamMapper.mapToOutputDto(team)).toList();
    }

    public void removeFollower(Long id) {
        followerRepository.deleteById(id);
    }

    public FollowerOutputDto findById(Long id) {

        Follower follower = followerRepository.findById(id).orElseThrow(() -> new FollowerNotFoundException(id));

        return followerMapper.mapToOutputDto(follower);
    }

    public boolean existsByEmail(String email) {
        return followerRepository.existsByEmail(email);
    }

    public boolean existsByUsername(String username) {
        return followerRepository.existsByUsername(username);
    }
//
//    public void updateFollower(Follower follower) {
//        followerRepository.updateFollower(follower);
//    }
//
//    public void updateFollowerName(Integer id, String userName) {
//        followerRepository.updateFollowerName(id, userName);
//    }
//
//    public void updateFollowerEmail(Integer id, String email) {
//        followerRepository.updateFollowerEmail(id, email);
//    }

//    public void updateFollowerPassword(Integer id, String password) {
//        followerRepository.updateFollowerPassword(id, password);
//    }

}

