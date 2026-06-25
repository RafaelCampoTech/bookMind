import { ChevronRight } from "lucide-react";
import "./TopBar.css";

type Page = "dashboard" | "books" | "jobs" | "search" | "analytics" | "settings";


export default function TopBar({ page }: { page: Page }) {

    const labels: Record<Page, string> = {
        dashboard: "Dashboard",
        books: "Book Explorer",
        jobs: "Ingestion Jobs",
        search: "Semantic Search",
        analytics: "Analytics",
        settings: "Settings",
    };

    return (
        <header className="topbar">
            <div className="topbar-left">
                <span className="topbar-brand">Atlas</span>

                <ChevronRight size={14} className="topbar-icon-muted" />

                <span className="topbar-title">{labels[page]}</span>
            </div>
        </header>
    );
};