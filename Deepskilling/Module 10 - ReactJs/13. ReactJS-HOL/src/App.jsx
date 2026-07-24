import { useMemo, useState } from 'react';
import { blogDetails, bookDetails, courseDetails } from './data';

const viewModes = ['ifElse', 'ternary', 'and', 'switch', 'lookup'];
const sections = ['book', 'blog', 'course'];

function BookDetails({ details }) {
  return (
    <article className="card">
      <h3>Book Details</h3>
      <dl>
        <div>
          <dt>Title</dt>
          <dd>{details.title}</dd>
        </div>
        <div>
          <dt>Author</dt>
          <dd>{details.author}</dd>
        </div>
        <div>
          <dt>Genre</dt>
          <dd>{details.genre}</dd>
        </div>
        <div>
          <dt>Rating</dt>
          <dd>{details.rating}</dd>
        </div>
        <div>
          <dt>Pages</dt>
          <dd>{details.pages}</dd>
        </div>
      </dl>
    </article>
  );
}

function BlogCard({ blog, highlighted }) {
  return (
    <section className={highlighted ? 'subcard highlight' : 'subcard'}>
      <h4>{blog.title}</h4>
      <p className="meta">
        {blog.category} by {blog.author}
      </p>
      <p>{blog.summary}</p>
    </section>
  );
}

function BlogDetails({ items, highlighted }) {
  return (
    <article className="card">
      <h3>Blog Details</h3>
      <div className="stack">
        {items.map((blog) => (
          <BlogCard key={blog.id} blog={blog} highlighted={highlighted} />
        ))}
      </div>
    </article>
  );
}

function CourseItem({ course }) {
  return (
    <li className="list-item">
      <strong>{course.name}</strong>
      <span>{course.trainer}</span>
      <span>{course.duration}</span>
    </li>
  );
}

function CourseDetails({ items }) {
  return (
    <article className="card">
      <h3>Course Details</h3>
      <ul className="list">
        {items.map((course) => (
          <CourseItem key={course.id} course={course} />
        ))}
      </ul>
    </article>
  );
}

export default function App() {
  const [section, setSection] = useState('book');
  const [viewMode, setViewMode] = useState('ifElse');
  const [showSummary, setShowSummary] = useState(true);
  const [highlightBlogs, setHighlightBlogs] = useState(true);

  const activeContent = useMemo(() => {
    const contentMap = {
      book: <BookDetails details={bookDetails} />,
      blog: <BlogDetails items={blogDetails} highlighted={highlightBlogs} />,
      course: <CourseDetails items={courseDetails} />
    };

    return contentMap[section];
  }, [section]);

  let renderedByIfElse;
  if (viewMode === 'ifElse') {
    renderedByIfElse = activeContent;
  } else {
    renderedByIfElse = <p className="placeholder">Pick a different rendering mode to see another branch.</p>;
  }

  const renderedByTernary = viewMode === 'ternary' ? activeContent : null;

  const renderedByAnd = viewMode === 'and' && activeContent;

  const renderedBySwitch = (() => {
    switch (viewMode) {
      case 'switch':
        return activeContent;
      default:
        return null;
    }
  })();

  const renderedByLookup = {
    lookup: activeContent
  }[viewMode] || null;

  return (
    <main className="app-shell">
      <section className="hero">
        <p className="eyebrow">React Lab</p>
        <h1>Blogger App</h1>
        <p>
          This app demonstrates conditional rendering, multiple components, list rendering, keys,
          component extraction, and the map() function in one place.
        </p>
      </section>

      <section className="panel controls">
        <h2>Choose a section</h2>
        <div className="button-row">
          {sections.map((item) => (
            <button
              key={item}
              className={section === item ? 'button active' : 'button'}
              onClick={() => setSection(item)}
              type="button"
            >
              {item}
            </button>
          ))}
        </div>

        <h2>Choose a conditional rendering style</h2>
        <div className="button-row">
          {viewModes.map((mode) => (
            <button
              key={mode}
              className={viewMode === mode ? 'button active' : 'button'}
              onClick={() => setViewMode(mode)}
              type="button"
            >
              {mode}
            </button>
          ))}
        </div>

        <div className="toggle-row">
          <label>
            <input
              checked={showSummary}
              onChange={(event) => setShowSummary(event.target.checked)}
              type="checkbox"
            />
            Show summary
          </label>
          <label>
            <input
              checked={highlightBlogs}
              onChange={(event) => setHighlightBlogs(event.target.checked)}
              type="checkbox"
            />
            Highlight blog cards
          </label>
        </div>
      </section>

      <section className="panel explanation">
        <h2>How this lab maps to React concepts</h2>
        {showSummary ? (
          <ul className="bullets">
            <li><strong>Conditional rendering:</strong> the same content can be shown with if/else, ternary, logical &&, switch, and a lookup object.</li>
            <li><strong>Multiple components:</strong> BookDetails, BlogDetails, and CourseDetails are separate components.</li>
            <li><strong>List rendering:</strong> blogs and courses are rendered from arrays.</li>
            <li><strong>Keys:</strong> each mapped item uses a stable key from the data.</li>
            <li><strong>Extracted components:</strong> list items are broken into focused components instead of repeating markup inline.</li>
          </ul>
        ) : (
          <p className="placeholder">Turn on the summary to read the lab notes again.</p>
        )}
      </section>

      <section className="grid">
        <div className={highlightBlogs ? 'highlighted' : ''}>{renderedByIfElse}</div>
        {renderedByTernary}
        {renderedByAnd}
        {renderedBySwitch}
        {renderedByLookup}
      </section>
    </main>
  );
}