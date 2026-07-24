import React, { useState } from 'react';

/**
 * BookingModal Component
 * Implements "Prevent Component Rendering" technique:
 * - If `isOpen` is false, it returns `null` so nothing is rendered to the DOM.
 * - If `isOpen` is true, the modal renders with seat selection and passenger details.
 */
function BookingModal({ isOpen, flight, onClose }) {
  const [passengerName, setPassengerName] = useState('');
  const [passportNumber, setPassportNumber] = useState('');
  const [selectedSeat, setSelectedSeat] = useState(null);
  const [isBooked, setIsBooked] = useState(false);

  // PREVENT COMPONENT RENDERING: Returning null hides the component entirely
  if (!isOpen) {
    return null;
  }

  // Sample seats layout: A=Available, B=Booked
  const seats = [
    { code: '1A', status: 'available' },
    { code: '1B', status: 'booked' },
    { code: '1C', status: 'available' },
    { code: '1D', status: 'available' },
    { code: '2A', status: 'booked' },
    { code: '2B', status: 'available' },
    { code: '2C', status: 'available' },
    { code: '2D', status: 'booked' },
    { code: '3A', status: 'available' },
    { code: '3B', status: 'available' },
    { code: '3C', status: 'available' },
    { code: '3D', status: 'available' },
  ];

  const handleSeatClick = (seat) => {
    if (seat.status === 'booked') return;
    setSelectedSeat(seat.code);
  };

  const handleBookingSubmit = (e) => {
    e.preventDefault();
    if (!passengerName.trim()) {
      alert('Please enter passenger name');
      return;
    }
    if (!passportNumber.trim()) {
      alert('Please enter passport number');
      return;
    }
    if (!selectedSeat) {
      alert('Please select a seat');
      return;
    }
    setIsBooked(true);
  };

  const handleClose = () => {
    // Reset state before closing
    setPassengerName('');
    setPassportNumber('');
    setSelectedSeat(null);
    setIsBooked(false);
    onClose();
  };

  return (
    <div className="modal-overlay" onClick={handleClose} id="booking-modal-overlay">
      <div className="modal-content" onClick={(e) => e.stopPropagation()} id="booking-modal-content">
        <div className="modal-header">
          <h3>
            {isBooked ? '🎉 Booking Successful' : `Book Ticket: ${flight.airline} (${flight.flightNumber})`}
          </h3>
          <button className="modal-close" onClick={handleClose} id="modal-close-btn">&times;</button>
        </div>

        <div className="modal-body">
          {/* Conditional rendering using a ternary operator depending on whether booking is confirmed */}
          {isBooked ? (
            <div className="receipt-container animate-fade-in" id="booking-receipt">
              <div className="receipt">
                <div className="receipt-title">BOARDING PASS</div>
                <div className="receipt-row">
                  <div className="receipt-label">PASSENGER:</div>
                  <div className="receipt-val">{passengerName.toUpperCase()}</div>
                </div>
                <div className="receipt-row">
                  <div className="receipt-label">PASSPORT:</div>
                  <div className="receipt-val">{passportNumber.toUpperCase()}</div>
                </div>
                <div className="receipt-row">
                  <div className="receipt-label">FLIGHT:</div>
                  <div className="receipt-val">{flight.airline} {flight.flightNumber}</div>
                </div>
                <div className="receipt-row">
                  <div className="receipt-label">ROUTE:</div>
                  <div className="receipt-val">{flight.fromCity} ({flight.from}) &rarr; {flight.toCity} ({flight.to})</div>
                </div>
                <div className="receipt-row">
                  <div className="receipt-label">DEPARTURE:</div>
                  <div className="receipt-val">{flight.departure}</div>
                </div>
                <div className="receipt-row">
                  <div className="receipt-label">SEAT:</div>
                  <div className="receipt-val" style={{ color: 'var(--secondary)', fontSize: '1.1rem' }}>{selectedSeat}</div>
                </div>
                <div className="receipt-row">
                  <div className="receipt-label">CLASS:</div>
                  <div className="receipt-val">{flight.classType}</div>
                </div>
                <div className="receipt-row" style={{ marginTop: '1rem', paddingTop: '0.5rem', borderTop: '1px dashed var(--border-glass)' }}>
                  <div className="receipt-label">PRICE PAID:</div>
                  <div className="receipt-val" style={{ color: 'var(--success)' }}>${flight.price}</div>
                </div>
              </div>
              <p style={{ textAlign: 'center', color: 'var(--text-secondary)', fontSize: '0.85rem' }}>
                Your ticket has been confirmed. Have a safe flight!
              </p>
            </div>
          ) : (
            <form onSubmit={handleBookingSubmit} id="booking-form">
              <div className="form-group">
                <label className="form-label">Passenger Full Name</label>
                <input 
                  type="text" 
                  className="form-input" 
                  value={passengerName}
                  onChange={(e) => setPassengerName(e.target.value)}
                  placeholder="Enter passenger name"
                  id="passenger-name-input"
                  required
                />
              </div>

              <div className="form-group">
                <label className="form-label">Passport Number</label>
                <input 
                  type="text" 
                  className="form-input" 
                  value={passportNumber}
                  onChange={(e) => setPassportNumber(e.target.value)}
                  placeholder="Enter passport number"
                  id="passport-number-input"
                  required
                />
              </div>

              <div className="seat-map-container">
                <div className="seat-map-title">Select Your Seat</div>
                <div className="seat-grid">
                  {seats.map((seat) => (
                    <div
                      key={seat.code}
                      id={`seat-${seat.code}`}
                      className={`seat ${
                        seat.status === 'booked' 
                          ? 'booked' 
                          : selectedSeat === seat.code 
                            ? 'selected' 
                            : 'available'
                      }`}
                      onClick={() => handleSeatClick(seat)}
                    >
                      {seat.code}
                    </div>
                  ))}
                </div>

                <div className="seat-legend">
                  <div className="legend-item">
                    <div className="legend-color" style={{ background: 'rgba(99, 102, 241, 0.15)', border: '1px solid rgba(99, 102, 241, 0.3)' }}></div>
                    <span>Available</span>
                  </div>
                  <div className="legend-item">
                    <div className="legend-color" style={{ background: 'var(--secondary)' }}></div>
                    <span>Selected</span>
                  </div>
                  <div className="legend-item">
                    <div className="legend-color" style={{ background: 'rgba(255, 255, 255, 0.05)' }}></div>
                    <span>Booked</span>
                  </div>
                </div>
              </div>

              <button 
                type="submit" 
                className="btn btn-primary" 
                style={{ width: '100%', marginTop: '1rem' }}
                id="submit-booking-btn"
              >
                Confirm Ticket Booking (${flight.price})
              </button>
            </form>
          )}
        </div>

        <div className="modal-footer">
          <button className="btn btn-secondary" onClick={handleClose} id="modal-cancel-btn">
            {isBooked ? 'Close' : 'Cancel'}
          </button>
        </div>
      </div>
    </div>
  );
}

export default BookingModal;
