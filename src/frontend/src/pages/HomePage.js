import {React, useEffect, useState} from 'react';
import {DisplayTeamName} from '../component/DisplayTeamname';

import './HomePage.scss';


export const HomePage = () => {

  const [teamNames, setTeamNames] = useState([]);

    useEffect(

    () =>{

        const getTeamNames = async () => {

            const response = await fetch(`/team/findAll`);
            const data = await response.json();

            setTeamNames(data);
        };
        getTeamNames();
    },[]

    );

  return (
    <div className="HomePage">
        <h1 className="app-name">
            IPL Dashboard
        </h1>

        <div className="team-grid">
            {teamNames.map(tn => <DisplayTeamName key={tn.id} teamNameList ={tn} />)}
        </div>

    </div>
  );
}