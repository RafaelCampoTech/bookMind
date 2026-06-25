import { useMemo, useState } from "react";
import { Filter, Search } from "lucide-react";
import BookCard, { type Book } from "../components/BookCard";
import "./book-explorer.css";

const BOOKS: Book[] = [
  {
    id: "1",
    title: "The Art of War",
    author: "Sun Tzu",
    meta: "512",
    tags: ["Philosophy", "Military strategy"],
    status: "indexed",
    chunks: 284,
    coverColor: "linear-gradient(145deg, #8b7355 0%, #5c4a32 100%)",
    coverLabel: "Art of War",
  },
  {
    id: "2",
    title: "Meditations",
    author: "Marcus Aurelius",
    meta: "256",
    tags: ["Philosophy"],
    status: "indexed",
    chunks: 312,
    coverColor: "linear-gradient(145deg, #9ca3af 0%, #6b7280 100%)",
    coverLabel: "Meditations",
  },
  {
    id: "3",
    title: "On the Origin of Species",
    author: "Charles Darwin",
    meta: "1859",
    tags: ["Evolution", "Biology"],
    status: "processing",
    coverColor: "linear-gradient(145deg, #84cc16 0%, #4d7c0f 100%)",
    coverLabel: "Origin",
  },
  {
    id: "4",
    title: "The Wealth of Nations",
    author: "Adam Smith",
    meta: "976",
    tags: ["Economics", "Philosophy"],
    status: "indexed",
    chunks: 891,
    coverColor: "linear-gradient(145deg, #d97706 0%, #92400e 100%)",
    coverLabel: "Wealth",
  },
  {
    id: "5",
    title: "Nicomachean Ethics",
    author: "Aristotle",
    meta: "352",
    tags: ["Ethics", "Philosophy"],
    status: "indexed",
    chunks: 402,
    coverColor: "linear-gradient(145deg, #6366f1 0%, #4338ca 100%)",
    coverLabel: "Ethics",
  },
  {
    id: "6",
    title: "The Prince",
    author: "Niccolò Machiavelli",
    meta: "140",
    tags: ["Politics", "Philosophy"],
    status: "processing",
    coverColor: "linear-gradient(145deg, #ef4444 0%, #991b1b 100%)",
    coverLabel: "Prince",
  },
];

export default function BookExplorer() {
  const [query, setQuery] = useState("");
  const [selected, setSelected] = useState<Set<string>>(new Set());

  const filteredBooks = useMemo(() => {
    const term = query.trim().toLowerCase();
    if (!term) return BOOKS;

    return BOOKS.filter(
      (book) =>
        book.title.toLowerCase().includes(term) ||
        book.author.toLowerCase().includes(term) ||
        book.tags.some((tag) => tag.toLowerCase().includes(term))
    );
  }, [query]);

  function toggleSelect(id: string) {
    setSelected((prev) => {
      const next = new Set(prev);
      if (next.has(id)) next.delete(id);
      else next.add(id);
      return next;
    });
  }

  return (
    <div className="book-explorer">
      <div className="book-explorer__header">
        <h3 className="book-explorer__title">Book Explorer</h3>
        <p className="book-explorer__subtitle">
          Discover and ingest books from Open Library.
        </p>
      </div>

      <div className="book-explorer__toolbar">
        <div className="book-explorer__search">
          <Search size={16} className="book-explorer__search-icon" />
          <input
            type="text"
            className="book-explorer__search-input"
            placeholder="Search books by title, author, or subject..."
            value={query}
            onChange={(e) => setQuery(e.target.value)}
          />
        </div>
        <button type="button" className="book-explorer__filter">
          <Filter size={14} />
          Filter
        </button>
      </div>

      <div className="book-explorer__grid">
        {filteredBooks.map((book) => (
          <BookCard
            key={book.id}
            book={book}
            selected={selected.has(book.id)}
            onSelect={toggleSelect}
          />
        ))}
      </div>
    </div>
  );
}
