# BookMind

A simple project for ingesting books into Qdrant collections. It consists of a Clojure API backend and a React frontend.

## Prerequisites

- **Java** (JDK 11+)
- **[Leiningen](https://leiningen.org/)** (`lein`) — Clojure build tool
- **Node.js** (18+) and **npm**
- **[just](https://github.com/casey/just)** (optional) — task runner for starting services

## Project layout

| Path | Description |
|------|-------------|
| `bookmind/` | Clojure API (http-kit + Reitit) |
| `frontend/bookmind/` | React + TypeScript + Vite UI |

## Quick start (both services)

From the repo root, with [just](https://github.com/casey/just) installed:

```bash
just up-all
```

This starts the Clojure backend and the frontend dev server in parallel.

## Clojure backend

The API listens on **http://localhost:8080**. Swagger UI is available at **http://localhost:8080/docs**.

### Install and run

```bash
cd bookmind
lein run
```

On first run, Leiningen will download Clojure dependencies automatically.

### Run with just

```bash
just up-backend
```

## Frontend

The UI is a Vite dev server (default **http://localhost:5173**). It talks to the Clojure API via the `VITE_CLJ_BK_API_URL` environment variable.

### Install and run

```bash
cd frontend/bookmind
npm install
cp .env.example .env   # points the API client at http://localhost:8080
npm run dev
```

### Run with just

```bash
just up-frontend
```

Make sure the backend is running before using the frontend, or API calls will fail.

## Environment variables

| Variable | Location | Default | Description |
|----------|----------|---------|-------------|
| `VITE_CLJ_BK_API_URL` | `frontend/bookmind/.env` | `http://localhost:8080` | Base URL for the Clojure API |

## Useful commands

| Command | Description |
|---------|-------------|
| `just up-all` | Start backend and frontend together |
| `just up-backend` | Start only the Clojure API |
| `just up-frontend` | Start only the frontend dev server |
| `cd frontend/bookmind && npm run build` | Production build of the frontend |
| `cd bookmind && lein test` | Run backend tests |
