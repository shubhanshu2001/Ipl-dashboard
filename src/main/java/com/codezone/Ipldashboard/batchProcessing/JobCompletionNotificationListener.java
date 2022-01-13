package com.codezone.Ipldashboard.batchProcessing;

import com.codezone.Ipldashboard.models.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final JdbcTemplate jdbcTemplate;
    private final EntityManager em;

    @Autowired
    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate, EntityManager em) {
        this.jdbcTemplate = jdbcTemplate;
        this.em = em;
    }


    @Transactional
    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

            //total wins
            List<Object[]> winsList =em.createQuery("Select m.matchWinner, count(*) from Match m group by m.matchWinner", Object[].class).getResultList();

            Map<String, Long> winsMap = new HashMap<>();

            for(int i=0; i<winsList.size(); i++){
                Object[] data = winsList.get(i);

                String teamName = (String) data[0];
                long totalWins = (Long) data[1];

                winsMap.put(teamName, totalWins);
            }

            //total matches from team1

            List<Object[]> team1List =em.createQuery("Select m.team1, count(*) from Match m group by m.team1", Object[].class).getResultList();

            Map<String, Team> teamMap = new HashMap<>();

            for(int i=0; i<team1List.size(); i++){
                Object[] data = team1List.get(i);

                String teamName = (String) data[0];
                long totalMatches = (Long) data[1];

                Team team = new Team();
                team.setTeamName(teamName);
                team.setTotalMatches(totalMatches);
                team.setTotalWins(winsMap.getOrDefault(teamName, 0L));

                teamMap.put(teamName, team);
            }


            //total match from team2

            List<Object[]> team2List =em.createQuery("Select m.team2, count(*) from Match m group by m.team2", Object[].class).getResultList();

            for(int i=0; i<team2List.size(); i++){
                Object[] data = team2List.get(i);

                String teamName = (String) data[0];
                long totalMatches = (Long) data[1];

                if(teamMap.containsKey(teamName)){
                    Team t = teamMap.get(teamName);
                    t.setTotalMatches(t.getTotalMatches() + totalMatches);
                }
                else{
                    Team team = new Team();
                    team.setTeamName(teamName);
                    team.setTotalMatches(totalMatches);
                    team.setTotalWins(winsMap.getOrDefault(teamName, 0L));

                    teamMap.put(teamName, team);
                }
            }

            List<Team> Teamlist = new ArrayList<>(teamMap.values());

            for(int i=0; i<Teamlist.size(); i++){
                em.persist(Teamlist.get(i));
            }

            for(int i=0; i<Teamlist.size(); i++) {
                System.out.println(Teamlist.get(i));
            }



        }
    }
}
