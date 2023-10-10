# 키친포스

## 요구 사항
- 메뉴 그룹
  - [ ] 메뉴 그룹을 생성할 수 있다.
  - [ ] 메뉴 그룹을 조회할 수 있다.
- 메뉴
  - [ ] 메뉴를 생성할 수 있다.
    - [ ] 가격이 널이거나 0원 미만인 경우 예외 처리
    - [ ] 없는 메뉴 그룹이면 예외 처 
    - [ ] 가격이 합계를 초과하면 예외 처리
  - [ ] 메뉴를 조회할 수 있다.
- 주문
  - [ ] 주문을 생성할 수 있다.
    - [ ] 주문 항목이 없으면 예외 처리
    - [ ] 주문 항목 사이즈가 맞지 않으면 예외 처리
    - [ ] 주문 테이블이 비어있으면 예외 처리
  - [ ] 주문을 조회할 수 있다.
  - [ ] 주문 상태를 변경할 수 있다.
    - [ ] 주문 상태가 완료 상태면 예외 처리
- 상품
  - [ ] 상품을 생성할 수 있다.
    - [ ] 가격이 널이거나 0원 미만인 경우 예외 처리
  - [ ] 상품은 조회할 수 있다.
- 단체 지정
  - [ ] 단체 지정을 생성할 수 있다.
    - [ ] 주문 테이블이 널이거나 사이즈가 2미만인 경우 예외 처리
    - [ ] 주문 테이블의 사이즈가 DB에 저장된 사이즈와 다른 경우 예외 처리
    - [ ] 주문 테이블이 비어있지 않은 경우 예외 처리
    - [ ] 주문 테이블의 단체 지정아이디가 널이 아닌 경우 예외 처리
  - [ ] 단체 지정을 풀 수 있다.
    - [ ] 주문 테이블이 존재하지 않으면 예외 처리
    - [ ] 주문 상태가 COOKING이면 예외 처리
    - [ ] 주문 상태가 MEAL이면 예외 처리
- 주문 테이블 
  - [ ] 주문 테이블을 생성할 수 있다.
  - [ ] 주문 테이블을 조회할 수 있다.
  - [ ] 주문 테이블을 비울 수 있다.
    - [ ] 주문 테이블의 단체 지정 id가 존재하지 않으면 예외 처리
    - [ ] 주문 테이블이 존재하지 않으면 예외 처리
    - [ ] 주문 상태가 COOKING이면 예외 처리
    - [ ] 주문 상태가 MEAL이면 예외 처리
  - [ ] 주문 테이블의 손님 수를 변경할 수 있다.
    - [ ] 손님의 수가 0미만인 경우 예외 처리
    - [ ] 주문 테이블이 비어있으면 예외 처리

## 용어 사전

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 상품 | product | 메뉴를 관리하는 기준이 되는 데이터 |
| 메뉴 그룹 | menu group | 메뉴 묶음, 분류 |
| 메뉴 | menu | 메뉴 그룹에 속하는 실제 주문 가능 단위 |
| 메뉴 상품 | menu product | 메뉴에 속하는 수량이 있는 상품 |
| 금액 | amount | 가격 * 수량 |
| 주문 테이블 | order table | 매장에서 주문이 발생하는 영역 |
| 빈 테이블 | empty table | 주문을 등록할 수 없는 주문 테이블 |
| 주문 | order | 매장에서 발생하는 주문 |
| 주문 상태 | order status | 주문은 조리 ➜ 식사 ➜ 계산 완료 순서로 진행된다. |
| 방문한 손님 수 | number of guests | 필수 사항은 아니며 주문은 0명으로 등록할 수 있다. |
| 단체 지정 | table group | 통합 계산을 위해 개별 주문 테이블을 그룹화하는 기능 |
| 주문 항목 | order line item | 주문에 속하는 수량이 있는 메뉴 |
| 매장 식사 | eat in | 포장하지 않고 매장에서 식사하는 것 |
