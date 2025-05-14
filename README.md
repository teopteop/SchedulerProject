### 프로젝트 개요

---
Spring Boot 기반의 일정관리 서비스

### 기술 스택

---
- Java 17
- Spring Boot 3.4.5
- JDBC Template
- MySQL
- IntelliJ IDEA

### 프로젝트 구조

---
- `controller` : 클라이언트 요청 핸들링
- `dto`
  - `request` : 요청에 필요한 DTO 클래스 패키지
  - `response` : 응답에 필요한 DTO 클래스 패키지
- `entity`
- `exception` : 커스텀 예외처리와 핸들러 패키지
- `repository` : JdbcTemplate 을 활용한 DB 접근
- `service` : 비즈니스 로직 처리

### api 명세서

---
![SchedulerAPI.png](src/img/SchedulerAPI.png)

### ERD

---
![SchedulerERD.png](src/img/SchedulerERD.png)