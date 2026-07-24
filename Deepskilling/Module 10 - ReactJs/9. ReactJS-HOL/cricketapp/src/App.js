import React, { useState } from 'react';
import ListofPlayers from './ListofPlayers';
import IndianPlayers from './IndianPlayers';

function App() {
  // Use state to manage the flag variable (defaults to true)
  const [flag, setFlag] = useState(true);

  // Implement a simple if-else statement to determine which component to render
  let componentToRender;
  if (flag === true) {
    componentToRender = <ListofPlayers />;
  } else {
    componentToRender = <IndianPlayers />;
  }

  return (
    <div className="app-container">
      {/* Premium Header Banner */}
      <header className="app-header-banner">
        <h1 className="app-title">Cricket App</h1>
        <p className="app-subtitle">ES6 React Hands-On Lab</p>
      </header>

      {/* Control Panel with dynamic Flag Toggle */}
      <div className="control-panel">
        <span className="control-label">View Selection</span>
        <div className="switch-wrapper">
          <span className={`flag-status ${flag ? 'active' : ''}`}>Flag = True</span>
          <label className="switch">
            <input 
              type="checkbox" 
              checked={!flag} 
              onChange={() => setFlag(!flag)} 
              id="flag-toggle"
            />
            <span className="slider"></span>
          </label>
          <span className={`flag-status ${!flag ? 'active' : ''}`}>Flag = False</span>
        </div>
      </div>

      {/* Render the selected component dynamically */}
      {componentToRender}
    </div>
  );
}

export default App;
