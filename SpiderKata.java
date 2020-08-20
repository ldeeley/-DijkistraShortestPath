package DijkistraShortestPath;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class SpiderKata {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {


        String spiderNode;
        String flyNode;
        String shortestPath;

        System.out.println("Enter a WebNode Ref for the Spider : ");
        spiderNode = reader.readLine();

        System.out.println("Enter a WebNode Ref for the Fly : ");
        flyNode = reader.readLine();

        shortestPath = Dinglemouse.spiderToFly(spiderNode,flyNode);
        System.out.println("The shortest path is : "+shortestPath);

    }
}
