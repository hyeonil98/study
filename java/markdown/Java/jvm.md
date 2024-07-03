# JVM 

----
JVM은 Java Virtual Machine의 약자로 자바 가상 머신이라고 부릅니다.


### JVM의 동작과정
1. 자바 프로그램이 실행되면 JVM은 OS으로 부터 메모리를 할당 받습니다.
2. 자바 컴파일러인 javac가 자바 파일을 자바 바이트 코드로 변환합니다
3. Class Loader는 동적 로딩을 통해 필요한 클래스들을 로딩 및 링크 하여 Runtime Data Area에 올립니다.
4. Runtime Data Area에 로딩 된 바이트 코드는 Execution Engine을 통해 해석됩니다.
5. Execution Engine에 의해 Garbage Collector의 작동과 Thread 동기화가 이루어 집니다.


### 클래스 로더
JVM내의 클래스 파일을 로드하고 링크를 통해 Runtime Date Areas에 배치하는 작업을 수행하는 모듈입니다.

클래스 파일 로딩 순서
1. Loading : JVM 메모리에 로드
2. Linking : 클래스 파일을 사용하기 위한 검증 과정
   1. Verifying
   2. Preparing
   3. Resolving
3. Initialization : 클래스 변수를 적절한 값으로 초기화

### Execution Engine
클래스 로더를 통해 런타임 데이터 영역에 배채된 바이트 코드를 명령어 단위로 읽어서 실행


### 가비지 컬렉터
JVM 에서는 가비지 컬렉터를 이용하여 Heap 메모리 영역에서 더이상 사용되지 않는 메모리를 자동으로 회수 합니다.
C언어 같은 경우 직접 개발자가 메모리를 해제해야 하지만, Java 에서는 가비지 컬렉터를 이용해 자동으로 메모리를 최적화 시켜주므로 
더욱 손쉽게 프로그래밍 할 수 있습니다.
