# 스타트링크 문제풀이

----
스타트링크는 총 F층으로 이루어진 고층 건물에 사무실이 있고, 스타트링크가 있는 곳의 위치는 G층이다. 강호가 지금 있는 곳은 S층이고, 이제 엘리베이터를 타고 G층으로 이동하려고 한다.

보통 엘리베이터에는 어떤 층으로 이동할 수 있는 버튼이 있지만, 강호가 탄 엘리베이터는 버튼이 2개밖에 없다. U버튼은 위로 U층을 가는 버튼, D버튼은 아래로 D층을 가는 버튼이다. (만약, U층 위, 또는 D층 아래에 해당하는 층이 없을 때는, 엘리베이터는 움직이지 않는다)

강호가 G층에 도착하려면, 버튼을 적어도 몇 번 눌러야 하는지 구하는 프로그램을 작성하시오. 만약, 엘리베이터를 이용해서 G층에 갈 수 없다면, "use the stairs"를 출력한다.

----
## 문제 해설
S층에서 출발하여 G층으로 가는 최단거리를 구하는 문제이다, 위 로는 U층 아래로는 D층 이동할 수 있다, S층에서 출발하여 특정 N층 까지의 최소 거리를
cost 배열이라고 한다면 cost[g]를 리턴하면 되는 문제이기에 탐색 알고리즘을 사용하여야 한다.

```java
package org.bfs;

import java.util.*;
import java.util.stream.IntStream;

public class Boj5014 {
    // 큐에 저장하기 위한 클래스
    public static class Point{
        int stair;
        int depth;

        public Point(int stair, int depth) {
            this.stair = stair;
            this.depth = depth;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Queue<Point> queue = new ArrayDeque<>();
        int[] cost = new int[1000001];
        Arrays.fill(cost, 100000000);

        int f = Integer.parseInt(sc.next());
        int s = Integer.parseInt(sc.next());
        int g = Integer.parseInt(sc.next());
        int u = Integer.parseInt(sc.next());
        int d = Integer.parseInt(sc.next());
        int answer = 0;
        
        // 도착할 곳이 같다면 탐색할 필요가 없음
        if(s == g){System.out.println(0);}
        else {
            queue.add(new Point(s, 0));
            while (!queue.isEmpty()) {
                Point poll = queue.poll();
                
                // 위층과 아래층 탐색
                int upStair = poll.stair + u;
                int downStair = poll.stair - d;
                int depth = poll.depth;
                
                // 탐색 종료
                if (upStair == g || downStair == g) {
                    answer = depth + 1;
                    break;
                }

                
                if (upStair <= f && cost[upStair] > depth) {
                    cost[upStair] = depth;
                    queue.add(new Point(upStair, depth + 1));
                }
                if (downStair >= 1 && cost[downStair] > depth) {
                    cost[downStair] = depth;
                    queue.add(new Point(downStair, depth + 1));
                }
            }
            if (answer != 0) {
                System.out.println(answer);
            } else {
                System.out.println("use the stairs");
            }
        }
        sc.close();

    }
}

```

