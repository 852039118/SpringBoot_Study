FROM java:8

# 服务器只有dockerfile 和 jar 在一起
COPY *.jar /app.jar

CMD ["--server.port=8088"]

# 暴露端口
EXPOSE ["8088"]

ENTRYPOINT ["java","-jar","/app.jar"]
