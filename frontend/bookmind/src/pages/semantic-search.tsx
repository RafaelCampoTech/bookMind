import { useState } from "react";
import { Search, SlidersHorizontal, Zap } from "lucide-react";
import "./semantic-search.css";

const SUGGESTED_QUERIES = [
  "What is strategic leadership?",
  "How does evolution work?",
  "Explain the invisible hand",
  "What is Stoic virtue?",
];

export default function SemanticSearch() {
  const [query, setQuery] = useState("");

  return (
    <div className="semantic-search">
      <div className="semantic-search__top">
        <div className="semantic-search__header">
          <h3 className="semantic-search__title">Semantic Search</h3>
          <p className="semantic-search__subtitle">
            Ask questions across your indexed knowledge base.
          </p>
        </div>

        <div className="semantic-search__bar">
          <div className="semantic-search__input-wrap">
            <Search size={16} className="semantic-search__input-icon" />
            <input
              type="text"
              className="semantic-search__input"
              placeholder="Ask a question about your indexed books..."
              value={query}
              onChange={(e) => setQuery(e.target.value)}
            />
          </div>
          <button type="button" className="semantic-search__submit">
            <Zap size={14} />
            Search
          </button>
          <button
            type="button"
            className="semantic-search__filter"
            aria-label="Search filters"
          >
            <SlidersHorizontal size={16} />
          </button>
        </div>
      </div>

      <div className="semantic-search__empty">
        <Search size={48} className="semantic-search__empty-icon" strokeWidth={1.25} />
        <h4 className="semantic-search__empty-title">Search your knowledge base</h4>
        <p className="semantic-search__empty-text">
          Enter a question or concept to find semantically similar passages.
        </p>

        <div className="semantic-search__suggestions">
          {SUGGESTED_QUERIES.map((suggestion) => (
            <button
              key={suggestion}
              type="button"
              className="semantic-search__suggestion"
              onClick={() => setQuery(suggestion)}
            >
              {suggestion}
            </button>
          ))}
        </div>
      </div>
    </div>
  );
}
