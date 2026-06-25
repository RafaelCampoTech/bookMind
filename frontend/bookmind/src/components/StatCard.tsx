import type { ComponentType } from "react";
import type { LucideProps } from "lucide-react";
import { ArrowUpRight, ArrowDownRight } from "lucide-react";
import "./StatCard.css";

type StatCardProps = {
  label: string;
  value: string;
  sub?: string;
  icon?: ComponentType<LucideProps>;
  trend?: string;
};

function StatCard({
  label,
  value,
  sub,
  icon: Icon,
  trend,
}: StatCardProps) {
  const isPositive = trend?.startsWith("+");

  return (
    <div className="stat-card">
      <div className="stat-card__header">
        <span className="stat-card__label">{label}</span>
        <div className="stat-card__icon">
          {Icon && <Icon size={15} />}
        </div>
      </div>

      <div className="stat-card__body">
        <div className="stat-card__value">{value}</div>
        {sub && <div className="stat-card__sub">{sub}</div>}
      </div>

      {trend && (
        <div
          className={`stat-card__trend ${
            isPositive ? "stat-card__trend--positive" : "stat-card__trend--negative"
          }`}
        >
          {isPositive ? <ArrowUpRight size={12} /> : <ArrowDownRight size={12} />}
          {trend}
        </div>
      )}
    </div>
  );
}

export default StatCard;
