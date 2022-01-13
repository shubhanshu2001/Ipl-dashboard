import {Link} from 'react-router-dom';
import './MatchSmallCard.scss';

export const MatchSmallCard = ({match, teamName}) => {

  const otherTeam = match.team1 === teamName ? match.team2 : match.team1;

  const otherTeamPath = `/team/${otherTeam}`;

  const isMatchWon = teamName === match.matchWinner;

  return (
    <div className= { isMatchWon? "MatchSmallCard won-card" : "MatchSmallCard lost-card"} >

      <span>vs</span>
      <h3> { <Link to = {otherTeamPath} > {otherTeam} </Link> }</h3>
      <h4 className="match-result">{match.matchWinner} Won by {match.resultMargin} {match.result} </h4>
    </div>
  );
}