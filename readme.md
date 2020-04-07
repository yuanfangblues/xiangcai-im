### Demo
1.从零学习netty, 目前是学习demo阶段, 可在 com.base目录查看

demo1: DiscardServer
demo2: EchoServer
demo3: TimeServer
demo4: TimeServerV2
demo5: UpTimeServer

2.基本的协议服务器

TelnetServer

ChatServer

持续更新中。。。

3.高级阶段 -> 手撕IM

### 基础设施:
 1.mysql + Mybatis Plus
 2.redis
 3.rocketmq
 4.netty
 5.zk

### 基础配置
 1.日志打印标准
 
 
 
 ### 描述
 香菜IM 是一个后端即时通讯服务
 
 
 ### 功能点
 1.单聊
 
 业务流程：用户A给用户B发送消息, B收到后给A回ok
 
 细节执行过程：
 1.A接入连接层，进行鉴权验证(考虑这个放在哪一层合适)
 2.连接层连接路由层 进行内部协议转换
 3.路由层捞B用户在线状态，如果在线，则直接推送消息连接层
 4.连接层推消息给用户B
 5.如果用户不在线，则消息落盘，等B上线了，客户端主动拉消息
 
 2.群聊
 
 
 ### 架构
 
 
 
 ### 技术选型
 
 
 
 ### 备注
 
 