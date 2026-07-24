import React, { useState } from 'react';
import FlightCard from './components/FlightCard';
import BookingModal from './components/BookingModal';

// Mock Flight Database
const FLIGHTS_DATA = [
  {
    id: 1,
    airline: 'Stellar Airways',
    flightNumber: 'ST-402',
    from: 'JFK',
    to: 'LAX',
    fromCity: 'New York',
    toCity: 'Los Angeles',
    duration: '6h 15m',
    departure: '08:30 AM',
    price: 320,
    classType: 'Economy'
  },
  {
    id: 2,
    airline: 'Aero Nebula',
    flightNumber: 'AN-881',
    from: 'LHR',
    to: 'HND',
    fromCity: 'London',
    toCity: 'Tokyo',
    duration: '11h 45m',
    departure: '01:15 PM',
    price: 940,
    classType: 'Business'
  },
  {
    id: 3,
    airline: 'Quantum Jet',
    flightNumber: 'QJ-109',
    from: 'DXB',
    to: 'CDG',
    fromCity: 'Dubai',
    toCity: 'Paris',
    duration: '7h 30m',
    departure: '09:00 PM',
    price: 580,
    classType: 'Economy'
  },
  {
    id: 4,
    airline: 'Horizon Express',
    flightNumber: 'HX-332',
    from: 'SIN',
    to: 'SYD',
    fromCity: 'Singapore',
    toCity: 'Sydney',
    duration: '8h 10m',
    departure: '11:45 PM',
    price: 450,
    classType: 'Economy'
  }
];

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [selectedFlight, setSelectedFlight] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);

  const handleLogin = () => {
    setIsLoggedIn(true);
  };

  const handleLogout = () => {
    setIsLoggedIn(false);
    setSelectedFlight(null);
    setIsModalOpen(false);
  };

  const handleOpenBooking = (flight) => {
    setSelectedFlight(flight);
    setIsModalOpen(true);
  };

  const handleCloseBooking = () => {
    setIsModalOpen(false);
    setSelectedFlight(null);
  };

  // CONDITIONAL RENDERING: Render different page components based on login status.
  // We'll create elements for Guest Page and User Page and render them conditionally.
  
  const guestPage = (
    <div className="guest-page animate-fade-in" id="guest-page">
      <div className="hero">
        <div className="hero-tagline">Welcome Explorer</div>
        <h1 className="hero-title">Discover Your Next Horizon</h1>
        <p className="hero-subtitle">
          Browse real-time global flight details, schedules, and ticket pricing. Log in to reserve your seats and book tickets.
        </p>
        <button className="btn btn-primary" onClick={handleLogin} id="hero-login-btn">
          Log In to Book Tickets
        </button>
      </div>

      <div className="container">
        <h2 className="section-title">
          <span>✈</span> Available Flight Schedules (Guest View)
        </h2>
        <div className="flights-grid">
          {FLIGHTS_DATA.map(flight => (
            <FlightCard 
              key={flight.id} 
              flight={flight} 
              isLoggedIn={isLoggedIn} 
              onBook={handleOpenBooking}
            />
          ))}
        </div>
      </div>
    </div>
  );

  const userPage = (
    <div className="user-page animate-fade-in" id="user-page">
      <div className="hero" style={{ padding: '3rem 0 1.5rem' }}>
        <div className="hero-tagline" style={{ color: 'var(--success)' }}>Active Member Portal</div>
        <h1 className="hero-title" style={{ fontSize: '2.5rem' }}>Welcome Back, Captain!</h1>
        <p className="hero-subtitle" style={{ marginBottom: '1rem' }}>
          Select any flight below to book tickets, select premium cabin seats, and view your digital boarding pass instantly.
        </p>
        <div style={{ display: 'flex', justifyContent: 'center', gap: '2rem', marginTop: '1.5rem' }}>
          <div className="user-badge">
            <span style={{ color: 'var(--text-secondary)' }}>Loyalty Tier:</span>
            <strong style={{ color: 'var(--secondary)' }}>Quantum Gold</strong>
          </div>
          <div className="user-badge">
            <span style={{ color: 'var(--text-secondary)' }}>Miles Balance:</span>
            <strong style={{ color: 'var(--primary)' }}>48,500 pts</strong>
          </div>
        </div>
      </div>

      <div className="container">
        <h2 className="section-title">
          <span style={{ color: 'var(--success)' }}>✓</span> Book Flight Tickets (User View)
        </h2>
        <div className="flights-grid">
          {FLIGHTS_DATA.map(flight => (
            <FlightCard 
              key={flight.id} 
              flight={flight} 
              isLoggedIn={isLoggedIn} 
              onBook={handleOpenBooking}
            />
          ))}
        </div>
      </div>
    </div>
  );

  return (
    <div className="app-root">
      <header className="header-glass">
        <div className="container header-container">
          <div className="brand" onClick={handleLogout}>
            <span className="brand-icon">🚀</span>
            <span>NebulaPass</span>
          </div>

          <div style={{ display: 'flex', alignItems: 'center', gap: '1.25rem' }}>
            {/* Conditional status badge using ternary operator */}
            <div className="user-badge" id="user-status-badge">
              <span className={`badge-dot ${isLoggedIn ? 'logged-in' : 'guest'}`}></span>
              <span>{isLoggedIn ? 'User Session' : 'Guest Mode'}</span>
            </div>

            {/* Conditional Button rendering using ternary operator */}
            {isLoggedIn ? (
              <button className="btn btn-danger" onClick={handleLogout} id="auth-logout-btn">
                Log Out
              </button>
            ) : (
              <button className="btn btn-primary" onClick={handleLogin} id="auth-login-btn">
                Log In
              </button>
            )}
          </div>
        </div>
      </header>

      <main>
        {/* Render page elements conditionally based on login state */}
        {isLoggedIn ? userPage : guestPage}
      </main>

      {/* Booking Modal is hidden/shown by preventing rendering internally (when selectedFlight is null or isModalOpen is false) */}
      <BookingModal 
        isOpen={isModalOpen} 
        flight={selectedFlight} 
        onClose={handleCloseBooking} 
      />

      <footer style={{ 
        textAlign: 'center', 
        padding: '3rem 1.5rem', 
        color: 'var(--text-muted)', 
        fontSize: '0.85rem',
        borderTop: '1px solid rgba(255, 255, 255, 0.03)',
        marginTop: '4rem',
        background: 'rgba(0, 0, 0, 0.2)'
      }}>
        <p>&copy; 2026 NebulaPass Inc. Built with React Conditional Rendering & Element Variables.</p>
      </footer>
    </div>
  );
}

export default App;
