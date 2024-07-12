# 使用 Amazon Corretto 21 映像檔作為基礎映像
FROM amazoncorretto:21

# 設置應用程式的工作目錄
WORKDIR /app

# 複製生成的 .jar 檔案到容器中
COPY target/work-practice-1.0-SNAPSHOT.jar app.jar

# 應用程式運行時的端口
EXPOSE 8080

# 設定容器啟動時執行的命令
ENTRYPOINT ["java", "SPRING_PROFILES_ACTIVE=dev","-jar", "app.jar"]



