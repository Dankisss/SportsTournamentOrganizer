package bg.fmi.javaweb.sportstournamentorganizer.controller;


import bg.fmi.javaweb.sportstournamentorganizer.dto.*;
import bg.fmi.javaweb.sportstournamentorganizer.service.ManagerService;
import jakarta.validation.constraints.NotEmpty;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


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

    @GetMapping("/show-details")
    public ResponseEntity<ManagerOutputDto> showDetails(Principal principal) {
        ManagerOutputDto managerOutputDto = managerService.findManagerByUsernameToDto(principal.getName());

        return new ResponseEntity<>(managerOutputDto, HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<ManagerOutputDto> getManagerByUsername(@PathVariable @NotEmpty String username) {
        ManagerOutputDto managerOutputDto = managerService.findManagerByUsernameToDto(username);

        return new ResponseEntity<>(managerOutputDto, HttpStatus.OK);
    }

    @GetMapping("/get-team")
    public ResponseEntity<TeamOutputDto> getTeam(Principal principal) {
        return new ResponseEntity<>(managerService.findTeamByManagerUsername(principal.getName()), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteManager(Principal principal) {
        managerService.removeManager(principal.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/add-team")
    public ResponseEntity<ManagerOutputDto> addTeam(Principal principal, @RequestBody @Validated TeamInputDto teamInputDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return new ResponseEntity<>(managerService.addTeam(principal.getName(), teamInputDto), HttpStatus.OK);
    }

    @PatchMapping("/add-player")
    public ResponseEntity<TeamOutputDto> addPlayerToTeam(Principal principal, @RequestParam @Validated String player) {
        return new ResponseEntity<>(managerService.addPlayerToTeam(getUsername(principal), player), HttpStatus.OK);
    }

    @PatchMapping("/sign-team-to-tournament")
    public ResponseEntity<TournamentOutputDto> signTeamToTournament(Principal principal, @RequestParam @NotEmpty String tournamentName) {
        return new ResponseEntity<>(managerService.addTeamToTournament(getUsername(principal), tournamentName), HttpStatus.OK);
    }

    private String getUsername(@NotNull Principal principal) {
        return principal.getName();
    }
}
