# 음식점 정보 시스템

> 해당 프로젝트는 컴퓨터소프트웨어공학 2022학년도 1학기 객체지향프로그래밍 과목 팀 프로젝트 입니다.
>
> 해당 프로젝트는 리팩토링된 프로젝트로써 기존의 프로젝트는 아래 링크에서 확인할 수 있습니다.
>
> [리팩토링 전 프로젝트](https://github.com/cmsong111/Restaurant-information-system-server/tree/main)

## 프로젝트 소개

음식점 정보 시스템은 사용자가 음식점을 검색하고, 음식점에 대한 정보를 확인할 수 있는 시스템입니다.

또한, 사용자는 음식점에 대한 리뷰를 남길 수 있으며, 다른 사용자들이 남긴 리뷰를 확인할 수 있습니다.

## 프로젝트 구조

해당 프로젝트는 음식점 정보시스템의 서버 프로젝트로써 SpringBoot 3 기반으로 제작되었습니다.

사용 기술은 다음과 같습니다.

- SpringBoot 3.3.0
- Spring Web
- Spring Data JPA
- Spring Security
- H2 Database(임베디드 데이터베이스)
- Swagger 2.3.0
- kotlin 1.9.24 (Java 17)

## 프로젝트 API 문서

해당 프로젝트의 API 문서는 Swagger를 통해 제공됩니다.

프로젝트를 실행시키면 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)로 접속하여 API 문서를 확인할 수 있습니다.

또는 아래 링크를 통해 확인할 수 있습니다.

[https://cmsong111.github.io/Restaurant-information-system-server/](https://cmsong111.github.io/Restaurant-information-system-server/)

## 프로젝트 기능

- 사용자
    - 회원가입
    - 로그인
    - 회원정보 조회
    - 회원정보 수정
- 음식점
    - 음식점 조회 (검색 기능 포함)
    - 음식점 등록
    - 음식점 수정
    - 음식점 삭제
- 음식점 메뉴 CRUD
    - 음식점 메뉴 조회
    - 음식점 메뉴 등록(관리자만 가능)
    - 음식점 메뉴 수정(관리자만 가능)
    - 음식점 메뉴 삭제(관리자만 가능)
- 리뷰 CRUD
    - 리뷰 조회
    - 리뷰 등록
    - 리뷰 수정(작성자만 가능)
    - 리뷰 삭제(작성자, 관리자만 가능)

## 프로젝트 실행

해당 프로젝트는 SpringBoot 기반으로 제작되었으며, Gradle을 통해 빌드 및 실행이 가능합니다.

프로젝트를 실행시키기 위해서는 다음과 같은 명령어를 실행하면 됩니다.

```shell
./gradlew bootRun
```

위 명령어를 실행하면 [http://localhost:8080](http://localhost:8080)으로 접속하여 해당 프로젝트를 확인할 수 있습니다.
