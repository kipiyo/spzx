---
name: Response style — /调优建议 command
description: Standing command template: concise problem list → complete fixed code → brief summary; no ellipsis/TODO; Chinese
type: feedback
---

用户激活了 `/调优建议` 命令模板，代码检查/修复类请求按三段式回答：

1. 只指出问题和优化点，不解释代码逻辑，文字精练；
2. 给出修复后的完整代码片段；不得根据想象补全未展示的依赖/所属类/方法/属性；不用省略号、TODO 或注释代替真实代码；
3. 结尾用尽可能简单的方式总结优化点。

**Why:** 用户明确配置的命令要求（真实性、清晰性、详实性、完整性、精炼性）。
**How to apply:** 本项目所有"检查/审查/修复/调优/啥原因"类请求默认套用此格式，用中文回答；纯粹的配置答疑类问题（如"该怎么设置"）也保持同样的精练风格。
