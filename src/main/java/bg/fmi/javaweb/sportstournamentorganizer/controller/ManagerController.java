package bg.fmi.javaweb.sportstournamentorganizer.controller;


import bg.fmi.javaweb.sportstournamentorganizer.dto.*;
import bg.fmi.javaweb.sportstournamentorganizer.service.ManagerService;
import jakarta.validation.constraints.NotEmpty;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/manager")
public class ManagerController {

    private final ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @PostMapping
    public ResponseEntity<ManagerOutputDto> createManager(@RequestBody @Validated ManagerInputDto managerInputDto) {

        return new ResponseEntity<>(managerService.addManager(managerInputDto), HttpStatus.CREATED);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ManagerOutputDto> getManager(@PathVariable @NotNull Long id) {
        ManagerOutputDto managerOutputDto = managerService.findById(id);

        return new ResponseEntity<>(managerOutputDto, HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<ManagerOutputDto> getManagerByUsername(@PathVariable @NotEmpty String username) {
        ManagerOutputDto managerOutputDto = managerService.getManagerByUsername(username);

        return new ResponseEntity<>(managerOutputDto, HttpStatus.OK);
    }

    @GetMapping("/{id}/team")
    public ResponseEntity<TeamOutputDto> findTeamByManagerId(@PathVariable @NotNull Long id) {
        return new ResponseEntity<>(managerService.findTeamByManagerId(id), HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteManager(@PathVariable @NotNull Long id) {
        managerService.removeManager(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/add-team")
    public ResponseEntity<ManagerOutputDto> addTeam(@RequestParam @NotNull Long id, @RequestBody @Validated TeamInputDto teamInputDto) {

        return new ResponseEntity<>(managerService.addTeam(id, teamInputDto), HttpStatus.OK);
    }

    @PatchMapping("/add-player")
    public ResponseEntity<TeamOutputDto> addPlayerToTeam(@RequestParam @NotNull Long id, @RequestParam @Validated String player) {
        return new ResponseEntity<>(managerService.addPlayerToTeam(id, player), HttpStatus.OK);
    }

    @PatchMapping("/add-team-to-tournament")
    public ResponseEntity<TournamentOutputDto> addTeamToTournament(@RequestParam @NotNull Long managerId, @RequestParam @NotEmpty String tournamentName) {
        return new ResponseEntity<>(managerService.addTeamToTournament(managerId, tournamentName), HttpStatus.OK);
    }
}
