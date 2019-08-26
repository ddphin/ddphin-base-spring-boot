# DDphin 通用基础工具

## 工具类
```$xslt
            <dependency>
                <groupId>com.github.ddphin</groupId>
                <artifactId>ddphin-base-common</artifactId>
                <version>1.0.2</version>
            </dependency>
```

## 数据库自动初始化和升级
```$xslt
            <dependency>
                <groupId>com.github.ddphin</groupId>
                <artifactId>ddphin-base-db-spring-boot-starter</artifactId>
                <version>1.0.2</version>
            </dependency>
```
### 使用
- 配置
application.yml
    ```$xslt
    application:
      version: 1.4.0  # 版本号
      init: false     # 是否初始化
    ```
- 创建目录resources/db，存放数据库SQL文件
- 初始化 
<br>resources/db目录下存放 init.sql 文件
<br>init.sql文件中必须包含数据库升级记录表：db_upgrade_log
    ```$xslt
    create table db_upgrade_log
    (
        id bigint auto_increment
            primary key,
        version varchar(32) not null,
        status int default 0 not null comment '0:upgrading 1:success 2:failed',
        constraint db_upgrade_log_version_uindex
            unique (version)
    );

    ```
- 升级
<br>resources/db目录下存放 upgrade-${version}.sql 文件

## Elasticsearch 自动升级
```$xslt
            <dependency>
                <groupId>com.github.ddphin</groupId>
                <artifactId>ddphin-base-es-spring-boot-starter</artifactId>
                <version>1.0.2</version>
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
        id bigint auto_increment
            primary key,
        version varchar(32) not null,
        status int default 0 not null comment '0:upgrading 1:success 2:failed',
        constraint es_upgrade_log_version_uindex
            unique (version)
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
        - delete: your_index                 
    ```

## Ali OSS 
```$xslt
            <dependency>
                <groupId>com.github.ddphin</groupId>
                <artifactId>ddphin-base-oss-spring-boot-starter</artifactId>
                <version>1.0.2</version>
            </dependency>
```
- 配置
application.yml
    ```$xslt
    ali:
      oss:
        appKeyId: xxxxx
        appKeySecret:  xxxxx
        imgBucket: xxx         # bucket
        imgHome: xxx           # 图片主目录
        imgCdn: xxx            # 图片CDN域名
    ```
- 接口：`POST: /oss/upload/image`
<br>参数： `file` 
<br>返回： `文件路径`