import React, { useState } from 'react';

function CurrencyConvertor() {
  const [amount, setAmount] = useState('');
  const [conversionType, setConversionType] = useState('INR_TO_EUR');
  const [result, setResult] = useState(null);

  // Conversion rate: 1 EUR = 90.0 INR (Static exchange rate for the lab)
  const EXCHANGE_RATE = 90.0;

  const performConversion = () => {
    const value = parseFloat(amount);
    if (isNaN(value) || value <= 0) {
      setResult({ error: 'Please enter a valid positive number.' });
      return;
    }

    if (conversionType === 'INR_TO_EUR') {
      const converted = value / EXCHANGE_RATE;
      setResult({
        original: `${value.toFixed(2)} INR`,
        converted: `${converted.toFixed(2)} EUR`,
        rate: `1 EUR = ${EXCHANGE_RATE} INR`,
      });
    } else {
      const converted = value * EXCHANGE_RATE;
      setResult({
        original: `${value.toFixed(2)} EUR`,
        converted: `${converted.toFixed(2)} INR`,
        rate: `1 EUR = ${EXCHANGE_RATE} INR`,
      });
    }
  };

  const handleSubmit = (event) => {
    // Prevent standard form submission reload
    event.preventDefault();
    performConversion();
  };

  const handleButtonClick = (event) => {
    // Invoke handleSubmit manually on click to satisfy the lab requirement
    console.log('Convert button clicked, invoking handleSubmit...');
    handleSubmit(event);
  };

  return (
    <div className="glass-card currency-card">
      <h2 className="card-title">
        <span role="img" aria-label="currency">💱</span> Currency Convertor
      </h2>
      <p className="card-description">
        Convert Indian Rupees to Euros or vice versa. Form submission is handled by invoking the submit event handler from the click event.
      </p>

      <form onSubmit={handleSubmit} className="card-content">
        <div className="form-group">
          <label className="form-label" htmlFor="conversion-select">
            Conversion Direction
          </label>
          <select
            id="conversion-select"
            className="form-control form-select"
            value={conversionType}
            onChange={(e) => {
              setConversionType(e.target.value);
              setResult(null);
            }}
          >
            <option value="INR_TO_EUR">Indian Rupees (INR) to Euro (EUR)</option>
            <option value="EUR_TO_INR">Euro (EUR) to Indian Rupees (INR)</option>
          </select>
        </div>

        <div className="form-group">
          <label className="form-label" htmlFor="currency-amount">
            Amount
          </label>
          <div className="input-container">
            <span className="input-prefix">
              {conversionType === 'INR_TO_EUR' ? '₹' : '€'}
            </span>
            <input
              id="currency-amount"
              type="number"
              step="any"
              className="form-control"
              placeholder="Enter amount"
              value={amount}
              onChange={(e) => setAmount(e.target.value)}
              required
            />
          </div>
        </div>

        <div className="btn-group">
          <button
            type="button"
            className="btn btn-success"
            onClick={handleButtonClick}
            style={{ width: '100%' }}
          >
            Convert Currency
          </button>
        </div>
      </form>

      {result && (
        <div className="conversion-result-box">
          {result.error ? (
            <div style={{ color: '#ef4444', fontWeight: 600 }}>{result.error}</div>
          ) : (
            <>
              <div className="conversion-rate">Exchange Rate: {result.rate}</div>
              <div className="conversion-value">
                {result.original} = <span style={{ color: '#10b981' }}>{result.converted}</span>
              </div>
            </>
          )}
        </div>
      )}
    </div>
  );
}

export default CurrencyConvertor;
