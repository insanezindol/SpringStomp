# SpringStomp 프로젝트

이 프로젝트는 Spring Boot와 STOMP(WebSocket)를 활용한 실시간 채팅 예제입니다.

## 주요 기능

-   실시간 채팅방 생성 및 입장
-   WebSocket(SockJS + STOMP)을 통한 메시지 송수신
-   채팅방 목록 조회 / 수정 / 삭제
-   간단한 REST API 제공

## 기술 스택

| 구분 | 버전 |
| --- | --- |
| Java | 25 |
| Spring Boot | 4.1.1 (Spring Framework 7) |
| 내장 웹서버 | Tomcat |
| 빌드 도구 | Maven 3.9.16 (Maven Wrapper 3.3.4) |
| 템플릿 | Thymeleaf |
| 데이터 접근 | MyBatis (mybatis-spring-boot-starter 4.1.0) |
| DB | MySQL (mysql-connector-j) |
| SQL 로깅 | log4jdbc-log4j2 |
| 프론트엔드 (WebJars) | Bootstrap 5.3.8, jQuery 4.0.0, sockjs-client 1.5.1, stomp-websocket 2.3.4 |

## 프로젝트 구조

```
src/
  main/
    java/
      com/example/demo/
        SpringStompApplication.java
        config/           # WebSocket(STOMP) 설정, 채널 인터셉터
        controller/       # 컨트롤러 (화면, REST API, STOMP 메시지)
        mapper/           # MyBatis 매퍼 인터페이스
        model/            # 데이터 모델
        service/          # 서비스 로직
    resources/
      application.properties
      log4jdbc.log4j2.properties
      mapper/            # MyBatis XML 매퍼
      templates/         # 뷰 템플릿 (index.html)
  test/
    java/
      com/example/demo/
        SpringStompApplicationTests.java
```

## 사전 준비

### JDK 25

`JAVA_HOME`이 JDK 25를 가리키도록 설정합니다. Maven은 Wrapper가 자동으로 내려받으므로 따로 설치하지 않아도 됩니다.

```powershell
# PowerShell
$env:JAVA_HOME = "C:\java\jdk-25.0.4.1"
```

```bash
# bash
export JAVA_HOME=/c/java/jdk-25.0.4.1
```

### MySQL

`application.properties`의 `spring.datasource.*` 설정에 맞게 DB를 준비합니다. 기본값은 `ubuntu-server:3306/appdb`, 계정 `appuser`입니다.

```sql
CREATE TABLE appdb.chat_room (
    id   BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    date DATETIME     NOT NULL
);
```

로컬에서 Docker로 간단히 띄울 수도 있습니다.

```bash
docker run -d --name springstomp-mysql -p 3306:3306 \
  -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=appdb \
  -e MYSQL_USER=appuser -e MYSQL_PASSWORD=123456 mysql:8.4
```

이 경우 실행할 때 접속 주소를 덮어쓰면 됩니다.

```
--spring.datasource.url=jdbc:log4jdbc:mysql://localhost:3306/appdb?useUnicode=true&characterEncoding=UTF-8
```

## 실행 방법

1. 빌드 및 테스트: `./mvnw clean verify` (Windows PowerShell/CMD: `mvnw.cmd clean verify`)
2. 서버 실행: `./mvnw spring-boot:run` 또는 `java -jar target/SpringStomp-0.0.1-SNAPSHOT.jar`
3. 브라우저에서 `http://localhost:8080` 접속

## API

### REST (채팅방)

| Method | URL | 파라미터 | 설명 |
| --- | --- | --- | --- |
| GET | `/room/resource` | - | 채팅방 목록 |
| GET | `/room/resource/{id}` | - | 채팅방 단건 조회 |
| POST | `/room/resource` | `name` | 채팅방 생성 |
| PUT | `/room/resource` | `id`, `name` | 채팅방 이름 수정 |
| DELETE | `/room/resource` | `id` | 채팅방 삭제 |

### STOMP

| 구분 | 경로 |
| --- | --- |
| 연결 endpoint (SockJS) | `/secured/ws-stomp` |
| 발행 (client → server) | `/pub/inout/{roomId}` |
| 구독 (server → client) | `/sub/inout/{roomId}` |

## 참고

-   WebSocket 및 STOMP 프로토콜 이해 필요
-   DB 설정은 `application.properties` 참고
-   SQL 로그는 log4jdbc를 통해 `jdbc.sqltiming`, `jdbc.resultsettable` 로거로 출력됩니다.
