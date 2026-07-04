up-all:
    #!/usr/bin/env bash
    set -euo pipefail
    trap 'kill $(jobs -pr) 2>/dev/null || true' EXIT INT TERM
    (cd bookmind && lein run) &
    (cd frontend/bookmind && npm run dev) &
    wait

up-backend:
    cd bookmind && lein run

up-frontend:
    cd frontend/bookmind && npm run dev
