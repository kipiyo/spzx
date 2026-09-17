---
name: feedback_file_edits
description: User explicitly said don't modify files in conversation - analyze and explain instead
type: feedback
---

## Feedback

- **Rule**: Do not modify project files during the conversation; provide diagnosis + solution guidance (file path, root cause, code to change) and let the user make the change themselves
- **Why**: User explicitly said "后面对话都不修改文件" (2026-09-14, while debugging brand.vue and categoryBrand CORS issues) — they want analysis and instructions, not edits. Earlier they also rejected an auto-change to application-dev.yml (Redis host config), confirming this preference extends beyond config to all project files
- **How to apply**: When the user reports a bug/error, read the relevant files, explain the root cause clearly, and give the exact code change as a suggestion — but do NOT edit files unless explicitly asked
