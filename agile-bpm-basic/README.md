# AgileBPM 敏捷工作流开发平台

#### 团队介绍
**AgileBPM 致力于构建灵活的、可选择的软件生态圈，您可以依赖自身需要的模块来构建自身应用，欢迎   :tw-1f449: Star  :tw-1f448:   持续关注！！！**

AgileBPM 是完全模块化的项目集合，含【流程模块】、【业务表单模块】、【组织架构模块】、【鉴权模块】、【系统模块】、【AO办公模块】、【PC前端项目】、【移动端前端项目】
模块与模块间不耦合，您可以自由选择需要的模块（目前鉴权有两个实现）

我们欢迎更多有志之士与我们一起共同打造 【基于统一底层的更多可共享应用的圈圈】

## AgileBPM 项目介绍


- 项目部署、实施文档: http://www.agilebpm.cn/  

- 流程实施视频介绍： https://share.weiyun.com/5uuOrvS

- PC在线测试地址: http://test.agilebpm.cn/login.html

- 【商业版 iview版本】 门户平台地址：http://test1.agilebpm.cn/agilebpm-eip-ui/index.html
- 【商业版 iview版本】 流程设计开发平台地址：http://test1.agilebpm.cn/index.html

- 功能缺陷请在项目上创建建 **issues**，可以查看已完成issues来寻找问题解决方法
<br/>

![移动端测试地址](https://images.gitee.com/uploads/images/2019/0212/102913_df0360bd_1861740.png "屏幕截图.png")

移动端测试账号密码 admin 1  [源码](https://gitee.com/agile-bpm/bpm-app)

## 工作流解决方案
**我们通过业务对象、表单、流程引擎共同协作来解决业务流难实施的痛点**

业务对象用来承载、持久化业务数据；表单则是业务数据的展示层；流程则用来驱动业务数据流转

三者协作完成流程实施。

> 
- **业务对象:**  由实体（表）组成，支持任意数据结构（关联关系），可以跨库来组织业务对象（支持分布式事务）。而且难以置信的支持N层。
- **业务表单:**  表单完美的支撑了业务对象的展示，并支持丰富的前端组件和字段级权限控制。
- **流程引擎:**  高效、解耦、强大、灵活。流程引擎**一切功能皆插件**。

**支持任意结构的业务对象 + 丰富控件易扩展的表单（字段级别的权限控制）  + 功能强大的工作流引擎** 
便是我们完整的流程解决方案

当然、流程也支持url表单，方便已有业务、异构系统的流程实施


**具体实施步骤请参考 [文档](https://agile-bpm.gitee.io/website/zh-cn/docs/businessObject.html) 中的敏捷流程实施三部曲** :smirk: 



## 软件架构说明

#### AgileBPM 项目模块总览脑图
http://naotu.baidu.com/file/08a8388689b651e4848ed07845bb5c76?token=5ec9a04eaf5b83bf

#### 组件化
系统通过功能划分出了多个模块，每个模块由API、CORE、REST、SERVICE(apiImpl) 几部分组成。模块与模块间通过API交互，WEB则用于整合各个模块 
[系统模块介绍介绍](https://agile-bpm.gitee.io/website/zh-cn/docs/framework.html)

[组件更多详细介绍](https://agile-bpm.gitee.io/website/zh-cn/docs/module.html)
 

#### 前后端分离
AgileBPM 是一个前后端分离的项目，这样各个团队会更专注于其本职工作，后端只负责业务逻辑、API 提供。而大前端则不拘泥于一种前端技术、更自由的构建UI交互逻辑

#### 项目技术组件
![项目组件](https://images.gitee.com/uploads/images/2018/0705/172349_e5de827a_1861740.png "屏幕截图.png")


##### 其他项目中用到的组件
前端：bootstrap-table，codemirror，echarts，layer，markdown，softable，ueditor，ztree
移动端：vue，vux,weui

#### 架构模式
AgileBPM 目前是标准的SOA架构，但依然拥有微服务架构的特点。
可以通过选择WEB模块的依赖来构建您需要的服务模块，然后修改API 实现，选择一个服务注册中心，就完成了微服务的改造

我们建议业务前期使用AgileBPM的这种模块化管理的架构模式，运维实施陈本小，也不必关注分布式事务问题，业务后期也可以很轻易的向微服务架构迁移。

## 流程功能
- **节点消息通知**： 用于在流程某一节点，满足某种条件后发送消息给配置的人员。可以自定义消息内容。
- **节点自由跳转**： 用于在流程某一节点，满足配置的条件后、自由跳转至配置的目标节点。
- **节点事件脚本**： 用于在流程节点，触发的配置groovy脚本。可以调用容器环境中bean方法。
- **节点人员配置**： 用于节点候选人配置，支持**用户，角色，岗位，组织，发起人，历史节点处理人，脚本人员**（通过groovy脚本调用自定义人员脚本）
- **节点处理按钮**： 用于节点任务处理动作配置。支持自由配置某节点可用的功能按钮。目前有**同意、反对、驳回、驳回配置节点、人工终止流程、流程图、审批意见、保存**等
- **节点跳过策略**：用于配置特殊场景的跳过策略，支持全局跳过（测试流程使用），跳过第一个节点，任务执行人为空跳过，脚本跳过
- **流程特殊属性配置**：配置流程特殊属性
- **全局表单**： 用于流程全局表单配置，目前支持内置表单、url表单。如果不配置则发起流程会提示错误。
- **节点表单**： 节点表单配置。如果不配置默认使用全局表单。
- **流程数据模型**：流程表单的数据模型、选择数据模型后，只能选择改数据模型下生成的表单。
- **流程变量管理**：定义管理流程变量
- **流程分支groovy脚本支持**
- **会签**: 多人参与投票，可通过百分比，票数来计算投票结果，并支持会签结果后置动作的配置。
- **外部子流程**： 外部子流程让流程公共逻辑抽出，更有利于业务流的划分和复用，是一枚大杀器。
- **流程标题自定义**： 可以通过业务数据配置生成个性化标题、让待办列表更易展示业务信息，也解决了业务数据与流程数据跨库下检索任务的问题。
- **动态任务**：（开发中）可以配置虚拟任务，定义任务产生和回收节点，并配置其办理人等信息。
- **移动端**：支持移动端协同办公，并且移动端依然支持强大的表单生成功能，支持pc端所有表单控件。

### 系统功能  
资源管理（用于服务器鉴权，用户分配资源菜单）、数据字典、定时计划（定时调度引擎）、系统属性（多环境系统参数定义）、系统数据源（系统支持多数据源的数据获取，系统数据源的动态切换，读写数据源的分离）、工作台（个人自定义首页）、流水号、常用脚本管理、自定义对话框、系统树

## 其他说明
我们是专业工作流研发团队，有多年工作流程实施经验，针对各种特殊场景，经过近一年多的(业余)时间设计开发了这款产品。
目前还有很多组件正在筹备开发中，如果有更多人支持，我们也会继续下去。

## spring boot 版本
https://gitee.com/agile-bpm/agilebpm-base-spring-boot/tree/master
## vue2 移动端版本
https://gitee.com/agile-bpm/bpm-app

##### SpringCloud 微服务版本正在构建中...

## 欢迎 **Star** 持续关注！！！ 
qq ①群： 477781857     ②群： 364350411

#### 欢迎关注技术公众号 java葵花宝典

![输入图片说明](https://images.gitee.com/uploads/images/2019/0515/110137_4c644b78_1861740.png "java葵花宝典")