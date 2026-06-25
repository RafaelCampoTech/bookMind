import { BookOpen, Database, Layers, Zap } from "lucide-react";
import StatCard from "../components/StatCard";
import  "./dashboard.css"

export default function Dashboard() {
  return (
    <div className="dashboard">
      <div className="dashboard__header">
        <h3 className="dashboard__title">Knowledge Ingestion Platform</h3>
        <p className="dashboard__subtitle">Build a searchable knowledge base from public books.</p>
      </div>

      <div className="statcard-grid">
        <StatCard label="Books Indexed" value="24" sub="3 in progress" icon={BookOpen} trend="+3 this week" />
        <StatCard label="Total Chunks" value="10,412" sub="across all books" icon={Layers} trend="+1,284 this week" />
        <StatCard label="Embeddings" value="10,412" sub="1536-dim vectors" icon={Database} trend="+1,284 this week" />
        <StatCard label="Searches" value="1,847" sub="avg 48ms latency" icon={Zap} trend="+142 today" />
      </div>
  </div>

  );
}