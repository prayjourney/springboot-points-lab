#FROM java:8
FROM amazoncorretto:8
MAINTAINER zgy<zgy@123.com>
#设置了工作目录,　下面拷贝的app.jar就放到了/usr/local/app/目录下面
WORKDIR /usr/local/app
#当前目录所有的jar都复制到app.jar之中
COPY *.jar app.jar
#暴露8080端口, 和application.yml之中要一致
EXPOSE 8080
#ENTRYPOINT可以传入参数
ENTRYPOINT ["java", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-jar", "app.jar"]