---
name: 本机 8500 端口被 Consul 服务占用(网关端口冲突)
description: 用户 Windows 机器上 8500 端口被 Windows 服务 Consul(consul.exe)占用,导致 spzx-server-gateway(配置端口 8500)启动失败
type: project
---

用户为处理跨域新建了网关模块 spzx-server-gateway,配置端口为 8500(application-dev.yaml),但启动报"8500 已被占用"。

排查结果:本机(Windows 10)上 8500 端口被 **Windows 服务 "Consul"(consul.exe,当时 PID 6992)** 占用(Consul 默认 HTTP 端口恰为 8500),与 spzx 项目无关——项目服务发现用的是 Nacos 8848。consul 是机器上残留的独立服务,以服务方式运行、可能开机自启。

**Why:** 项目网关固定用 8500,而 Consul 服务长期霸占该端口,导致网关起不来。

**How to apply:** 之后若再遇到 8500 端口占用,先想到是 consul.exe 服务:`netstat -ano | findstr :8500` 拿 PID,`tasklist /svc /fi "PID eq <PID>"` 确认;处理方式为 `net stop Consul`(并 `sc config Consul start= disabled` 禁自启)或改网关端口。
