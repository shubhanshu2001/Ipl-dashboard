package com.codezone.Ipldashboard.repository;

import com.codezone.Ipldashboard.models.Match;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    List<Match> getByTeam1OrTeam2OrderByDateDesc(String team1, String team2, Pageable pageable);


    @Query("select m from Match m where (m.team1 = :teamName or m.team2 = :teamName) AND m.date between :startDate and :endDate order by m.date desc")
    List<Match> getByTeamInYear(@Param("teamName") String team, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    //List<Match> getByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(String team1, LocalDate StartDate, LocalDate EndDate, String team2, LocalDate startDate, LocalDate endDate);
}
