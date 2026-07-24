import React from 'react';

function IndianPlayers() {
  // 1. Array of players for Destructuring
  const allIndianPlayers = [
    "Virat Kohli",      // Player 1 (Odd)
    "Rohit Sharma",     // Player 2 (Even)
    "Jasprit Bumrah",   // Player 3 (Odd)
    "Rishabh Pant",     // Player 4 (Even)
    "Hardik Pandya",    // Player 5 (Odd)
    "Ravindra Jadeja",  // Player 6 (Even)
    "KL Rahul",         // Player 7 (Odd)
    "Shikhar Dhawan",   // Player 8 (Even)
    "Mohammed Shami",   // Player 9 (Odd)
    "Shreyas Iyer",     // Player 10 (Even)
    "Yuzvendra Chahal"  // Player 11 (Odd)
  ];

  // Destructure the array elements into separate player variables (ES6 Array Destructuring)
  const [
    player1,
    player2,
    player3,
    player4,
    player5,
    player6,
    player7,
    player8,
    player9,
    player10,
    player11
  ] = allIndianPlayers;

  // Form Odd Team and Even Team from the destructured variables
  const oddTeam = [player1, player3, player5, player7, player9, player11];
  const evenTeam = [player2, player4, player6, player8, player10];

  // 2. Declare T20 and Ranji Trophy Player Arrays
  const T20players = ["Suryakumar Yadav", "Ishan Kishan", "Sanju Samson", "Ruturaj Gaikwad"];
  const RanjiTrophyPlayers = ["Sarfaraz Khan", "Yashasvi Jaiswal", "Abhimanyu Easwaran", "Shahbaz Nadeem"];

  // Merge the two arrays using the ES6 Spread / Merge Feature
  const mergedPlayers = [...T20players, ...RanjiTrophyPlayers];

  return (
    <div className="view-container">
      <div className="section-title-wrapper">
        <h2 className="section-title">Indian <span>Players</span></h2>
        <p className="section-desc">Demonstrating ES6 Array Destructuring and Spread Operator merging</p>
      </div>

      <div className="indian-players-container">
        {/* Requirement a: Odd and Even Teams via Destructuring */}
        <div className="team-split-grid">
          {/* Odd Team */}
          <div className="team-column">
            <h3 className="team-column-title">
              Odd Team Players
              <span className="team-column-badge">Odd Position</span>
            </h3>
            <div className="team-player-list">
              {oddTeam.map((name, index) => (
                <div key={index} className="team-player-item">
                  <span className="team-player-num">#{index * 2 + 1}</span>
                  <span className="team-player-name">{name}</span>
                  <span className="team-player-detail">Destructured</span>
                </div>
              ))}
            </div>
          </div>

          {/* Even Team */}
          <div className="team-column">
            <h3 className="team-column-title">
              Even Team Players
              <span className="team-column-badge even">Even Position</span>
            </h3>
            <div className="team-player-list">
              {evenTeam.map((name, index) => (
                <div key={index} className="team-player-item">
                  <span className="team-player-num">#{index * 2 + 2}</span>
                  <span className="team-player-name">{name}</span>
                  <span className="team-player-detail">Destructured</span>
                </div>
              ))}
            </div>
          </div>
        </div>

        {/* Requirement b: Merge T20 and Ranji Trophy arrays */}
        <div className="merged-section">
          <div className="sub-section-header">
            <h3 className="sub-section-title">Merged Squad (T20 & Ranji Trophy)</h3>
            <span className="sub-section-count">{mergedPlayers.length} Total Players</span>
          </div>
          <p style={{ color: 'var(--text-muted)', marginBottom: '1.5rem', fontSize: '0.95rem' }}>
            Combined using the ES6 spread operator (<code>[...T20players, ...RanjiTrophyPlayers]</code>)
          </p>

          <div className="merged-players-grid">
            {mergedPlayers.map((name, index) => {
              // Determine if the player originally belonged to T20 or Ranji array
              const isT20 = T20players.includes(name);
              return (
                <div key={index} className="merged-player-card">
                  <span className="merged-player-name">{name}</span>
                  <span className={`merged-source-badge ${isT20 ? 't20' : 'ranji'}`}>
                    {isT20 ? 'T20 Squad' : 'Ranji Trophy'}
                  </span>
                </div>
              );
            })}
          </div>
        </div>
      </div>
    </div>
  );
}

export default IndianPlayers;
