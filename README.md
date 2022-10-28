# MIANYANG 项目
## 背景介绍
MIANYANG项目是一个数据采集收集显示的项目，在内网中不同地点布置多台子系统，
子系统采集附近设备的数据，汇总解析保存，并发送给总的控制中心，进行后续处理。
## 原项目
原项目基于C#编写，代码结构简陋，功能不完全，不宜维护，且由于自身不熟悉C#，
编写起来很费劲,所以起了用java重构的念头。

## 新项目v1
新的项目使用了springboot+dubbo+nacos+netty+mybatis-plus进行重构
，但仍然面临着前端向数据库轮询新数据的过程。

本项目是一个模拟实现，主要实现了子系统采集数据的过程，并未包含transfer的功能，
第一版修改可以用dubbo实现数据保存。

### 项目架构

#### krt-pojo pojo类模块
#### krt-api service接口模块
#### krt-dubbo-data-service 具体serverImpl模块
#### krt-tranfer-service 子系统连接控制中心模块
#### krt-server-test 模拟的子系统下游设备


