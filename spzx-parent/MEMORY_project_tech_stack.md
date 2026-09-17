---
name: project_tech_stack
description: spzx-parent tech stack and infrastructure details
type: project
---

## Project Infrastructure

**Tech Stack:**
- Frontend: `localhost:3001`
- Backend: `localhost:8501` (Spring Boot)
- Database: MySQL (localhost:3306, db_spzx)
- Redis: Docker-based on server (user hasn't migrated to local yet)
- File Storage: MinIO (running minio.exe locally on Windows)
- Bucket name: `spzx-b`

**Configuration File:**
- `spzx-manager/src/main/resources/application-dev.yml`
