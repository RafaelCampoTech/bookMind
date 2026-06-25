import "./index.css";
import BookExplorer from "./pages/book-explorer";
import Dashboard from "./pages/dashboard";
import SemanticSearch from "./pages/semantic-search";
import AppLayout from "./layouts/AppLayout";
import { Route, Routes } from "react-router";
import { BrowserRouter } from "react-router";

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<AppLayout />}>
          <Route index element={<Dashboard />} />
          <Route path="books" element={<BookExplorer />} />
          <Route path="search" element={<SemanticSearch />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}