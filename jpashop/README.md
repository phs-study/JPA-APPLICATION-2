API 개발 기본
--------------

- 커맨드와 쿼리를 분리하라. 
  - ``Member update(Long id, String name)``
  - 위 함수는 커맨드와 조회가 함께 되고 있음.
  - ``void update(Long id, String name)``
  - 위 함수는 커맨드만 수행함. (조회를 하고 싶다면, 다시 select를 호출하라.)

API 개발 고급
--------------
- Entity를 외부에 노출하지 말라.
  - API 스펙이 Entity에 의존적으로 변하는 문제
  - 양방향 관계에서의 무한 루프
  - Lazy 로딩이 설정된 경우, Jackson 라이브러리가 Proxy 객체를 reflect하지 못함.
  - > 외부 노출용 DTO를 사용하라.

- 지연 로딩에 의한 성능 이슈
  - N + 1 문제
    - 1번째 쿼리의 결과가 N개이고 결과 타입에 지연 로딩 연관관계가 M개일 경우, 최대 1 + N * M 개의 쿼리가 실행됨
      - order 조회 1번
      - order -> member 지연 로딩 조회 
      - order -> delivery 지연 로딩 조회
      - order의 결과가 2개일 경우, 1 + 2 * 2 개의 쿼리 실행(모두 영속성 컨텍스트에 존재하지 않을 때)
