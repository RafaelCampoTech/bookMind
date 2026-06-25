import "./Sidebar.css"
import { Layers } from "lucide-react";
import { NavLink } from "react-router";


export default function Sidebar() {
    return (
        <aside className="sidebar">
            {/* Header */}
            <div className="sidebar-header">
                <div className="sidebar-logo">
                    <Layers size={14} color="white" />
                </div>
                <span className="sidebar-title">Atlas</span>

            </div>
            {/* Navigation */}
            <nav className="sidebar-nav">
                <NavLink to="/" end className="sidebar-item">
                    <span>Dashboard</span>
                </NavLink>
                <NavLink to="/books" className="sidebar-item">
                    <span>Book Explorer</span>
                </NavLink>
                <NavLink to="/search" className="sidebar-item">
                    <span>Semantic Search</span>
                </NavLink>
            </nav>
        </aside>

    );
}