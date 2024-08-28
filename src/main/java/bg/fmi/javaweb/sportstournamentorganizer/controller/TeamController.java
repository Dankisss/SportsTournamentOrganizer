package bg.fmi.javaweb.sportstournamentorganizer.controller;

import bg.fmi.javaweb.sportstournamentorganizer.dto.PlayerInputDto;
import bg.fmi.javaweb.sportstournamentorganizer.dto.TeamInputDto;
import bg.fmi.javaweb.sportstournamentorganizer.dto.TeamOutputDto;
import bg.fmi.javaweb.sportstournamentorganizer.service.TeamService;
import jakarta.validation.constraints.NotEmpty;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/team")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping("/add")
    public ResponseEntity<TeamOutputDto> addTeam(@RequestBody @Validated TeamInputDto teamInputDto) {
            return new ResponseEntity<>(teamService.addTeam(teamInputDto), HttpStatus.OK);
    }

    @GetMapping("/get-team")
    public ResponseEntity<TeamOutputDto> findById(@RequestParam @NotNull Long id) {
        return new ResponseEntity<>(teamService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/teamName/{teamName}")
    public ResponseEntity<TeamOutputDto> findByTeamName(@PathVariable @NotNull String teamName) {
        return new ResponseEntity<>(teamService.findByTeamNameToDto(teamName), HttpStatus.OK);
    }

    @PatchMapping("/addPlayer")
    public ResponseEntity<TeamOutputDto> addPlayer(@RequestParam @NotNull Long id, @RequestBody @Validated PlayerInputDto playerInputDto) {
        return new ResponseEntity<>(teamService.addPlayer(id, playerInputDto), HttpStatus.OK);
    }

//    @GetMapping("/{id}/manager")
//    public ResponseEntity<ManagerOutputDto> getManagerById(@PathVariable Long id) {
//        return new ResponseEntity<>(teamService.findById(id), HttpStatus.OK);
//    }
}
