---
name: Alipay payment learning setup
description: WAP-pay local testing plan — idcfengye tunnel for notify, LAN IP + vite for return; sandbox switch advised (2026-09-17)
type: project
---

用户在本地学习环境测试支付宝手机网站支付（2026-09-17 进行中），教程里的公网占位地址已失效。

已定方案：
- `notify_payment_url` 走花生壳(idcfengye)内网穿透隧道指向后端（仅一条免费隧道，指向后端网关）
- `return_payment_url` 用电脑局域网 IP + Vite dev server（spzx-admin，端口 3001，需 `host: '0.0.0.0'`），手机与电脑同一 WiFi；return 页面纯展示，订单状态靠 notify 异步回调更新

已建议但用户未必已执行：
- 切换支付宝沙箱环境（网关 `https://openapi-sandbox.dl.alipaydev.com/gateway.do`）——当前配置是正式网关 + 真实 app_id + 代码写死 0.01 元真实扣款
- `app_private_key` 曾在对话中明文暴露，建议到开放平台重置，勿提交 git

**Why:** 纯学习、无公网服务器；教程地址失效后需要可复现的本地支付测试链路。
**How to apply:** 支付相关问题基于"本地开发、无公网域名"前提回答，优先给局域网/沙箱方案，不建议买域名或部署公网。
