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
