package com.codezone.Ipldashboard.processor;

import com.codezone.Ipldashboard.models.Match;
import com.codezone.Ipldashboard.models.MatchInput;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;

public class MatchItemProcessor implements ItemProcessor<MatchInput, Match> {

    @Override
    public Match process(final MatchInput matchInput) throws Exception {

        Match match = new Match();
        match.setId(matchInput.getId());
        match.setCity(matchInput.getCity());

        LocalDate ld = LocalDate.parse(matchInput.getDate());
        match.setDate(ld);

        match.setManOfTheMatch(matchInput.getPlayer_of_match());
        match.setVenue(matchInput.getVenue());

        match.setTossWinner(matchInput.getToss_winner());
        match.setTossDecision(matchInput.getToss_decision());
        match.setResult(matchInput.getResult());
        match.setMatchWinner(matchInput.getWinner());
        match.setResultMargin(matchInput.getResult_margin());

        match.setUmpire1(matchInput.getUmpire1());
        match.setUmpire2(matchInput.getUmpire2());

        String FirstInningsTeam="";
        String SeconfInningsTeam="";

        if(matchInput.getToss_decision().equals("bat")){
            FirstInningsTeam= matchInput.getToss_winner();

            if(FirstInningsTeam.equals(matchInput.getTeam1())){
                SeconfInningsTeam = matchInput.getTeam2();
            }
            else{
                SeconfInningsTeam = matchInput.getTeam1();
            }
        }
        else{
            SeconfInningsTeam= matchInput.getToss_winner();

            if(SeconfInningsTeam.equals(matchInput.getTeam1())){
                FirstInningsTeam = matchInput.getTeam2();
            }
            else{
                FirstInningsTeam = matchInput.getTeam1();
            }
        }

        match.setTeam1(FirstInningsTeam);
        match.setTeam2(SeconfInningsTeam);

        return match;
    }

}


