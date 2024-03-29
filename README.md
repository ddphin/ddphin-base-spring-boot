# DDphin 通用基础工具

## 1. 通用工具类
```$xslt
            <dependency>
                <groupId>com.github.ddphin</groupId>
                <artifactId>ddphin-base-common-spring-boot-starter</artifactId>
                <version>1.1.9</version>
            </dependency>
```

- 通用 HttpMessageConverter： FastJsonHttpMessageConverter
- 通用异常处理类：CExceptionHandler
- 通用异常类：CException
- 通用信息类：CMessage
- 通用可排序类：COrderable
- 通用响应类：CResponse
- 通用工具包：com.ddphin.base.common.util
- 通用校验包：com.ddphin.base.common.validate

## 2. 数据库自动初始化和升级
```$xslt
            <dependency>
                <groupId>com.github.ddphin</groupId>
                <artifactId>ddphin-base-db-spring-boot-starter</artifactId>
                <version>1.1.9</version>
            </dependency>
```
- 配置
application.yml
    ```$xslt
    application:
        version: 1.4.0  # 版本号
        init: false     # 是否初始化
    spring:
        datasource:
            driver-class-name: com.mysql.cj.jdbc.Driver
            url: jdbc:mysql://xxx.xxx.xxx.xxx:13306/xxx
            username: xxx
            password: xxx
            admin-url: jdbc:mysql://xxx.xxx.xxx.xxx:13306
            admin-username: xxx
            admin-password: xxx              
    ```
- 创建目录resources/db，存放数据库SQL文件
- 初始化 
<br>resources/db目录下存放 init.sql 文件
<br>init.sql文件中必须包含数据库升级记录表：db_upgrade_log
    ```$xslt
    create table db_upgrade_log
    (
        id bigint auto_increment primary key,
        version varchar(32) not null,
        status int default 0 not null comment '0:upgrading 1:success 2:failed',
        constraint db_upgrade_log_version_uindex unique (version)
    );

    ```
- 升级
<br>resources/db目录下存放 upgrade-${version}.sql 文件

## 3. Elasticsearch 自动升级
```$xslt
            <dependency>
                <groupId>com.github.ddphin</groupId>
                <artifactId>ddphin-base-es-spring-boot-starter</artifactId>
                <version>1.1.9</version>
            </dependency>
```
- 配置
application.yml
    ```$xslt
    application:
      version: 1.4.0  # 版本号
    ```
- 创建目录resources/es，存放es升级文件
- 初始化 
<br>数据库中必须包含es升级记录表：es_upgrade_log
    ```$xslt
    create table es_upgrade_log
    (
        id bigint auto_increment primary key,
        version varchar(32) not null,
        status int default 0 not null comment '0:upgrading 1:success 2:failed',
        constraint es_upgrade_log_version_uindex unique (version)
    );

    ```
- 升级
<br>resources/es目录下存放 upgrade-${version}.yml 文件
<br>yml 格式：
    ```$xslt
    upgrade:
      index:
        - create:
            your_index: your_index_define
        - aliases:
            - add:
                index: your_index
                alias: your_alias
            - remvoe:
                index: your_index
                alias: your_index
        - reindex:
            source:
              index: your_index
            dest:
              index: your_index    
            script:
              code: ctx._source.field1 = ctx._source.remove("field2");
        - delete: your_index                 
    ```

## 4. Ali OSS 
```$xslt
            <dependency>
                <groupId>com.github.ddphin</groupId>
                <artifactId>ddphin-base-oss-spring-boot-starter</artifactId>
                <version>1.1.9</version>
            </dependency>
```
- 配置
application.yml
    ```$xslt
    ali:
      oss:
        appKeyId: xxxxx
        appKeySecret:  xxxxx
        endpoint: xxxxx.aliyuncs.com
        imgBucket: xxx         # bucket
        imgHome: xxx           # 图片主目录
        imgCdn: xxx            # 图片CDN域名
    ```
- 接口：`POST: /oss/upload/image`
<br>参数： `file` 
<br>返回： `文件路径`


## 5. Swagger Api 文档 
```$xslt
            <dependency>
                <groupId>com.github.ddphin</groupId>
                <artifactId>ddphin-base-swagger-spring-boot-starter</artifactId>
                <version>1.1.9</version>
            </dependency>
```
- 增加对枚举的支持
<br>在任何对象的属性上使用注解将属性与枚举绑定`@BindEnum(YourEnum.class)`，文档在输出的时候，在描述文案中将添加所有枚举值
    ```$xslt
    @Data
    public class DemoModel {
        private Long id;
        private String name;
        @BindEnum(DemoModelStatusEnum.class)
        private Integer status;
    }
    ```
   - 如果是原生枚举，输出枚举格式为： 
   <br>`[ordinal1：name1, ordinal2：name2, ordinal3：name3]`
   - 如果是实现`CodeNameEnum`接口的枚举，输出枚举格式为： 
   <br>`[code1：name1, code2：name2, code3：name3]`
- 如果有权限限制，需要给相关URL配置访问权限，或者在界面添加授权参数
<br>（如全局参数Header中添加Authorization为TOEKN）
    ```$xslt
    "/swagger-resources/**",
    "/webjars/**",
    "/v2/api-docs",
    "/v2/api-docs-ext",
    "/doc.html"
    ```
- 地址：`${HOST}/api/doc.html`
