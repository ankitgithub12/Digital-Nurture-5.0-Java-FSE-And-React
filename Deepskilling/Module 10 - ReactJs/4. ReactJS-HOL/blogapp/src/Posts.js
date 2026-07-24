import React, { Component } from 'react';
import Post from './Post';

// A child component to render a single post.
// We use this to demonstrate componentDidCatch error handling, as React's componentDidCatch
// catches errors thrown by child components during their rendering phase.
class PostItem extends Component {
  render() {
    const { post, shouldThrowError } = this.props;

    if (shouldThrowError) {
      throw new Error('Simulated rendering error in PostItem!');
    }

    return (
      <div className="post-card">
        <h3>{post.title}</h3>
        <p>{post.body}</p>
      </div>
    );
  }
}

class Posts extends Component {
  constructor(props) {
    super(props);
    // Initialize the component state with a list of posts
    this.state = {
      posts: [],
      hasError: false,
      errorMsg: '',
      simulateError: false
    };

    // Bind methods
    this.loadPosts = this.loadPosts.bind(this);
    this.triggerSimulatedError = this.triggerSimulatedError.bind(this);
  }

  // Fetch API method to load posts
  loadPosts() {
    fetch('https://jsonplaceholder.typicode.com/posts')
      .then(response => {
        if (!response.ok) {
          throw new Error('Failed to fetch posts: ' + response.statusText);
        }
        return response.json();
      })
      .then(data => {
        // Map fetched JSON items to instances of Post class
        const postInstances = data.map(
          item => new Post(item.userId, item.id, item.title, item.body)
        );
        this.setState({ posts: postInstances });
      })
      .catch(error => {
        console.error('Error fetching posts:', error);
        // Note: Catching async fetch errors usually does not trigger componentDidCatch.
        // We set local state error or rethrow if needed.
        this.setState({ hasError: true, errorMsg: error.message });
      });
  }

  // Component lifecycle: componentDidMount
  componentDidMount() {
    this.loadPosts();
  }

  // Component lifecycle: componentDidCatch
  componentDidCatch(error, info) {
    // Display the error happening in child component as an alert message
    alert('componentDidCatch triggered!\nError Message: ' + error.message);
    this.setState({
      hasError: true,
      errorMsg: error.message
    });
  }

  triggerSimulatedError() {
    this.setState({ simulateError: true });
  }

  render() {
    if (this.state.hasError) {
      return (
        <div className="error-container">
          <h2>Something went wrong.</h2>
          <p className="error-message">{this.state.errorMsg}</p>
          <button className="btn btn-secondary" onClick={() => window.location.reload()}>
            Reload Page
          </button>
        </div>
      );
    }

    return (
      <div className="posts-container">
        <header className="posts-header">
          <h1>Blog Posts</h1>
          <div className="controls">
            <button className="btn btn-danger" onClick={this.triggerSimulatedError}>
              Simulate Render Error (Test componentDidCatch)
            </button>
          </div>
        </header>

        <div className="posts-grid">
          {this.state.posts.length > 0 ? (
            this.state.posts.map(post => (
              <PostItem
                key={post.id}
                post={post}
                shouldThrowError={this.state.simulateError && post.id === this.state.posts[0].id}
              />
            ))
          ) : (
            <div className="loading">Loading posts...</div>
          )}
        </div>
      </div>
    );
  }
}

export default Posts;
