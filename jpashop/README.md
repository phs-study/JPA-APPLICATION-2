API 개발 기본
--------------

- 커맨드와 쿼리를 분리하라. 
  - ``Member update(Long id, String name)``
  - 위 함수는 커맨드와 조회가 함께 되고 있음.
  - ``void update(Long id, String name)``
  - 위 함수는 커맨드만 수행함. (조회를 하고 싶다면, 다시 select를 호출하라.)