import { Outlet, useLocation } from "react-router";
import "../App.css";
import Sidebar from "./Sidebar"
import TopBar from "./Topbar";


type Page = "dashboard" | "books" | "jobs" | "search" | "analytics" | "settings";



export default function AppLayout() {

    const {pathname} = useLocation();
    const segment = pathname.split("/").filter(Boolean)[0] ?? "dashboard";
    const page = segment as Page;




    return (
        <div className="app">
            <Sidebar />
            <div className="app__content">
                <TopBar page={page} />
                <main className="app__main">
                    <Outlet />
                </main>
            </div>
        </div>



     );
}