package com.codezone.Ipldashboard.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name ="match")

public class Match {
    @Id
    public long id;
    public String city;
    public LocalDate date;
    public String manOfTheMatch;
    public String venue;
    public String team1;
    public String team2;
    public String tossWinner;
    public String tossDecision;
    public String matchWinner;
    public String result;
    public String resultMargin;
    public String umpire1;
    public String umpire2;
}
