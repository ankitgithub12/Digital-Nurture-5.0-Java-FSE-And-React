import React, { useState } from 'react';
import './App.css';
import CurrencyConvertor from './CurrencyConvertor';
import ClassEventDemo from './ClassEventDemo';

function App() {
  // 1. Counter and Greeting States
  const [counter, setCounter] = useState(0);
  const [helloMsg, setHelloMsg] = useState('');

  // 2. Greeting Argument State
  const [welcomeMsg, setWelcomeMsg] = useState('');

  // 3. Synthetic Event State
  const [syntheticMsg, setSyntheticMsg] = useState('');
  const [syntheticDetails, setSyntheticDetails] = useState(null);

  // Method A: To increment the counter value
  const incrementCount = () => {
    setCounter((prev) => prev + 1);
  };

  // Method B: Say Hello followed by a static message
  const sayHello = () => {
    setHelloMsg('Hello! Welcome to the React Events hands-on lab.');
  };

  // Multi-method Invocation for "Increment" button click
  const handleIncrementClick = () => {
    console.log('Increment button clicked, invoking multiple methods...');
    incrementCount(); // Method A
    sayHello();       // Method B
  };

  // Decrement handler to decrease value of the counter
  const handleDecrementClick = () => {
    console.log('Decrement button clicked...');
    setCounter((prev) => prev - 1);
  };

  // 2. Function taking "welcome" as an argument
  const handleSayWelcome = (text) => {
    console.log(`handleSayWelcome called with argument: ${text}`);
    setWelcomeMsg(`Successfully received argument: "${text}"! Welcome to the application.`);
  };

  // 3. Synthetic Event "OnPress" Click Handler
  const handleOnPress = (event) => {
    console.log('Synthetic Event "OnPress" invoked...', event);
    setSyntheticMsg('I was clicked');
    
    // Capturing properties of the React SyntheticEvent
    setSyntheticDetails({
      eventType: event.type,
      targetTag: event.target.tagName,
      currentTargetTag: event.currentTarget.tagName,
      isTrusted: event.isTrusted ? 'Yes' : 'No',
      timeStamp: `${event.timeStamp.toFixed(1)} ms`,
      nativeEventClass: event.nativeEvent.constructor.name,
    });
  };

  return (
    <div className="App">
      <header className="app-header">
        <span className="app-badge">Module 10 - Hands-on Lab 11</span>
        <h1>React Event Handling Dashboard</h1>
        <p>Explore React events, Synthetic events, binding context, and currency conversion handlers</p>
      </header>

      <div className="dashboard-grid">
        {/* Card 1: Counter & Multiple Methods */}
        <div className="glass-card">
          <h2 className="card-title">
            <span role="img" aria-label="counter">🔢</span> Counter & Multi-Methods
          </h2>
          <p className="card-description">
            The Increment button invokes multiple methods (to increment value and say hello). The Decrement button decreases the value.
          </p>

          <div className="card-content">
            <div className="counter-display">
              <div className="counter-value">{counter}</div>
              <div className="counter-label">Counter Value</div>
            </div>

            {helloMsg && (
              <div className="notification-box">
                <div className="notification-title">Static Message</div>
                <div className="notification-text">{helloMsg}</div>
              </div>
            )}
          </div>

          <div className="btn-group">
            <button className="btn btn-primary" onClick={handleIncrementClick}>
              Increment
            </button>
            <button className="btn btn-danger" onClick={handleDecrementClick}>
              Decrement
            </button>
          </div>
        </div>

        {/* Card 2: Passing Arguments */}
        <div className="glass-card">
          <h2 className="card-title">
            <span role="img" aria-label="argument">💬</span> Event Arguments
          </h2>
          <p className="card-description">
            Invokes an event handler by passing the string <code>"welcome"</code> as a custom argument.
          </p>

          <div className="card-content">
            {welcomeMsg ? (
              <div className="notification-box success">
                <div className="notification-title">Argument Output</div>
                <div className="notification-text">{welcomeMsg}</div>
              </div>
            ) : (
              <div style={{ textAlign: 'center', color: 'var(--text-secondary)', padding: '1rem' }}>
                Click below to invoke the function with parameters
              </div>
            )}
          </div>

          <div className="btn-group">
            <button className="btn btn-primary" onClick={() => handleSayWelcome('welcome')} style={{ width: '100%' }}>
              Say Welcome
            </button>
          </div>
        </div>

        {/* Card 3: Synthetic Event (OnPress) */}
        <div className="glass-card" style={{ gridColumn: 'span 1' }}>
          <h2 className="card-title">
            <span role="img" aria-label="synthetic">⚡</span> Synthetic Event
          </h2>
          <p className="card-description">
            Invokes event handler simulating standard web click (OnPress). Displays target click feedback and inspects the React <code>SyntheticEvent</code> object.
          </p>

          <div className="card-content">
            {syntheticMsg ? (
              <div className="notification-box success" style={{ marginBottom: '1rem' }}>
                <div className="notification-title">Status</div>
                <div className="notification-text">{syntheticMsg}</div>
              </div>
            ) : (
              <div style={{ textAlign: 'center', color: 'var(--text-secondary)', padding: '0.5rem' }}>
                Trigger event to view the Synthetic Event properties
              </div>
            )}

            {syntheticDetails && (
              <div className="event-table-container">
                <table className="event-table">
                  <thead>
                    <tr>
                      <th>Property</th>
                      <th>Value</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td className="property-name">event.type</td>
                      <td className="property-value">{syntheticDetails.eventType}</td>
                    </tr>
                    <tr>
                      <td className="property-name">event.target.tagName</td>
                      <td className="property-value">{syntheticDetails.targetTag}</td>
                    </tr>
                    <tr>
                      <td className="property-name">event.currentTarget.tagName</td>
                      <td className="property-value">{syntheticDetails.currentTargetTag}</td>
                    </tr>
                    <tr>
                      <td className="property-name">event.isTrusted</td>
                      <td className="property-value">{syntheticDetails.isTrusted}</td>
                    </tr>
                    <tr>
                      <td className="property-name">event.timeStamp</td>
                      <td className="property-value">{syntheticDetails.timeStamp}</td>
                    </tr>
                    <tr>
                      <td className="property-name">event.nativeEvent</td>
                      <td className="property-value">{syntheticDetails.nativeEventClass}</td>
                    </tr>
                  </tbody>
                </table>
              </div>
            )}
          </div>

          <div className="btn-group">
            <button className="btn btn-primary" onClick={handleOnPress} style={{ width: '100%' }}>
              Trigger OnPress Event
            </button>
          </div>
        </div>

        {/* Card 4: Currency Convertor Component */}
        <CurrencyConvertor />

        {/* Card 5: Class Component & Binding */}
        <ClassEventDemo />
      </div>

      <footer className="app-footer">
        <p>ReactJS Hands-On Lab 11 - Event Examples App &copy; 2026. All rights reserved.</p>
      </footer>
    </div>
  );
}

export default App;
