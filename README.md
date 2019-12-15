# **kakaopay-tourism**

## 생태 관광 정보 서비스 개발

### 개발 환경

- Spring Boot 2.1.11
- Java 1.8
- Spring Data JPA
- Gradle
- IntelliJ IDEA
- MySQL 8.0.13
- OpenCSV



### 기능 목록
[API 문서](<https://github.com/yjll1019/kakaopay-tourism/wiki/kakaopay-tourism-API-%EB%AC%B8%EC%84%9C>)
- [x] 데이터 파일(.csv) 에서 각 레코드를 데이터베이스에 저장한다.
- [x] 생태 관광 정보 데이터를 조회/추가/수정할 수 있다.
- [x] 생태 관광지 중에 서비스 지역 컬럼에서 특정 지역에서 진행되는 프로그램명과 테마를 출력한다.
- [x] 생태 정보 데이터에 **프로그램 소개** 컬럼에서 특정 문자열이 포함된 레코드에서 서비스 지역 개수를 세어 출력한다.
- [x] 모든 레코드에 프로그램 상세 정보를 읽어와서 입력 단어의 출현빈도수를 계산하여 출력한다.
- [x] 지역명과 관광 키워드를 입력받아 관광 프로그램을 추천한다.
  - 프로그램 추천 시, **테마 컬럼, 프로그램 소개 컬럼, 프로그램 상세 소개 컬럼** 을 이용하여 가중치를 계산한다.
- [ ] JWT를 이용해 Token을 기반으로 하여 API를 호출한다.



### 빌드 및 실행 방법

```
$ git clone https://github.com/yjll1019/kakaopay-tourism.git

$ cd kakaopay-tourism

$ gradlew clean build

$ java -jar build/libs/tourism-0.0.1-SNAPSHOT.jar
```



### 문제 해결 전략

1. 데이터 파일을 읽을 때 문서 앞부분에 BOM 삽입 문제

   - 배경

     - csv 파일에 있는 레코드를 DB에 저장하기 위해서 파일을 읽어야 했다.
     - `FileDataReader` 클래스를 생성하고, 테스트를 진행하였다.
     - 같은 값을 가지는 문자열을 비교했을 때, **false** 값이 출력되었다.

   - 원인

     - 문서 처음에 BOM이 추가되어 눈으로 보았을 때 같은 문자열이지만, 문자열을 비교하면 다른 값으로 인식되었다.

       - BOM

         - 인코딩 된 문서 첫 머리에 사용되어 정확한 인코딩 방식을 알려주는 역할을 한다.

   - 해결 방안

     - `FileDataReader`  클래스에서 파일을 읽을 때, 문서 첫 머리에 삽입되는 `"\uFEFF"` 문자를 String 클래스의 replaceAll() 메서드를 사용하여, 빈 문자열로 변환해주었다.

2. 커스텀 PK 생성하기

   - 배경
     - 사전 과제 중 **서비스 지역 코드 컬럼을 추가하여 PK 로 지정한다.** 라는 요구사항이 있었다.
     - PK는 Entity를 구분하는 유일한 값이기 때문에, 다른 값들과 중복되지 않게 생성해야 한다.
   - 해결 방안
     - hibernate에서 제공하는 `@GenericGenerator` 어노테이션과 `IdentifierGenerator`  인터페이스를 사용하여 직접 PK를 생성해주었다.
     - 생성한 PK가 이미 존재한다면 다시 PK를 생성하도록 하였다.
     - 이 과정에서 PK 존재 유무를 DB에 확인하는 과정을 줄이고 싶었다. 따라서 최대한 유니크한 PK가 생성되도록 아래의 방법으로 PK를 생성하였다.
       - `Table명` + `Java에서 제공하는 UUID의 앞 4글자` + `랜덤 함수에서 반환된 값에 31을 곱한 4자리 수` 의 조합으로 PK를 생성하였다.
     - 현재는 `Program ` 엔티티만 커스텀 PK를 사용하지만, 추후에 다른 엔티티 또한 커스텀 PK를 사용할 수 있다고 생각했다.
       - PK를 생성하는 로직은 `IdGenerator` 인터페이스로 분리하였고, 해당 인터페이스를 구현한 하위 클래스에서 PK 생성 시 필요한 로직을 구현하도록 하였다.

3. 사용자의 입력을 통해 관광 프로그램 추천하기

   - 배경
     - 사용자는 `지역명`과 `관광 키워드`를 통해 관광 프로그램을 추천 받을 수 있다.
     - **테마 컬럼, 프로그램 소개 컬럼, 프로그램 상세 소개 컬럼** 을 이용하여 가중치를 계산하여 프로그램을 추천해야 한다.
   - 해결 방안
     - 사용자가 입력한 지역명이 가장 중요하다고 생각되어, 지역명과 일치하는 프로그램 중에서 가중치를 계산하였다.
     - 가중치 계산법
       - 각 컬럼마다 점수를 부여하여, 사용자가 입력한 관광 키워드와 일치하는 개수만큼 점수를 곱해주었다. 그리고 점수 합계가 가장 높은 프로그램을 추천하도록 하였다.
       - 테마 컬럼(5점), 프로그램 소개 컬럼(3점), 프로그램 상세 소개(1점)
     - 가중치를 계산법은 변경 가능성이 높다고 생각되어, `WeightStrategy` 인터페이스를 생성하고 이를 구현한 하위 클래스에서 가중치를 계산하는 방법을 정하도록 하였다. 
