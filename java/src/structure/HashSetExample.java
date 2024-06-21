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
