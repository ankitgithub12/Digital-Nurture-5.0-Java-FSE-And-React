import React, { useState } from 'react';
import './App.css';
import CalculateScore from './Components/CalculateScore';

function App() {
  // Setup state for interactive input with sample default values
  const [student, setStudent] = useState({
    Name: 'Emma Watson',
    School: 'Hogwarts Academy',
    Total: 475,
    goal: 5
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setStudent(prev => ({
      ...prev,
      [name]: name === 'Total' || name === 'goal' ? (value === '' ? '' : Number(value)) : value
    }));
  };

  return (
    <div className="App">
      <header className="App-header">
        <h1>Student Management Portal</h1>
        <p className="subtitle">Track academic benchmarks and performance goals</p>
      </header>
      
      <main className="app-main-content">
        <div className="app-layout-grid">
          {/* Interactive Input Form */}
          <div className="input-form-card">
            <h3>Enter Student Details</h3>
            <form onSubmit={(e) => e.preventDefault()} className="calculator-form">
              <div className="form-group">
                <label htmlFor="student-name">Student Name</label>
                <input
                  id="student-name"
                  type="text"
                  name="Name"
                  value={student.Name}
                  onChange={handleChange}
                  placeholder="Enter student name"
                />
              </div>
              <div className="form-group">
                <label htmlFor="school-name">School</label>
                <input
                  id="school-name"
                  type="text"
                  name="School"
                  value={student.School}
                  onChange={handleChange}
                  placeholder="Enter school name"
                />
              </div>
              <div className="form-group-row">
                <div className="form-group">
                  <label htmlFor="total-marks">Total Marks</label>
                  <input
                    id="total-marks"
                    type="number"
                    name="Total"
                    value={student.Total}
                    onChange={handleChange}
                    placeholder="e.g. 450"
                    min="0"
                  />
                </div>
                <div className="form-group">
                  <label htmlFor="total-subjects">Goal (Subjects)</label>
                  <input
                    id="total-subjects"
                    type="number"
                    name="goal"
                    value={student.goal}
                    onChange={handleChange}
                    placeholder="e.g. 5"
                    min="1"
                  />
                </div>
              </div>
            </form>
          </div>

          {/* Render the CalculateScore Functional Component */}
          <div className="preview-section">
            <CalculateScore
              Name={student.Name}
              School={student.School}
              Total={student.Total}
              goal={student.goal}
            />
          </div>
        </div>
      </main>
    </div>
  );
}

export default App;
