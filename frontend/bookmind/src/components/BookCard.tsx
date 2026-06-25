import { Check, Eye, Loader2, Play } from "lucide-react";
import "./BookCard.css";

export type BookStatus = "indexed" | "processing";

export type Book = {
  id: string;
  title: string;
  author: string;
  meta: string;
  tags: string[];
  status: BookStatus;
  chunks?: number;
  coverColor: string;
  coverLabel: string;
};

type BookCardProps = {
  book: Book;
  selected: boolean;
  onSelect: (id: string) => void;
};

export default function BookCard({ book, selected, onSelect }: BookCardProps) {
  return (
    <article className="book-card">
      <div className="book-card__body">
        <input
          type="checkbox"
          className="book-card__checkbox"
          checked={selected}
          onChange={() => onSelect(book.id)}
          aria-label={`Select ${book.title}`}
        />

        <div
          className="book-card__cover"
          style={{ background: book.coverColor }}
          aria-hidden="true"
        >
          <span>{book.coverLabel}</span>
        </div>

        <div className="book-card__info">
          <h4 className="book-card__title">{book.title}</h4>
          <p className="book-card__author">{book.author}</p>
          <p className="book-card__meta">{book.meta}</p>
          <div className="book-card__tags">
            {book.tags.map((tag) => (
              <span key={tag} className="book-card__tag">
                {tag}
              </span>
            ))}
          </div>
        </div>
      </div>

      <footer className="book-card__footer">
        {book.status === "indexed" ? (
          <span className="book-card__status book-card__status--indexed">
            <Check size={12} />
            Indexed · {book.chunks} chunks
          </span>
        ) : (
          <span className="book-card__status book-card__status--processing">
            <Loader2 size={12} className="book-card__spinner" />
            Processing
          </span>
        )}

        <div className="book-card__actions">
          <button type="button" className="book-card__view" aria-label={`View ${book.title}`}>
            <Eye size={14} />
          </button>
          {book.status === "indexed" && (
            <button type="button" className="book-card__process">
              <Play size={12} />
              Process
            </button>
          )}
        </div>
      </footer>
    </article>
  );
}
