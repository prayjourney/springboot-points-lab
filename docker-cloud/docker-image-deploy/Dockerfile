#基础镜像
FROM amazoncorretto:8
MAINTAINER zgy<zgy@123.com>
#工作目录
WORKDIR /usr/local/app
#把我们打包好的target目录下的这个jar, add到工作目录之中
ADD ./target/docker-image-deploy.jar .
#端口要和配置的profile中的一致
EXPOSE 11998
CMD ["java", "-jar", "docker-image-deploy.jar"]
