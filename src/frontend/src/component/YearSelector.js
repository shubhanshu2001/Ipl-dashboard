import {Link} from 'react-router-dom';
import './YearSelector.scss';

export const YearSelector = ({teamName}) => {

const years = [];

  for(var i = 2008; i<=2020; i++){
    years.push(i);
  }

  return (
    <div className="YearSelector">

      {years.map(y => <li key={y} > {<Link to = {`/team/${teamName}/year/${y}`}> {y} </ Link> }</li> )}

    </div>
  );
}