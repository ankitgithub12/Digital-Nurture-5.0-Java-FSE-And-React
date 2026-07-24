import React from 'react';

/**
 * FlightCard Component
 * Displays specific flight details.
 * Implements Conditional Rendering to check user login status:
 * - If logged in, displays active "Book Ticket" button.
 * - If guest, displays disabled/locked button prompting the user to login.
 */
function FlightCard({ flight, isLoggedIn, onBook }) {
  const { id, airline, flightNumber, from, to, fromCity, toCity, duration, departure, price, classType } = flight;

  // Conditional Rendering using an Element Variable for the action button
  let bookingActionButton;

  if (isLoggedIn) {
    bookingActionButton = (
      <button 
        className="btn btn-primary btn-book-action"
        onClick={() => onBook(flight)}
        id={`book-btn-${id}`}
      >
        Select Seats & Book
      </button>
    );
  } else {
    bookingActionButton = (
      <button 
        className="btn btn-locked" 
        disabled
        title="Please login to book tickets"
        id={`locked-btn-${id}`}
      >
        <span>🔒 Log In to Book</span>
      </button>
    );
  }

  return (
    <div className="glass-card flight-card" id={`flight-card-${id}`}>
      <div className="flight-header">
        <div className="airline-info">
          <span>✈ {airline}</span>
          <span className="flight-number">{flightNumber}</span>
        </div>
        <span className="flight-class-badge">{classType}</span>
      </div>

      <div className="flight-route">
        <div className="route-point">
          <div className="route-code">{from}</div>
          <div className="route-city">{fromCity}</div>
        </div>
        
        <div className="route-path">
          <div className="path-line"></div>
          <div className="path-duration">{duration}</div>
        </div>

        <div className="route-point">
          <div className="route-code">{to}</div>
          <div className="route-city">{toCity}</div>
        </div>
      </div>

      <div className="flight-meta">
        <div className="meta-item">
          <span className="meta-label">Departure</span>
          <span className="meta-value">{departure}</span>
        </div>
        <div className="meta-item" style={{ textAlign: 'right' }}>
          <span className="meta-label">Ticket Price</span>
          <span className="meta-price">${price}</span>
        </div>
      </div>

      {/* Rendering the element variable containing the conditional button */}
      <div className="flight-action">
        {bookingActionButton}
      </div>
    </div>
  );
}

export default FlightCard;
