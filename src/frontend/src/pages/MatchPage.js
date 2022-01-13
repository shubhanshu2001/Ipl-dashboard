import {MatchDetailedCard} from '../component/MatchDetailedCard';
import {React, useEffect, useState} from 'react';
import {useParams} from 'react-router-dom';
import './MatchPage.scss';
import {YearSelector} from '../component/YearSelector';
import {Link} from 'react-router-dom';

export const MatchPage = () => {

  const [matches, setMatches] = useState([]);

  const {teamName, year} = useParams();

  useEffect(

  () =>{

      const getMatches = async () => {

          const response = await fetch(`/team/${teamName}?year=${year}`);
          const data = await response.json();

          setMatches(data);

      };

      getMatches();

  },[teamName, year]

  );



  return (
    <div className="MatchPage">

        <div className="year-selector">
            <h3> Select Year</h3>
            <h3> { <YearSelector teamName={teamName}/> } </h3>
        </div>

            <div>
                <h1 className="page-heading">{teamName} matches in {year}</h1>

                {matches.map(m => <MatchDetailedCard key={m.id} teamName={teamName} match={m} />)}
            </div>

            <div className="home-link"> { <Link to = {`/`}> Home </Link>} </div>

    </div>
  );
}