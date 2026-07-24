import React from 'react';
import '../Stylesheets/mystyle.css';

function CalculateScore({ Name, School, Total, goal }) {
  // Parse inputs safely
  const parsedTotal = parseFloat(Total);
  const parsedGoal = parseFloat(goal);

  // Calculate the average score
  const average = !isNaN(parsedTotal) && !isNaN(parsedGoal) && parsedGoal !== 0
    ? (parsedTotal / parsedGoal).toFixed(2)
    : '0.00';

  // Determine visual style class based on average
  let ratingClass = 'rating-average';
  let ratingText = 'Average';

  const numAverage = parseFloat(average);
  if (numAverage >= 90) {
    ratingClass = 'rating-excellent';
    ratingText = 'Excellent';
  } else if (numAverage >= 75) {
    ratingClass = 'rating-good';
    ratingText = 'Good';
  } else if (numAverage < 50) {
    ratingClass = 'rating-fail';
    ratingText = 'Needs Improvement';
  }

  return (
    <div className="score-card">
      <div className="card-badge-container">
        <span className={`rating-badge ${ratingClass}`}>{ratingText}</span>
      </div>
      <h2 className="card-title">Student Score Report</h2>
      
      <div className="card-content">
        <div className="detail-row">
          <span className="detail-label">Student Name:</span>
          <span className="detail-value highlight-text">{Name || 'N/A'}</span>
        </div>
        <div className="detail-row">
          <span className="detail-label">School:</span>
          <span className="detail-value">{School || 'N/A'}</span>
        </div>
        <div className="detail-row">
          <span className="detail-label">Total Marks:</span>
          <span className="detail-value score-highlight">{Total !== undefined ? Total : '0'}</span>
        </div>
        <div className="detail-row">
          <span className="detail-label">Goal (Subjects):</span>
          <span className="detail-value score-highlight">{goal !== undefined ? goal : '0'}</span>
        </div>
      </div>

      <div className="divider"></div>

      <div className="average-display-container">
        <span className="average-label">Calculated Average Score</span>
        <div className="average-score-bubble">
          <span className="average-score">{average}</span>
        </div>
        <p className="formula-hint">Formula: Total Marks ({Total}) / Goal ({goal})</p>
      </div>
    </div>
  );
}

export default CalculateScore;
