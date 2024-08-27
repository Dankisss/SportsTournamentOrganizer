package bg.fmi.javaweb.sportstournamentorganizer.controller;


import bg.fmi.javaweb.sportstournamentorganizer.dto.*;
import bg.fmi.javaweb.sportstournamentorganizer.service.ModeratorService;
import jakarta.validation.constraints.NotEmpty;
import org.apache.catalina.connector.Response;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moderator")
public class ModeratorController {

    private final ModeratorService moderatorService;

    public ModeratorController(ModeratorService moderatorService) {
        this.moderatorService = moderatorService;
    }

    @PostMapping("/create")
    public ResponseEntity<ModeratorOutputDto> createModerator(@RequestBody @Validated ModeratorInputDto moderatorInputDto) {
        ModeratorOutputDto moderatorOutputDto = moderatorService.addModerator(moderatorInputDto);
        return new ResponseEntity<>(moderatorOutputDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ModeratorOutputDto> getModerator(@RequestParam @NotNull Long id) {
        return new ResponseEntity<>(moderatorService.getModeratorById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ModeratorOutputDto> deleteModerator(@RequestParam @NotNull Long id) {

           moderatorService.removeModerator(id);
           return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/add-tournament")
    public ResponseEntity<TournamentOutputDto> createTournament(@RequestParam @NotNull Long id, @RequestBody @Validated TournamentInputDto tournamentInputDto) {
        return new ResponseEntity<>(moderatorService.createTournament(id, tournamentInputDto), HttpStatus.OK);
    }

    @GetMapping("/all-tournaments")
    public ResponseEntity<List<TournamentOutputDto>> getAllTournaments(@RequestParam @NotNull Long id) {
        return new ResponseEntity<>(moderatorService.getAllTournaments(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete-tournament/{tournamentName}")
    public ResponseEntity<List<TournamentOutputDto>> deleteTournament(@RequestParam @NotNull Long moderatorId, @PathVariable @NotEmpty String tournamentName) {
        return new ResponseEntity<>(moderatorService.deleteTournament(moderatorId, tournamentName), HttpStatus.OK);
    }

    @PatchMapping("/add-match")
    public ResponseEntity<TournamentOutputDto> addMatch(@RequestParam @NotNull Long moderatorId,
                                                        @RequestParam @NotNull Long tournamentId,
                                                        @RequestBody @Validated MatchInputDto matchInputDto) {
        return new ResponseEntity<>(moderatorService.addMatch(moderatorId, tournamentId, matchInputDto), HttpStatus.OK);
    }

    @PatchMapping("/update-match-result")
    public ResponseEntity<MatchOutputDto> updateMatchResult(@RequestParam @NotNull Long moderatorId, @RequestParam @NotNull Long matchId, @RequestParam @NotNull boolean isHost) {
        return new ResponseEntity<>(moderatorService.updateMatchResult(moderatorId, matchId, isHost), HttpStatus.OK);
    }
//    @PatchMapping("/add-match")
//    public ResponseEntity<TournamentOutputDto> addMatch(@RequestParam Long moderatorId, @RequestBody MatchIn)
}
