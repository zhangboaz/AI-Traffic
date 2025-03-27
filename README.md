# AI-Traffic

### 智能交通管理系统

## 城市交通建模

<img src="https://github.com/zhangboaz/AI-Traffic/blob/main/docs/idea.drawio.png" style="zoom:80%;" />

* 机动车辆位置、行人位置、红绿灯状态及唯一标识
* 确定城市大小，等比例缩放，使用坐标轴（x，y）表示机动车辆位置、行人位置
* 使用0，1，2表示红绿灯状态
* 测试用例以1s的时间间隔向主程序传输坐标信息及唯一标识等信息
* 前端使用threejs实现城市渲染
* 后端接收到数据后使用缓存保证访问速度
* --AI方案待确认

## 技术选择

### 前端

* nodejs（v20.18.1）

* pnpm (10.5.2)

* vue3

* vue-router

* threejs

### 后端

* java（17.0.12）
* springboot (3.4.3)
* mybatis
## 解决方案
### 登录
1. 密码使用MD5加密存储
2. 使用springboot配置全局异常处理器，返回错误信息
3. 使用Hs256算法, 私匙使用固定秘钥实现jwt令牌
4. RESTful API服务，处理前端请求


