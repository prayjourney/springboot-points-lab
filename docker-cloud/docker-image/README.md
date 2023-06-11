### SpringBoot工程使用docker运行起来的两种方式
1. 写dockerfile, 然后自己打包, 上传到仓库之中, 执行命令, 运行容器, 通用但是比较繁琐
2. 在idea之中，配合docker插件运行, 方便但是不通用, 写dockerfile之后的操作与idea强相关



---
#### 1. 直接把jar包打包成镜像
写dockerfile的模版, 写完之后把springboot工程打包成镜像然后运行
1. 把工程打成jar包
2. 写dockerfile文件
```dockerfile
FROM java:8
MAINTAINER zgy<zgy@123.com>
WORKDIR /usr/local/app
COPY *.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-jar", "app.jar"]
ENTRYPOINT ["java", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-jar", "app.jar"]
```
3. 把dockerfile和jar包放在一起, 然后执行打包命令
`docker build -t myimage:v1.0 -f ./Dockerfile .`, 最后的.不能丢
4. 创建运行容器
`docker run -d --name demo p 8080:8080 myimage`
5. 查看日志
``docker logs demo`, demo是容器名称
6. 把镜像推送到仓库, 推送之前要给镜像设置版本, tag命令, 一开始设置也可以
```dockerfile
docker push 注册用户名/镜像名
docker tag boonya/tomcat-allow-remote boonyadocker/tomcat-allow-remote
docker push boonyadocker/tomcat-allow-remote:latest
```

>引用
>
>https://blog.csdn.net/m0_58969296/article/details/128063832, https://www.bmabk.com/index.php/post/83619.html,
>https://www.runoob.com/docker/docker-build-command.html, https://www.cnblogs.com/weifeng1463/p/10214972.html,
>https://www.kancloud.cn/woshigrey/docker/935037, https://blog.csdn.net/boonya/article/details/74906927, https://blog.csdn.net/kaixincheng2009/article/details/108250848


