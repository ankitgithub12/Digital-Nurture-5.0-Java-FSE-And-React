import { render, screen } from '@testing-library/react';
import App from './App';

test('renders app header brand and guest view by default', () => {
  render(<App />);
  const brandElements = screen.getAllByText(/NebulaPass/i);
  expect(brandElements.length).toBeGreaterThan(0);
  
  const guestBadge = screen.getByText(/Guest Mode/i);
  expect(guestBadge).toBeInTheDocument();

  const titleElement = screen.getByText(/Available Flight Schedules/i);
  expect(titleElement).toBeInTheDocument();
});
