# 음식점 정보 시스템

> 해당 프로젝트는 컴퓨터소프트웨어공학 2022학년도 1학기 객체지향프로그래밍 과목 팀 프로젝트 입니다.
>
> `refactoring`브런치는는 리팩토링된 프로젝트로써 기존의 프로젝트는 [main 브런치](https://github.com/cmsong111/Restaurant-information-system-server/tree/main)에서 확인하실 수 있습니다.

## 프로젝트 소개

![img](https://raw.githubusercontent.com/cmsong111/Restaurant-information-system/df1e8787e5e1938f5f6ea0fb5bfb5ae41006d7f1/img/capture1.png)

음식점 정보 시스템은 사용자가 음식점을 검색하고, 음식점에 대한 정보를 확인할 수 있는 시스템입니다.

또한, 사용자는 음식점에 대한 리뷰를 남길 수 있으며, 다른 사용자들이 남긴 리뷰를 확인할 수 있습니다.

해당 프로젝트의 경우 프론트엔드와 백엔드로 나누어져 있으며, 해당 프로젝트는 백엔드 프로젝트입니다.

프론트엔드의 경우 [Restaurant information system client](https://github.com/cmsong111/Restaurant-information-system) 프로젝트를 참고하시기 바랍니다.

## 프로젝트 구조

해당 프로젝트는 음식점 정보시스템의 서버 프로젝트로써 SpringBoot 3 기반으로 제작되었습니다.

사용된 주요 패키지는 다음과 같습니다.

- SpringBoot 3.3.0
- Spring Web
- Spring Data JPA
- Spring Security
- H2 Database(임베디드 데이터베이스)

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

## 프로젝트 API 문서

해당 프로젝트의 API 문서는 OpenAPI 3.0을 기반으로 작성되었습니다.

- PostMan Collection(리팩토링 전 사용하던 API 문서)
    - [PostMan Collection](https://documenter.getpostman.com/view/19569578/2sA3rxqtLg)
- Swagger Editor(GitHub Pages 배포)
    - [Swagger UI](https://cmsong111.github.io/Restaurant-information-system-server/)
- Swagger UI (서버 실행 후 확인)
    - [Swagger UI](http://localhost:8080/swagger-ui.html)

