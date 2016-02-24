#2bcms

## 技术

- 项目 spring+springMVC+mybatis 并使用maven进行构建
- 项目中使用spring boot 进行简化开发.
- 数据源使用alibaba 的druid

更新项目代码后通过如下命令进行运行:

    mvn spring-boot:run

    或者 在ApplicationMain 类上右键 运行

前端构建:

    npm install
    bower install
    gulp 


## 项目目录结构说明

    src
    --main
    ----java
    ------com.tbcms
    ----------config                //配置bean
    ----------dao                   //dao mybatis mapper
    ----------domain                //domain
    ----------service               //业务实现类
    ----------spi                   //业务类接口 interface
    ----------web                   //web相关
    -------------api                // (api Controller)
    -------------action             // (action Controller)
    ----------ApplicationMain       // spring boot Main
    ----resources
    ------config
    ---------sqlmap                 // mybatis mapper xml
    ------application.properties    // spring boot config
    ------generator.properties      // mybatis model,mapper,domain 自动生成配置文件
    ------genetatorConfig.xml       // mybatis model,mapper,domain 自动生成配置文件
    --test
    ----java
    pom.xml
    README.md
    .gitignore

## git
git使用将遵循 git flow , develop 为开发分支, master 为产品级的稳定代码
