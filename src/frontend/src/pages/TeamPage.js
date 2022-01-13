import {MatchDetailedCard} from '../component/MatchDetailedCard';
import {MatchSmallCard} from '../component/MatchSmallcard';
import {React, useEffect, useState} from 'react';
import {useParams} from 'react-router-dom';
import {Link} from 'react-router-dom';
import './TeamPage.scss';
import { PieChart } from 'react-minimal-pie-chart';


export const TeamPage = () => {

  const [team, setTeam] = useState({});

  const {teamName} = useParams();


  useEffect(
  () => {

  const getTeamData = async() => {
  const response = await fetch(`/team/teamName/${teamName}`);
  const data = await response.json();

  setTeam(data);

  };
  getTeamData();

  },[teamName]

  );

  if(!team || !team.teamName){
  return (<h2>Team Not Found !!!</h2>)
  }


  return (
    <div className="TeamPage">
       <div className="team-name-section">
          <h1 className="team-name">{team.teamName}</h1>
       </div>

       <div className="win-loss-section">
       Win / Losses
       <PieChart
         data={[
           { title: 'losses', value: team.totalMatches - team.totalWins, color: '#a34d5c' },
           { title: 'wins', value: team.totalWins, color: '#4da375' },
         ]}
       />

       </div>



       <div className="match-detail-section">
          <MatchDetailedCard teamName = {team.teamName} match = {team.latestMatches[0]} />
       </div>

            {team.latestMatches.slice(1).map(matchObject => <MatchSmallCard key={matchObject.id} teamName = {team.teamName} match = {matchObject}/>)}

            <div className="more-link"> { <Link to ={`/team/${teamName}/year/2020`} > More </Link> } </div>

            <div className="home-link"> { <Link to = {`/`}> Home </Link>} </div>

    </div>
  );
}