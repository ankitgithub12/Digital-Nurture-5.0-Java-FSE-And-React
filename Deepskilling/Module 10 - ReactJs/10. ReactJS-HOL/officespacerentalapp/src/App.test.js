import { render, screen } from '@testing-library/react';
import App from './App';

test('renders page heading', () => {
  render(<App />);
  const headingElement = screen.getByRole('heading', { name: /LuxSpace Rentals/i });
  expect(headingElement).toBeInTheDocument();
});
