# SpringStomp 프로젝트

이 프로젝트는 Spring Boot와 STOMP(WebSocket)를 활용한 실시간 채팅 예제입니다.

## 주요 기능

-   실시간 채팅방 생성 및 입장
-   WebSocket을 통한 메시지 송수신
-   채팅방 목록 조회
-   간단한 REST API 제공

## 프로젝트 구조

```
src/
  main/
    java/
      com/example/demo/
        SpringStompApplication.java
        config/           # 설정 파일
        controller/       # 컨트롤러 (API, 웹소켓)
        mapper/           # MyBatis 매퍼
        model/            # 데이터 모델
        service/          # 서비스 로직
    resources/
      application.properties
      mapper/            # MyBatis XML 매퍼
      templates/         # 뷰 템플릿 (index.html)
  test/
    java/
      com/example/demo/
        SpringStompApplicationTests.java
```

## 실행 방법

1. 의존성 설치: `./mvnw clean install`
2. 서버 실행: `./mvnw spring-boot:run`
3. 브라우저에서 `http://localhost:8080` 접속

## 참고

-   WebSocket 및 STOMP 프로토콜 이해 필요
-   DB 설정은 `application.properties` 참고
