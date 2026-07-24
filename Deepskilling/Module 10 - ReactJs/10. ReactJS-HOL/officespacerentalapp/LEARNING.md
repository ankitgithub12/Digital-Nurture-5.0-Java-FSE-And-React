# ReactJS Concepts Guide - Office Space Rental App

This document covers the core theoretical objectives of this hands-on lab.

---

## 1. What is JSX?
**JSX** stands for **JavaScript XML**. It is a syntax extension to JavaScript used with React to describe what the user interface (UI) should look like. JSX may look like HTML, but it has the full power of JavaScript.

* **Key features of JSX:**
  * It allows writing HTML-like tags directly inside JavaScript files.
  * Web browsers cannot read JSX directly. It must be transpiled into standard JavaScript (using tools like Babel) before being executed.
  * It helps prevent Cross-Site Scripting (XSS) attacks by escaping values before rendering them.

---

## 2. What is ECMAScript?
**ECMAScript (ES)** is a standard script-language specification standardized by Ecma International. JavaScript is the most popular implementation of ECMAScript.
* **Significance in React:** Modern React development heavily relies on ES6 (ECMAScript 2015) and subsequent versions. Key ES features used in React include:
  * **Classes & Arrow Functions:** Used to define components and event handlers.
  * **Modules (import/export):** Used to share components across files.
  * **Destructuring, Spread, and Rest Operators:** Used to pass and extract props and state values cleanly.
  * **Variables & Block scope (`let`, `const`).**

---

## 3. Explain `React.createElement()`
Behind the scenes, JSX elements are syntactic sugar for calling `React.createElement()`.
* **Syntax:** `React.createElement(type, [props], [...children])`
  * `type`: Can be an HTML tag name (like `'div'`, `'h1'`) or a React component.
  * `props`: An object containing attributes/properties passed to the element (like `{ className: 'container' }`).
  * `children`: Nested elements or text node content inside the element.

* **Example comparison:**
  ```javascript
  // JSX Syntax
  const element = <h1 className="title">Hello World</h1>;

  // Transpiled JavaScript
  const element = React.createElement('h1', { className: 'title' }, 'Hello World');
  ```

---

## 4. How to Create React Nodes with JSX
To create React nodes using JSX, you write standard tag-like elements inside your components.
* Elements must be closed. A self-closing tag like `<img />` or `<br />` is required.
* Adjacent JSX elements must be wrapped in a single parent tag or a React Fragment (`<>...</>`).
* Attribute names use **camelCase** (e.g., `className` instead of `class`, `onClick` instead of `onclick`, `tabIndex` instead of `tabindex`).

* **Example:**
  ```jsx
  const officeNode = (
    <div className="card">
      <h2>Premium Office Suite</h2>
      <p>Rent: $55,000</p>
    </div>
  );
  ```

---

## 5. How to Render JSX to the DOM
To display React elements in the browser, they must be rendered to the DOM using **React DOM**.
* In modern React (v18 and v19), we use `createRoot` from the `react-dom/client` package.
* We select a target DOM container (typically a `div` with `id="root"` in `index.html`) and render our root React element into it.

* **Example (`src/index.js`):**
  ```javascript
  import React from 'react';
  import { createRoot } from 'react-dom/client';
  import App from './App';

  const container = document.getElementById('root');
  const root = createRoot(container);
  root.render(
    <React.StrictMode>
      <App />
    </React.StrictMode>
  );
  ```

---

## 6. How to Use JavaScript Expressions in JSX
You can embed any valid JavaScript expression in JSX by wrapping it in curly braces `{}`. This enables dynamic rendering of data.
* **Examples of expressions:**
  * Variables and object properties: `{office.name}`
  * Function calls: `{formatCurrency(office.rent)}`
  * Arithmetic operations: `{2 + 2}`
  * Conditional (ternary) operators: `{office.rent < 60000 ? 'Budget' : 'Premium'}`
  * Looping/Mapping arrays: `{officeList.map(item => <Card key={item.id} data={item} />)}`

---

## 7. How to Use Inline CSS in JSX
In JSX, inline styles are written not as strings, but as JavaScript **objects**.
* The property names of the style object are written in **camelCase** (e.g., `backgroundColor` instead of `background-color`, `fontSize` instead of `font-size`).
* **Syntax:** `style={{ propertyName: value }}` (the outer curly braces embed the JavaScript expression, and the inner curly braces represent the style object).

* **Example of Dynamic CSS:**
  ```jsx
  const rentStyle = {
    color: office.rent < 60000 ? 'red' : 'green',
    fontWeight: 'bold'
  };

  return <span style={rentStyle}>Rent: {office.rent}</span>;
  ```
