## JAVA2 학습 정리  
## 202330114 백승진  

## JAVA2 학습 정리  
## 202330114 백승진  

---

## 6주차 (4월 8일)

### 배열의 크기 length 필드
- 자바의 배열은 객체로 처리  
- 배열 크기는 배열 객체의 length 필드에 저장  

```java
for(int i = 0; i < intArray.length; i++){
    // 반복 작업
}
```

---

### 함수 호출 시 배열 전달
- C/C++ : 배열 + 크기 같이 전달  
- Java : 배열만 전달  

---

### 배열과 for-each 문
```java
int[] n = {1,2,3,4,5};
int sum = 0;

for(int k : n){
    sum += k;
}
```

---

### 메소드의 배열 리턴
- 배열 전체가 아닌 레퍼런스만 리턴  

---

## 5주차 Java 예제 실습

### condition.java
- 삼항 연산자 사용  
- 조건 ? 참 : 거짓  

---

### 비트 연산
- AND : &  
- OR : |  
- XOR : ^  
- NOT : ~  

---

### 비트 시프트 연산
- >> : 산술적 오른쪽 시프트  
- >>> : 논리적 오른쪽 시프트  
- << : 왼쪽 시프트  

---

### 조건문 if-else
```java
if(조건1){
    실행문
}
else if(조건2){
    실행문
}
else{
    실행문
}
```

---

### 조건문 switch
```java
switch(식){
    case 값1:
        실행문
        break;
    case 값2:
        실행문
        break;
    default:
        실행문
}
```

---

### 반복문

#### for문
```java
for(int i=0; i<10; i++){
    System.out.println(i);
}
```

#### while문
```java
int i = 0;
while(i < 10){
    System.out.println(i);
    i++;
}
```

#### do-while문
```java
do{
    실행문
} while(조건식);
```

---

### 제어문
- continue : 다음 반복  
- break : 반복 종료  

---

### 배열
```java
int[] arr = {1,2,3,4,5};
```

---

## 4주차 Java 심화 개념

### 메모리 구조
- Heap : 객체 저장  
- Stack : 변수, 함수 실행  

---

### 자료형
- 기본 타입 : int, double 등  
- 참조 타입 : 배열, 클래스  

---

### 변수와 상수
- 변수 : 변경 가능  
- 상수 : final 사용  

---

### 출력
- print()  
- println()  
- printf()  

---

### 타입 변환
- 자동 변환  
- 강제 변환 `(int)`  

---

### 입력
- System.in  
- Scanner  

---

### 연산자
- 산술, 비교, 논리  

---

## 3주차 Java 기초 이론

### 프로그래밍 언어
- 컴퓨터에게 명령을 내리는 도구  

---

### 절차 지향 vs 객체 지향
- 절차 : 순서 중심  
- 객체 : 객체 중심  

---

### 컴파일 과정
- .java → .class  

---

### Java 특징
- 플랫폼 독립성  
- 객체 지향  
- 자동 메모리 관리  

---

### JDK / JRE
- JDK : 개발 도구 포함  
- JRE : 실행 환경  

---

### 실행 도구
- javac, java, jar 등  

---

## 2주차 Git 및 GitHub 기초

### Repository
- 프로젝트 저장 공간  

---

### Branch
- 독립 작업 공간  

---

### 기본 명령어
```bash
git init
git add .
git commit -m "설명"
```

---

### 원격 저장소
```bash
git clone 저장소주소
git push origin main
```

---

### 설정
```bash
git config --global user.name "이름"
git config --global user.email "이메일"
```

---

### 작업 흐름
```bash
git add .
git commit -m "수정"
git push
```

---

## 전체 요약
- Git으로 버전 관리  
- Java 기본 문법 이해  
- 배열, 반복문, 조건문 학습  
- 실습을 통한 개념 적용  