package com.codezone.Ipldashboard.controller;

import com.codezone.Ipldashboard.models.Match;
import com.codezone.Ipldashboard.models.Team;
import com.codezone.Ipldashboard.repository.MatchRepository;
import com.codezone.Ipldashboard.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/team")

public class TeamController {

    TeamRepository teamRepository;
    MatchRepository matchRepository;

    @Autowired
    TeamController(TeamRepository teamRepository, MatchRepository matchRepository){
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    @GetMapping("/teamName/{teamname}")
    public Team getByTeamName(@PathVariable String teamname){
        Team team =  teamRepository.findByTeamName(teamname);

        PageRequest p = PageRequest.of(0, 4);
        List<Match> matches = matchRepository.getByTeam1OrTeam2OrderByDateDesc(teamname, teamname, p);
        team.setLatestMatches(matches);
        return team;

    }

    @GetMapping("/wins")
    public List<Team> getByTeamWins(@RequestParam("lessthan") long lessthan){
        return teamRepository.findByTotalWinsLessThanOrderByTotalMatches(lessthan);
    }

    @GetMapping("/findAll")
    public List<Team> findAllTeams(){
        return teamRepository.findAll();
    }



    @GetMapping("/{teamName}")
    public List<Match> GetMatchesForTeamInYear(@PathVariable String teamName, @RequestParam("year") int year){

        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year + 1, 1, 1);

        return matchRepository.getByTeamInYear(teamName, startDate, endDate);

        //return matchRepository.getByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(teamName, startDate, endDate, teamName, startDate, endDate);
    }


}
