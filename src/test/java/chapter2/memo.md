# chapter 2
### TDD 예: 암호 검사기

##### 검사할 규칙은 다음 세 가지이다.

- 길이가 8글자 이상
- 0 부터 9 사이의 숫자를 포함
- 대문자 포함

암호 검사기는 문자열을 검사해서 규칙을 준수하는지에 따라 암호를 '약함', '보통', '강함' 으로 구분한다.

##### 첫 번째 테스트를 할 때에는 가장 쉽거나 가장 예외적인 상황을 선택해야 한다.

##### 필자가 생각한 다음 두 가지 상황

- 모든 규칙을 충족하는 경우
- 모든 조건을 충족하지 않는 경우

둘 중 어떤 것을 먼저 테스트 해야 할까?

##### 모든 규칙을 충족하는 경우 먼저 시작한다.

meetsAllCriteria_then_String

##### 두 번째 테스트 : 길이만 8글자 미만이고 나머지 조건을 충족하는 경우

meetsOrderCriteria_except_for_Length_then_Normal

##### 세 번째 테스트 : 숫자를 포함하지 않고 나머지 조건을 충족하는 경우

meetsOtherCriteria_except_for_Length_Then_Normal

##### 네 번째 테스트 : 값이 없는 경우

- 사용자가 null을 입력한 경우에는?
    - Exception을 발생시킨다.
    - 유효하지 않은 암호를 의미하는 PasswordStrength.INVALID 를 리턴한다.


##### 다섯 번째 테스트 : 대문자를 포함하지 않고 나머지 조건을 충족하는 경우

meetContainingUppercaseCriteria

##### 여섯 번째 테스트  : 길이가 8글자 이상인 조건만 충족하는 경우

세 조건 중에 길이 조건은 충족하고 나머지 두 조건은 충족하지 않았을 때 WEAK를 리턴하도록 구현해야 한다.

##### 일곱번째 테스트 : 숫자 포함 조건만 충족하는 경우

meetsOnlyLengthCriteria_Then_Weak

##### 여덟 번째 테스트 : 대문자 포함 조건만 충족하는 경우

meetsOnlyUpperCriteria_Then_Weak

##### 아홉 번째 테스트 : 아무 조건도 충족하지 않는 경우
