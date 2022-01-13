package com.codezone.Ipldashboard.repository;


import com.codezone.Ipldashboard.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    public Team findByTeamName(String name);

    public List<Team> findByTotalWinsLessThanOrderByTotalMatches(long lessthan);

    public List<Team> findAll();

}
