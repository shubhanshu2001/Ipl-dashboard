package com.codezone.Ipldashboard.controller;

import com.codezone.Ipldashboard.models.Match;
import com.codezone.Ipldashboard.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/match")

public class MatchController {

    MatchRepository matchRepository;

    @Autowired
    MatchController(MatchRepository matchRepository){
        this.matchRepository = matchRepository;
    }

    /*@GetMapping("/teamMatches/{teamname}")
    public  List<Match> getMatchesOfTeam(@PathVariable String teamname){
        return matchRepository.getByTeam1OrTeam2OrderByDate(teamname, teamname);
    }*/

}
