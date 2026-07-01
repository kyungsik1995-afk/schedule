📌 Schedule API 프로젝트 (README)

📖 프로젝트 소개

Spring Boot + JPA를 활용하여 일정 관리 REST API를 구현한 프로젝트입니다.
CRUD 기능을 포함하며, 작성자 기준 조회 및 수정일 정렬 기능을 제공합니다.

⚙️ 기술 스택
Java 17
Spring Boot 3.x
Spring Web
Spring Data JPA
MySQL
Lombok

📂 프로젝트 구조
com.example.schedule
 ┣ controller
 ┃ ┗ ScheduleController
 ┣ service
 ┃ ┗ ScheduleService
 ┣ repository
 ┃ ┗ ScheduleRepository
 ┣ entity
 ┃ ┗ Schedule
 ┣ dto
 ┃ ┣ request
 ┃ ┗ response

📌 주요 기능

✅ Lv1. 일정 생성
일정 생성 (제목, 내용, 작성자, 비밀번호)
작성일 / 수정일 자동 생성 (JPA Auditing)
비밀번호는 응답에서 제외

✅ Lv2. 일정 조회
✔ 전체 조회
모든 일정 조회
수정일 기준 내림차순 정렬
✔ 작성자 조건 조회
writer 값이 있을 경우 해당 작성자만 조회
하나의 API로 처리
API

📍 전체 조회 / 조건 조회
GET /schedules
GET /schedules?writer=경식

✅ Lv3. 일정 수정
제목, 작성자 수정 가능
비밀번호 검증 필수
수정 시 modifiedAt 자동 갱신
비밀번호 응답 제외
API
PUT /schedules/{id}

✅ Lv4. 일정 삭제
비밀번호 검증 후 삭제
데이터 영구 삭제
API
DELETE /schedules/{id}

📌 Entity 구조
@Entity
public class Schedule {

    private Long id;
    private String title;
    private String contents;
    private String writer;
    private String password;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}

📌 Repository 기능
List<Schedule> findAllByOrderByModifiedAtDesc();

List<Schedule> findByWriterOrderByModifiedAtDesc(String writer);

📌 핵심 설계 포인트
DTO를 통한 계층 분리
Service 중심 비즈니스 로직 처리
Controller는 요청/응답 역할만 수행
JPA Auditing을 통한 시간 자동 관리
비밀번호는 항상 응답에서 제외

🧪 Postman 테스트 정리
1️⃣ 생성
POST /schedules
2️⃣ 조회
GET /schedules
GET /schedules?writer=경식
3️⃣ 수정
PUT /schedules/{id}
4️⃣ 삭제
DELETE /schedules/{id}

🎯 최종 결과

✔ CRUD 기능 완성
✔ 조건 조회 구현
✔ 정렬 기능 구현
✔ 비밀번호 검증 처리
✔ JPA Auditing 적용
