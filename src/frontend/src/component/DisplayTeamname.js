import {Link} from 'react-router-dom';
import './DisplayTeamName.scss';

export const DisplayTeamName = ({teamNameList}) => {


  const TeamPath = `/team/${teamNameList.teamName}`;

  return (
    <div className="DisplayTeamName">
      <h1> { <Link to = {TeamPath} > {teamNameList.teamName} </Link> } </h1>
    </div>
  );
}