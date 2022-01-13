package com.codezone.Ipldashboard.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class MatchInput {
    public int id;
    public String city;
    public String date;
    public String player_of_match;
    public String venue;
    public String neutral_venue;
    public String team1;
    public String team2;
    public String toss_winner;
    public String toss_decision;
    public String winner;
    public String result;
    public String result_margin;
    public String eliminator;
    public String method;
    public String umpire1;
    public String umpire2;
}
