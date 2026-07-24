import React from 'react';
import './App.css';

// 1. Single Featured Office Object (to display details like Name, Rent, Address, and Image attribute)
const featuredOffice = {
  name: "Skyline Premium Suite",
  rent: 75000,
  address: "Block A, 14th Floor, DLF Cyber City, Sector 25, Gurugram",
  image: `${process.env.PUBLIC_URL}/office_space.png`, // Using the generated image path
  capacity: "15 - 20 Seats",
  type: "Private Executive Office",
  amenities: ["High-speed Wi-Fi", "Conference Room Access", "Complimentary Beverages", "24/7 Access"]
};

// 2. List of Office Objects (to loop through and display more data)
const officesList = [
  {
    id: 1,
    name: "Co-Working Premium Desk",
    rent: 25000,
    address: "Level 2, Wave Silver Tower, Sector 18, Noida",
    image: "https://images.unsplash.com/photo-1527192491265-7e15c55b1ed2?auto=format&fit=crop&w=600&q=80",
    capacity: "1 Seat (Hot Desk)",
    type: "Co-Working space",
    amenities: ["Ergonomic Chair", "Unlimited Coffee", "High-speed Internet"]
  },
  {
    id: 2,
    name: "Dedicated Creative Studio",
    rent: 55000,
    address: "4th Floor, Tech Hub Building, Salt Lake, Kolkata",
    image: "https://images.unsplash.com/photo-1497366216548-37526070297c?auto=format&fit=crop&w=600&q=80",
    capacity: "4 - 6 Seats",
    type: "Dedicated Desk Area",
    amenities: ["Whiteboard Wall", "Dual Monitors", "Lounge Access"]
  },
  {
    id: 3,
    name: "Enterprise Tech Suite",
    rent: 145000,
    address: "Tower C, Prestige Tech Park, Outer Ring Road, Bengaluru",
    image: "https://images.unsplash.com/photo-1497215728101-856f4ea42174?auto=format&fit=crop&w=600&q=80",
    capacity: "25 - 30 Seats",
    type: "Private Enterprise Office",
    amenities: ["Dedicated Server Room", "Private Meeting Room", "Custom Branding"]
  },
  {
    id: 4,
    name: "Boutique Executive Office",
    rent: 59000,
    address: "Suite 302, Maker Chambers VI, Nariman Point, Mumbai",
    image: "https://images.unsplash.com/photo-1556761175-5973dc0f32e7?auto=format&fit=crop&w=600&q=80",
    capacity: "5 Seats",
    type: "Private Office",
    amenities: ["Receptionist Desk", "Premium Furniture", "Ocean View"]
  },
  {
    id: 5,
    name: "Collaborative Innovation Lab",
    rent: 85000,
    address: "Level 11, Signature Towers, Bandra Kurla Complex, Mumbai",
    image: "https://images.unsplash.com/photo-1497366811353-6870744d04b2?auto=format&fit=crop&w=600&q=80",
    capacity: "12 Seats",
    type: "Meeting & Innovation Hub",
    amenities: ["Smart Projector", "Flexible Layout", "Snack Bar"]
  }
];

function App() {
  // Helper to determine inline CSS for rent based on the requirement:
  // Red if rent is below 60000, Green if it's above 60000 (or equal)
  const getRentStyle = (rent) => {
    return {
      color: rent < 60000 ? '#ef4444' : '#10b981', // Elegant Red / Green colors
      fontWeight: '700',
      fontSize: '1.4rem'
    };
  };

  return (
    <div className="app-container">
      {/* 3. Heading element */}
      <header className="app-header">
        <div className="logo-wrapper">
          <span className="logo-icon">🏢</span>
          <h1 className="main-title">LuxSpace Rentals</h1>
        </div>
        <p className="subtitle">Find your next premium office space with state-of-the-art amenities</p>
      </header>

      <main className="app-content">
        {/* 4. Single Featured Office Display */}
        <section className="featured-section">
          <h2 className="section-title">✨ Featured Office Space</h2>
          <div className="featured-card">
            <div className="featured-image-container">
              {/* Image using React/JSX Attribute */}
              <img 
                src={featuredOffice.image} 
                alt={featuredOffice.name} 
                className="featured-image"
              />
              <span className="badge badge-featured">Featured Room</span>
            </div>
            
            <div className="featured-details">
              <span className="office-type">{featuredOffice.type}</span>
              <h3>{featuredOffice.name}</h3>
              <p className="office-address">📍 {featuredOffice.address}</p>
              
              <div className="office-meta">
                <div className="meta-item">
                  <span className="meta-label">Capacity</span>
                  <span className="meta-value">{featuredOffice.capacity}</span>
                </div>
                <div className="meta-item">
                  <span className="meta-label">Rent (Monthly)</span>
                  {/* Inline CSS styling based on Rent value */}
                  <span className="meta-value" style={getRentStyle(featuredOffice.rent)}>
                    ₹{featuredOffice.rent.toLocaleString('en-IN')}
                  </span>
                </div>
              </div>

              <div className="amenities-section">
                <h4>Included Amenities</h4>
                <div className="amenities-grid">
                  {featuredOffice.amenities.map((amenity, index) => (
                    <span key={index} className="amenity-tag">✓ {amenity}</span>
                  ))}
                </div>
              </div>
            </div>
          </div>
        </section>

        {/* 5. Loop through list of Object to display more data */}
        <section className="listings-section">
          <h2 className="section-title">📋 Available Office Spaces</h2>
          <div className="listings-grid">
            {officesList.map((office) => (
              <div key={office.id} className="office-card">
                <div className="card-image-container">
                  <img src={office.image} alt={office.name} className="card-image" />
                  <span className="badge badge-capacity">{office.capacity}</span>
                </div>
                <div className="card-content">
                  <span className="card-office-type">{office.type}</span>
                  <h3 className="card-title">{office.name}</h3>
                  <p className="card-address">📍 {office.address}</p>
                  
                  <div className="card-footer">
                    <div className="price-block">
                      <span className="price-label">Monthly Rent</span>
                      {/* Inline CSS styling applied here too */}
                      <span style={getRentStyle(office.rent)}>
                        ₹{office.rent.toLocaleString('en-IN')}
                      </span>
                    </div>
                    <button className="btn-details">Book Tour</button>
                  </div>
                </div>
              </div>
            ))}
          </div>
        </section>
      </main>

      <footer className="app-footer">
        <p>© 2026 LuxSpace Rentals. Powered by React JSX & ES6.</p>
      </footer>
    </div>
  );
}

export default App;
