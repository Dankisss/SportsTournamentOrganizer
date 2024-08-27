package bg.fmi.javaweb.sportstournamentorganizer.controller;

import bg.fmi.javaweb.sportstournamentorganizer.dto.FollowerInputDto;
import bg.fmi.javaweb.sportstournamentorganizer.dto.FollowerOutputDto;
import bg.fmi.javaweb.sportstournamentorganizer.dto.TeamOutputDto;
import bg.fmi.javaweb.sportstournamentorganizer.service.FollowerService;
import jakarta.validation.constraints.NotEmpty;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/follower")
public class FollowerController {


    private final FollowerService followerService;

    public FollowerController(FollowerService followerService) {
        this.followerService = followerService;
    }


    @PostMapping("/create")
    public ResponseEntity<FollowerOutputDto> createFollower(@RequestBody @Validated FollowerInputDto followerInputDto) {

        return new ResponseEntity<>(followerService.createFollower(followerInputDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FollowerOutputDto> getFollower(@PathVariable @NotNull Long id) {
        return new ResponseEntity<>(followerService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FollowerOutputDto> deleteFollower(@PathVariable @NotNull Long id) {
        followerService.removeFollower(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/follow")
    public ResponseEntity<FollowerOutputDto> followTeam(@RequestParam @NotEmpty String followerName, @RequestParam @NotEmpty String teamName) {
        return new ResponseEntity<>(followerService.follow(followerName, teamName), HttpStatus.OK);
    }

    @GetMapping("/followed-teams")
    public ResponseEntity<List<TeamOutputDto>> getFollowedTeams(@RequestParam @NotNull Long id) {
        return new ResponseEntity<>(followerService.getFollowedTeams(id), HttpStatus.OK);

    }

}

