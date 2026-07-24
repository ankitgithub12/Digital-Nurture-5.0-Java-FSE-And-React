import React from 'react';

function ListofPlayers() {
  // Declare an array with 11 players containing their names and scores
  const players = [
    { name: "Virat Kohli", score: 112 },
    { name: "Rohit Sharma", score: 85 },
    { name: "KL Rahul", score: 45 },
    { name: "Shikhar Dhawan", score: 62 },
    { name: "MS Dhoni", score: 95 },
    { name: "Hardik Pandya", score: 78 },
    { name: "Ravindra Jadeja", score: 55 },
    { name: "Jasprit Bumrah", score: 18 },
    { name: "Rishabh Pant", score: 82 },
    { name: "Mohammed Shami", score: 12 },
    { name: "Shreyas Iyer", score: 68 }
  ];

  // Filter the players with scores below 70 using an arrow function
  const playersBelow70 = players.filter(player => player.score < 70);

  return (
    <div className="view-container">
      <div className="section-title-wrapper">
        <h2 className="section-title">List of <span>Players</span></h2>
        <p className="section-desc">Showing overall team players and those with scores below 70</p>
      </div>

      <div className="listofplayers-sections">
        {/* Section 1: All Players using map() */}
        <div>
          <div className="sub-section-header">
            <h3 className="sub-section-title">All Registered Players</h3>
            <span className="sub-section-count">{players.length} Players</span>
          </div>
          <div className="players-grid">
            {players.map((player, index) => {
              // Destructure player object (ES6 feature)
              const { name, score } = player;
              const isLowScore = score < 70;
              
              return (
                <div key={index} className={`player-card ${isLowScore ? 'danger' : ''}`}>
                  <div className="player-card-header">
                    <span className="player-index">#{index + 1}</span>
                    <span className={`player-badge ${isLowScore ? 'low' : 'high'}`}>
                      {isLowScore ? 'Below 70' : '70+ Club'}
                    </span>
                  </div>
                  <div className="player-info">
                    <h4 className="player-name">{name}</h4>
                    <div className="player-score-container">
                      <span className="player-score-label">Runs Scored:</span>
                      <span className="player-score-value">{score}</span>
                    </div>
                  </div>
                </div>
              );
            })}
          </div>
        </div>

        {/* Section 2: Players with score below 70 using filter() and arrow functions */}
        <div>
          <div className="sub-section-header">
            <h3 className="sub-section-title danger">Filtered: Scores Below 70</h3>
            <span className="sub-section-count">{playersBelow70.length} Players</span>
          </div>
          <div className="players-grid">
            {playersBelow70.map((player, index) => {
              const { name, score } = player;
              return (
                <div key={index} className="player-card danger">
                  <div className="player-card-header">
                    <span className="player-index">#{index + 1}</span>
                    <span className="player-badge low">Below 70</span>
                  </div>
                  <div className="player-info">
                    <h4 className="player-name">{name}</h4>
                    <div className="player-score-container">
                      <span className="player-score-label">Runs Scored:</span>
                      <span className="player-score-value">{score}</span>
                    </div>
                  </div>
                </div>
              );
            })}
          </div>
        </div>
      </div>
    </div>
  );
}

export default ListofPlayers;
