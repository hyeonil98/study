# Java Structure 

자바에서는 List, Set, Map 등 다양한 컬렉션 클래스를 제공한다, 이에 대해서 하나씩 알아보고 실제로 사용해보겠습니다.

### List
List Collection은 객체를 인덱스로 관리하기 때문에 객체를 저장하면 인덱스가 부여되고 인덱스를 통해 검색 및 삭제를 제공합니다

List Collection 중에 많이 쓰이는 ArrayList, Vector, LinkedList에 대해 포스팅 하겠습니다.

#### ArrayList
ArrayList는 일반 배열과 다르게 선언시 길이를 정하지 않아도 됩니다, 또한 객체를 저장하는 것이 아닌 객체의 번지를 저장합니다
ArrayList의 선언은 다음과 같습니다

```java
import java.util.ArrayList;
// Integer 타입
List<Integer> list = new ArrayList<>();
// 모든 타입
List list = new ArrayList<>();
```

#### Vector
Vector의 경우 ArrayList와 동일한 구조를 가지고 있지만 synchronized 메소드로 구성되어 있습니다.
```java
/*
        Vector 클래스의 일부분
        synchronized 메소드로 구성됨
        멀티 쓰레드 환경에서 동일한 Vector에 접근할 수 없음을 의미
 */
public synchronized void addElement(E obj) {
    modCount++;
    add(obj, elementData, elementCount);
}

/**
 * Removes the first (lowest-indexed) occurrence of the argument
 * from this vector. If the object is found in this vector, each
 * component in the vector with an index greater or equal to the
 * object's index is shifted downward to have an index one smaller
 * than the value it had previously.
 *
 * <p>This method is identical in functionality to the
 * {@link #remove(Object)} method (which is part of the
 * {@link List} interface).
 *
 * @param   obj   the component to be removed
 * @return  {@code true} if the argument was a component of this
 *          vector; {@code false} otherwise.
 */
public synchronized boolean removeElement(Object obj) {
    modCount++;
    int i = indexOf(obj);
    if (i >= 0) {
        removeElementAt(i);
        return true;
    }
    return false;
}
```
#### LinkedList
LinkedList는 인접 객체를 체인처럼 연결하여 사용합니다, ArrayList와 다르게 인덱스가 아닌 링크 구조로 이루어져 삽입과 삭제 속도가 빠릅니다

### Set
List는 저장 순서가 유지되는 반면에 Set은 저장 순서가 유지 되지 않습니다, 또한 객체를 중복해서 저장할 수 없습니다

#### HashSet
HashSet의 경우 동등한 객체를 저장하지 않습니다, 다음은 HashSet의 저장방식입니다.
1. Input으로 들어온 객체의 hashCode() 값이 HashSet에 있는지 검사한다
2. 만일 hashCode() 값이 같다면 equals() 값을 비교해 동등한 객체인지 검사한다
3. 동등한 객체라면 저장하지 않고 2개의 조건중 하나라도 False 라면 HashSet에 저장한다

HashSet에 있는 객체를 모두 꺼내올려면 for 문 또는 iterator를 사용하면 됩니다.
```java
package structure;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HashSetExample {
    public static void main(String[] args) {
        Set<Member> members = new HashSet<>();
        members.add(new Member("member1", 30));
        members.add(new Member("member2", 40));
        members.add(new Member("member3", 20));

        // for 문을 이용한 HashSet 출력
        for (Member member : members) {
            System.out.println("member.toString() = " + member.toString());
        }

        System.out.println(" ");
        
        //iterator를 이용한 HashSet 출력
        Iterator<Member> iterator = members.iterator();
        while (iterator.hasNext()) {
            Member member = iterator.next();
            System.out.println("member.toString() = " + member.toString());
        }
    }
}

```

### Map
Map collection은 Key : value 로 구성된 엔트리 객체를 저장합니다, Python의 Dictionary와 동일한 구조를 가지고 있습니다,
키는 동일한 값을 저장할 수 없습니다, 대표적으로 HashMap이 존재합니다.

### Hashtable
Hashtable은 HashMap과 동일하지만 synchronized 메소드 이기 때문에 멀티 스레드 환경에서도 안전하게 객체를 취급할 수 있다.


### Properties
Hashtable 의 자식클래스 이다, Properties는 키와 값을 String으로 제한한 컬렉션이며 .properties 파일을 읽을 때 사용한다

```java
import java.util.Properties;

Properties properties = new Properties();
// load 메서드로 .properties 파일 로드
properties.load(~)
```

이 외에도 TreeSet, TreeMap, Stack, Queue 등 다양한 컬렉션이 존재한다.
