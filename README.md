# Tasty-Inventory: Back-End

### 1. 개요

---

#### 1.1. 소개

쉽고 간편한 재고 관리, 맛있는 재고와 함께 시작해요! - [바로가기](https://tasty-inventory-fe.vercel.app/)

* ✅ **제작 의도**: 매일 엑셀 파일에 정리했던 재고를 맛있는 재고에서 간편하게 입력하고, 남은 수량을 예측할 수 있어요.
  
#### 1.2. 기술 스택


| Category | Used |
| --- | --- |
| Java version | Java 17 |
| Spring version | 3.2.4 |
| Cloud Computing |	AWS EC2 (Ubuntu 22.04 LTS) |
| Database | AWS RDS (Mariadb 10.6.18) |
| File Upload | AWS S3 |
| CI/CD | Github Actions, S3, CodeDeploy, Nginx |
| Monitoring  | Sentry, Slack |
| API Docs | Swagger, Notion |

### 2. 실행방법

---

#### 2.1. Config

* `application.yml` 파일 `Tasty-Inventory_BE/src/main/resources` 디렉토리에 위치

    <br>

    ![alt text](<./image/Screenshot 2024-06-19 at 10.59.19 AM.png>)

    <br>

* `build.gradle`에서 로컬 컴퓨터에 설치된 데이터베이스 종류 추가
  
  <br>

  ![alt text](<./image/Screenshot 2024-06-19 at 11.04.16 AM.png>)

* `application.yml` 파일에서 DB 설정 정보 변경
  
  <br>

  ![alt text](<./image/Screenshot 2024-06-19 at 11.07.15 AM.png>)

  <br>

#### 2.2. Project Build

```shell
 chmod a+x gradlew
./gradlew clean build -x test
```

#### 2.3. Run jar

```shell
cd ./build/libs
java -jar Tasty-Inventory_BE-0.0.1-SNAPSHOT.jar &
```


### 3. 🤝 Code Convention

---

#### 3.1 ✓ File Naming
- 파일 이름 및 클래스, 인터페이스 이름: **파스칼 케이스(Pascal Case)**
- Entity에서 사용되는 속성값들은 ? **카멜 케이스(camel Case)**
- 내부에서 사용되는 함수 및 기타 사용: **카멜 케이스(camelCase)**

#### 3.2 ✓ 인터페이스 이름에 명사/형용사 사용 [interface-noun-adj]
인터페이스(interface)의 이름은 명사/명사절로 혹은 형용사/형용사절로 짓는다.

#### 3.3 ✓ 클래스 이름에 명사 사용 [class-noun]

클래스 이름은 명사나 명사절로 짓는다.

#### 3.4 ✓ 메서드 이름은 동사/전치사로 시작 [method-verb-preposition]

메서드명은 기본적으로 동사로 시작한다.

다른 타입으로 전환하는 메서드나 빌더 패턴을 구현한 클래스의 메서드에서는 전치사를 쓸 수 있다.

#### 3.5 ✓ 상수는 대문자와 언더스코어로 구성[constant_uppercase]

"static final"로 선언되어 있는 필드일 때 상수로 간주한다.

상수 이름은 대문자로 작성하며, 복합어는 언더스코어'_'를 사용하여 단어를 구분한다.

#### 3.6 ✓ 변수에 소문자 카멜표기법 적용 [var-lower-camelcase]

상수가 아닌 클래스의 멤버변수/지역변수/메서드 파라미터에는 소문자 카멜표기법(Lower camel case)을 사용한다.

#### 3.7 ✓ 임시 변수 외에는 1 글자 이름 사용 금지 [avoid-1-char-var]

메서드 블럭 범위 이상의 생명 주기를 가지는 변수에는 1글자로 된 이름을 쓰지 않는다.

**반복문의 인덱스나 람다 표현식의 파라미터 등 짧은 범위의 임시 변수**에는 관례적으로 1글자 변수명을 사용할 수 있다.
<br><br>

### 4. 🤝 Git Convention

---


#### 4.1 Issue

모든 작업의 단위는 github에 생성된 Issue를 기준으로 합니다.

Issue의 볼륨은 최소 하나의 기능으로 합니다.

하나의 이슈를 마무리하기 전에는 특별한 상황이 아닌 이상 다른 작업에 대한 이슈를 생성하지 않습니다.

#### 4.2 PR (Pull Request)

Issue ≤ PR

하나의 이슈에 대해서 반드시 하나의 PR이 열려야하는 건 아닙니다.

원활한 코드리뷰와 리뷰에 대한 내용을 반영하기 위해서 PR은 3개의 commit을 넘어가지 않아야합니다.

하나의 PR에 3개 이상의 File Change는 지양합니다.
 

### 5. Commit

---

| 커밋 구분 | 설명 |
| --- | --- |
| Feature | (Feature) 개선 또는 기능 추가 |
| Bug | (Bug Fix) 버그 수정 |
| Doc | (Documentation) 문서 작업 |
| Test | (Test) 테스트 추가/수정 |
| Build | (Build) 빌드 프로세스 관련 수정(yml) |
| Performance | (Performance) 속도 개선 |
| Refactor | (Cleanup) 코드 정리/리팩토링 |

- 이슈번호와 함께 커밋 내용을 적는다.
- 예시 : [#1] feataure : ~