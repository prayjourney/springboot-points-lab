#基础镜像
FROM amazoncorretto:8
MAINTAINER zgy<zgy@123.com>
#工作目录
WORKDIR /usr/local/app
#把我们打包好的target目录下的这个jar, add到工作目录之中
ADD ./target/docker-logs.jar .
#端口要和配置的profile中的一致
EXPOSE 16100
# 通过配置, 选择激活的profile
ENTRYPOINT ["java", "-Dspring.profiles.active=${active_profile}", "-jar", "docker-logs.jar"]
