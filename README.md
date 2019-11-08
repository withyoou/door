>   Summary

    项目分为基础设施服务（discovery、gateway、auth）和业务服务（demo...）
    
>   功能点

 1. 鉴权
    - 认证方式：预认证和认证。登录需要经过认证，获取到token后鉴权只通过预认证，预认证会对每个request进行鉴定，同时不需要频繁的数据库query等重量级操作。
    - token机制：采用JWT解决方案，token签名防止被篡改，可以在token上控制过期时间，也可以灵活续约。
    - 权限粒度: 基于spring security的Role-based的方式，在方法或api级别控制角色权限，后台业务服务可以灵活控制自己的api或方法的角色。
    
 2. 数据库变更管理 Liquibase
 
 3. 数据库层单元测试
    - 结合liquibase通过h2内存数据库，模拟数据结构，进行结构和数据操作测试
    
    
>   演示方式

 1. 鉴权: 需要启动discovery、gateway、auth、demo，通过postman发送POST：http://localhost:8081/auth/login,参数为{"username": "xxx", "password": "xxx"} json结构体。GET： http://localhost:8081/test/hello Header参数token->[上一步的返回值]
 2. liqubase： 启动demo，在demo的resources中有版本脚本文件，启动自动执行并记录。
 3. 数据库单元测试： 在demo下执行mvn test