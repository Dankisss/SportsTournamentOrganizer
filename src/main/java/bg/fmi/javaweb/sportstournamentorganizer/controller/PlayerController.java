package bg.fmi.javaweb.sportstournamentorganizer.controller;


import bg.fmi.javaweb.sportstournamentorganizer.dto.PlayerInputDto;
import bg.fmi.javaweb.sportstournamentorganizer.dto.PlayerOutputDto;
import bg.fmi.javaweb.sportstournamentorganizer.service.PlayerService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<PlayerOutputDto> getPlayerById(@PathVariable @NotNull Long id) {
        return new ResponseEntity<>(playerService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/username")
    public ResponseEntity<PlayerOutputDto> getPLayerByUsername(@RequestParam @NotNull String username) {
        return new ResponseEntity<>(playerService.findByUsernameAsDto(username), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PlayerOutputDto>> findAll() {

        return new ResponseEntity<>(playerService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<PlayerOutputDto> createFollower(@RequestBody @Validated PlayerInputDto playerInputDto) {

        return new ResponseEntity<>(playerService.createPlayer(playerInputDto), HttpStatus.OK);
    }

    @PatchMapping("/{id}/change-name/{username}")
    public ResponseEntity<PlayerOutputDto> updateTeam(@PathVariable @NotNull Long id, @PathVariable @NotEmpty String username) {
        return new ResponseEntity<>(playerService.updateUsername(id, username), HttpStatus.OK);
    }


}
