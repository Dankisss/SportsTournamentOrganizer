package bg.fmi.javaweb.sportstournamentorganizer.controller;

import bg.fmi.javaweb.sportstournamentorganizer.dto.MatchOutputDto;
import bg.fmi.javaweb.sportstournamentorganizer.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/match")
public class MatchController {

    private final MatchService matchService;


    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

//    @PatchMapping("/set-timer")
//    public ResponseEntity<MatchOutputDto> setTimer(@RequestParam Long moderatorId, @RequestParam Long matchId, @RequestParam boolean isRunning) {
//        return new ResponseEntity<>(matchService.setTimer(moderatorId, matchId, isRunning), HttpStatus.OK);
//    }
}
