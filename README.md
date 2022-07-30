환경 변수로 설정값 전달하기

```
$ SPRING_APPLICATION_JSON='{"acme":{"name":"test"}}' java -jar myapp.jar
```

시스템 속성으로 설정값 전달하기

```
$ java - Dspring.application.json='{"name":"test"}' -jar myapp.jar
```

명령줄 인수로 설정값 전달하기

```
$ java -jar myapp.jar --spring.application.json='{"name":"test"}'
```

인텔리제이의 Run Configuration 창에서 인수 지정하기

```
Arguments: -Pargs="--job=importStaffJob"
```

배치 작업명을 인수로 전달하기(build.gradle)

```groovy
project(":sample-batch") {
    bootRun {
        // 프로젝트 속성을 인수로 건넨다.
        if (project.hasProperty("args")) {
            args project.args.split("\\s+")
        }
    }
}
```

## 멀티프로젝트

- 빌드 스크립트를 공통화할 수 있어 작성량이 줄어든다
- 로컬/원격 저장소에 artifact를 업로드하지 않고도 소스 코드의 변경이 반영된다
- 각각의 프로젝트를 관련지어 작업을 실행할 수 있다

### 예제 프로젝트의 구성

- `build.gradle`: 부모 프로젝트의 빌드 스크립트
- `sample-common`: 공통으로 사용하는 유틸리티를 관리하는 모듈
- `sample-domain`: 도메인 객체를 관리하는 모듈
- `sample-web-base`: 웹 모듈의 공통 기능을 관리하는 모듈
- `sample-web-front`: 최종 사용자용 웹 애플리케이션
- `sample-web-admin`: 운영자용 웹 애플리케이션
- `sample-batch`: 정기 실행 배치를 관리하는 모듈

## 애플리케이션 아키텍처

예제 프로젝트의 애플리케이션은 다음과 같은 층으로 이루어진 계층형 아키텍처이다

### 프레젠테이션 층

프레젠테이션 층은 입력된 값을 받아 값을 확인하거나 값의 변환을 실시하는 층으로,  
웹 모듈의 Form 클래스, FormValidator 클래스가 이에 해당한다

### 애플리케이션 층

애플리케이션 층은 프레젠테이션 층에서 받은 값을 도메인 층에 전달하는 층으로 웹 모듈의 Controller가 이에 해당한다
비즈니스 로직은 포함하지 않지만 화면 전환을 제어하거나 세션을 사용하여 다음 화면에 값을 전달한다

### 도메인 층

도메인 층은 도메인 객체를 가지고 비즈니스 로직을 처리하는 메인 층으로 도메인 모듈의 Service 클래스가 이에 해당한다

도메인 객체는 모든 계층에서 사용되지만, 반대로 도메인 층은 다른 계층에 의존해서는 안 된다는 점에 주의해야 한다

### 인프라스트럭처 층

인프라스트럭처 층은 도메인 계층에서 전달된 데이터를 영속화하는 층으로 도메인 모듈 Repository 클래스가 이에 해당한다  
애플리케이션 계층의 영향을 받지 않도록 범용적인 부품으로 만들어야 한다