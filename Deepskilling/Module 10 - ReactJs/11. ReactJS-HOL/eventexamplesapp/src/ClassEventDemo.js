import React, { Component } from 'react';

class ClassEventDemo extends Component {
  constructor(props) {
    super(props);
    this.state = {
      clicks: 0,
      bindingMethod: 'None clicked yet',
    };

    // Method 1: Binding in the constructor (Traditional way)
    this.handleConstructorClick = this.handleConstructorClick.bind(this);
  }

  // Method 1 Handler: Needs binding in the constructor to access "this"
  handleConstructorClick() {
    this.setState((prevState) => ({
      clicks: prevState.clicks + 1,
      bindingMethod: 'Constructor Binding (.bind(this))',
    }));
  }

  // Method 2 Handler: Arrow Function as a Class Property (Recommended)
  // Arrow functions inherit "this" context lexically, so no constructor binding is needed
  handleArrowClick = () => {
    this.setState((prevState) => ({
      clicks: prevState.clicks + 1,
      bindingMethod: 'Arrow Function Class Property (= () => {})',
    }));
  };

  // Method 3 Handler: Called inline via arrow function in render
  handleRenderClick() {
    this.setState((prevState) => ({
      clicks: prevState.clicks + 1,
      bindingMethod: 'Arrow Function in Render Callback (() => this.method())',
    }));
  }

  render() {
    return (
      <div className="glass-card class-card">
        <h2 className="card-title">
          <span role="img" aria-label="class">🏫</span> Class Component & "this" Keyword
        </h2>
        <p className="card-description">
          Demonstrates how the <code>this</code> keyword is used and bound in Class Component event handlers.
        </p>

        <div className="counter-display">
          <div className="counter-value">{this.state.clicks}</div>
          <div className="counter-label">Total Method Calls</div>
        </div>

        <div className="notification-box" style={{ background: 'rgba(245, 158, 11, 0.1)', borderColor: 'rgba(245, 158, 11, 0.2)' }}>
          <div className="notification-title" style={{ color: '#fbbf24' }}>Last Used Binding Method</div>
          <div className="notification-text">{this.state.bindingMethod}</div>
        </div>

        <div className="btn-group" style={{ display: 'flex', flexDirection: 'column', gap: '0.75rem', marginTop: '1.25rem' }}>
          <button className="btn btn-secondary" onClick={this.handleConstructorClick}>
            1. Constructor Bind (.bind(this))
          </button>
          
          <button className="btn btn-secondary" onClick={this.handleArrowClick}>
            2. Arrow Class Property (= () => {})
          </button>
          
          <button className="btn btn-secondary" onClick={() => this.handleRenderClick()}>
            3. Inline Arrow Call (() => fn())
          </button>
        </div>
      </div>
    );
  }
}

export default ClassEventDemo;
