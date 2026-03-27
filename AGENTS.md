# AGENTS.md

이 문서는 `D:\alfo\mickle-muckle-api` 저장소에서 작업하는 Codex 및 기타 코딩 에이전트를 위한 기본 작업 규칙이다.

## 목표

- 사용자 요청을 해결하는 데 필요한 최소 변경만 수행한다.
- 변경 전에 관련 파일과 직접 연결된 진입 지점을 먼저 확인한다.
- 요청이 명확하지 않으면 기존 동작과 프로젝트 패턴을 우선 보존한다.
- 사용자가 명시적으로 구현이나 수정을 요청하지 않은 경우, 분석과 설명만으로 끝낼 수 있으면 코드 변경을 강행하지 않는다.

## 기본 전제

- 이 저장소는 사용자가 계속 작업 중인 로컬 백엔드 프로젝트라고 가정한다.
- 워크트리는 이미 변경된 상태일 수 있으며, 내가 만들지 않은 변경은 되돌리지 않는다.
- 현재 작업과 무관한 리팩터링, 파일 이동, 네이밍 변경은 하지 않는다.
- 설정, 데이터베이스, API 계약을 건드리는 변경은 영향 범위를 먼저 확인한다.

## 프로젝트 위치

- 프론트엔드: `D:\alfo\mickle-muckle`
- 백엔드: `D:\alfo\mickle-muckle-api`

## 프로젝트 개요

- 스택: Java 17, Spring Boot 3.4.3, Spring Web, Spring Data JPA, Lombok
- API 문서: `springdoc-openapi-starter-webmvc-ui`
- 기본 애플리케이션 진입점: `src/main/java/com/future/micklemuckle/MickleMuckleApiApplication.java`
- 주요 설정 파일: `src/main/resources/application.yml`
- 프로필별 설정 파일: `src/main/resources/config/application-h2.yml`, `src/main/resources/config/application-docker.yml`
- 초기 SQL: `src/main/resources/sql/schema.sql`, `src/main/resources/sql/data.sql`

## 주요 디렉터리 구조

- `src/main/java/com/future/micklemuckle/rest`: REST 컨트롤러
- `src/main/java/com/future/micklemuckle/modules`: 도메인별 모듈
- `src/main/java/com/future/micklemuckle/modules/*/service`: 서비스 계층
- `src/main/java/com/future/micklemuckle/modules/*/repository`: 저장소 계층
- `src/main/java/com/future/micklemuckle/modules/*/entity`: JPA 엔티티
- `src/main/java/com/future/micklemuckle/modules/*/dto`: 요청/응답 DTO
- `src/main/java/com/future/micklemuckle/config`: 웹, Jackson 등 설정
- `src/main/java/com/future/micklemuckle/common`: 공통 예외 및 공통 엔티티
- `src/test/java`: 테스트 코드

## 현재 확인된 도메인

- `ledger`
- `categories`
- `paymentMethod`

새 기능을 추가할 때는 기존 도메인 구조와 동일한 계층 분리를 우선 따른다.

## 작업 원칙

- 컨트롤러 수정 전에는 해당 서비스와 DTO를 함께 확인한다.
- 서비스 수정 전에는 관련 엔티티와 레포지토리 메서드를 먼저 확인한다.
- 요청 DTO와 응답 DTO는 분리된 현재 패턴을 유지한다.
- 공통 예외가 이미 있으면 새 예외를 만들기 전에 재사용 가능성을 먼저 본다.
- 단순 조회와 수정 로직을 한 메서드에 섞지 않는다.
- 가능한 한 현재 코드의 명명 규칙과 반환 타입 패턴을 따른다.
- 파일이 이미 ASCII 중심이면 새 문서와 코드도 ASCII를 우선 사용한다.

## API 작업 지침

- 엔드포인트를 바꾸기 전에는 프론트와의 계약이 깨지는지 먼저 확인한다.
- `POST`, `PATCH`, `DELETE`의 응답 형식은 같은 리소스의 기존 엔드포인트 스타일과 일관되게 유지한다.
- 조회 API는 엔티티를 직접 반환하지 말고 DTO를 통해 응답한다.
- 페이징은 현재 사용 중인 `Slice` 패턴을 우선 유지한다.
- 월 단위, 합계 등 집계 API는 기존 `ledger` 패턴과 맞춘다.

## JPA 및 도메인 규칙

- 엔티티 변경 전에는 연관관계와 기존 쿼리 메서드 영향 범위를 확인한다.
- 서비스 레벨에서 트랜잭션 경계를 관리하고, 현재 패턴과 맞게 `@Transactional`을 사용한다.
- 단순 필드 변경이라도 도메인 메서드가 이미 있으면 setter 추가보다 기존 메서드 확장을 우선 검토한다.
- 조회 성능이 민감한 구간에서는 레포지토리 쿼리와 반환 DTO 구조를 함께 본다.

## 문서화 및 검증

- 변경이 작으면 관련된 최소 검증만 수행한다.
- 실행 가능한 검증 명령을 모르면 `build.gradle`과 기존 테스트부터 확인한다.
- 테스트나 빌드를 실행하지 못했다면 최종 보고에서 명확히 밝힌다.

현재 이 저장소에서 우선 고려할 검증 명령:

- `.\gradlew test`
- `.\gradlew build`
- `.\gradlew bootRun`

## 커뮤니케이션 규칙

- 설명은 짧고 사실 위주로 작성한다.
- 추측이 필요한 경우, 무엇이 확인된 사실이고 무엇이 가정인지 구분한다.
- 리뷰 요청에서는 요약보다 버그 가능성, 회귀 위험, 누락된 테스트를 우선 본다.
- 현재 저장소에서 확인한 내용과 일반적인 Spring 관례를 섞어 말할 때는 그 경계를 분명히 한다.
- 사용자가 코드 관련 질문을 했을 때 현재 코드 스타일이 실무나 좋은 코드 컨벤션에 어긋나면, 수정된 코드나 예시 코드를 기존 스타일에 맞춰 제시하기 전에 더 권장되는 실무 기준과 방향을 먼저 안내한다.


## 금지 사항

- 사용자가 요청하지 않은 광범위한 리팩터링
- 내가 만들지 않은 변경의 임의 되돌리기
- 엔티티를 API 응답으로 직접 노출하는 변경
- 기존 DTO, 서비스, 레포지토리 계층을 무시한 우회 구현
- 설정 파일을 확인하지 않은 상태에서의 추정성 환경 변경

## 먼저 확인할 파일

- `README.md` 또는 별도 문서가 생기면 이 문서보다 우선 확인한다.
- `build.gradle`
- `src/main/resources/application.yml`
- 관련 컨트롤러, 서비스, DTO, 엔티티, 레포지토리

## 추가되면 좋은 정보

이 문서를 더 유용하게 만들려면 아래 정보를 나중에 보강하면 좋다.

- 실제 운영/개발 프로필 사용 방식
- 로컬 DB 실행 방식과 기본 접속 정보
- Swagger 또는 API 문서 접근 경로
- 프론트엔드와 맞물리는 핵심 API 목록
- 도메인별 명명 규칙과 예외 처리 규칙
- 테스트 작성 기준과 우선순위
