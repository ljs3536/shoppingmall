# 프로젝트 기록 및 작업 관련 정보

## 2025-03-19
프로젝트 생성
Java : 17
SpringFramework : 3.4.3
- web, devtools, validation, lombok, jpa, thymeleaf

## 2025-03-25
- Buyer의 상품목록 조회 시 상품 상세정보 페이지 작업 필요
- ErrorPage 및 Exception 처리 필요

## 2025-03-26
- 상품 상세정보 페이지 작업 완료
- ErrorPage 및 Exception 처리 추가 완료
- Buyer의 상품 주문 기능 구현 필요
- 상품정보에서 Image 추가 필요
- Validation 기능 강화 필요

## 2025-03-27
- 주문등록, 주문목록, 주문상세 기능 구현 완료

## 2025-03-28
- 상품 이미지 업로드 기능 생성
- 장바구니 등록 기능 임시 생성
- 이미지 미리보기 기능 필요
- 장바구니 등록 후 주문 기능 필요


## 2025-03-31
- 상품 이미지 미리보기 기능 추가
- 상품 이미지 수정 기능 추가 (SubImage Id를 사용하여 기존의 데이터와 새로생긴 데이터 구분)
- 이미지 경로를 위한 Config 추가

## 2025-04-01
- 판매자의 주문 상품 목록 기능 추가
- 판매자의 주문 상품 상태 변경 기능 추가
- BootStrap 버전 5.0 업데이트
- 장바구니에서 주문 완료 시 장바구니에서 체크되었던 상품 삭제 로직 추가 필요

## 2025-04-02
- 장바구니에서 주문하기 시 주문 후 장바구니 목록에서 상품 삭제
- 장바구니 목록에서 수량 수정, 상품 삭제 기능 구현
- CSRF 토큰 처리 추가

## 2025-04-03
- 멤버, 상품, 주문 목록 페이징 처리 추가
- 페이징 작업 중 JPA의 N+1 문제 발생 방지로 @EntityGraph 적용 (이부분은 더 알아봐야함)
- 멤버, 상품 목록 검색 기능 추가
- 검색 쿼리문 작성 부분에서 Querydsl 적용 (Querydsl 과 JPARepository 제공 메서드 혼용)
- 리뷰 작성 및 상품상세에서 작성한 리뷰 확인 기능 추가
- Fetch join을 사용하여 조회하는 부분에서의 N+1 문제 확인 필요
- 로그 기능 추가 필요

## 2025-04-04
- 로그 기능 추가
- Service와 분리된 EventListener 사용 및 Aspect 적용
- 사용자 메뉴 접근, 장바구니 추가, 상품 주문 시 로그 등록
- 로그 데이터 관리를 위한 Elasticsearch 사용

## 2025-04-08
- 통계 페이지 구현 중
- log 데이터를 활용한 페이지 및 로직 구현 중

## 2025-04-15 
Docker에 mariadb, Elasticsearch, Flask, Springboot를 Container화 하기 위한 시도 
Springboot와 Elasticsearch 연결에서 에러 발생

## 2025-04-17
Docker에 minikube 환경 세팅
docker hub에 springboot와 flask repository 등록 후 kubernetes에서 사용
CI/CD 필요
