import * as React from 'react';

type StatCardProps = {
  label: string;
  value: string;
  sub?: string;
  icon?: React.ReactElement;
  trend?: string;
};

function StatCard({
  label,
  value,
  sub,
  icon,
  trend,
}: StatCardProps) {
  return (
    <div className="StatCard">
      <h1>{label}</h1>
      <h1>{value}</h1>

      {sub && <h2>{sub}</h2>}

      {icon && <div>{icon}</div>}

      {trend && <div>{trend}</div>}
    </div>
  );
}

export default StatCard;