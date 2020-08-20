package DijkistraShortestPath;

import java.util.*;

public class Dinglemouse {


    public static String spiderToFly(final String spider, final String fly) {
        // Your code here
        if (spider.equals(fly)) return spider;   //nothing to do

        List<String> adjacentWebNodeKeys;
        Queue<String> adjacentWebNodeQueue = new LinkedList<>();
        String adjacentWebNode;

        SpidersWeb spidersWeb = new SpidersWeb();
        //initialise - set the shortest distance on every node of the spiders web  to MAX_VALUE

        //initialise - create a list of UNVISITED WebNodeKeys
        ArrayList<String> unVisitedWebNodeKeys = new ArrayList<>(spidersWeb.spidersWebMap.keySet());
        ArrayList<String> visitedWebNodeKeys = new ArrayList<>();

        spidersWeb.spidersWebMap.get(spider).shortestPathByDistance = 0;  //initialise - set the distance from the spider (start vertex) to the spider(start vertex) to zero

        String currentWebNode = spider;  //start at the spider

        //WHILE WEBNODES REMAIN UNVISITED
        while (!(unVisitedWebNodeKeys.isEmpty())) {

            //get neighbouring web node keys
            adjacentWebNodeKeys = spidersWeb.getAdjacentWebNodes(currentWebNode);

            // update the distance and path on the adjacent WebNodeKeys for the currentWebNode
            for (int i = 0; i <= adjacentWebNodeKeys.size() - 1; i++) {                  //for each unVisitedWebNodeKey of the current vertex
                adjacentWebNode = adjacentWebNodeKeys.get(i);
                adjacentWebNodeQueue.add(adjacentWebNode);
                spidersWeb.spidersWebMap.get(adjacentWebNode).setShortestPathByDistance(spidersWeb.spidersWebMap.get(currentWebNode), spidersWeb.spidersWebMap.get(adjacentWebNode));
            }

            // add the current WebNodeKey to the list of visited WebNodes, and take it off the unVisited WebNodes queue
            visitedWebNodeKeys.add(currentWebNode);
            unVisitedWebNodeKeys.remove(currentWebNode);

            //get the next currentWebNode to consider, make sure it hasn't been visited already
            do {
                currentWebNode = adjacentWebNodeQueue.poll();
            } while (visitedWebNodeKeys.contains(currentWebNode));

        }

        //all WebNodes have been visited and updated with shortest path from Spider, now get path from Spider to Fly

        WebNode spiderNode = spidersWeb.spidersWebMap.get(spider);
        WebNode flyNode = spidersWeb.spidersWebMap.get(fly);

        Iterator<Map.Entry<String,WebNode>> itr = spidersWeb.spidersWebMap.entrySet().iterator();
        while (itr.hasNext()){
            Map.Entry<String,WebNode> entry = itr.next();
            System.out.println("Key = "+ entry.getKey()+"  :  Value : "+spidersWeb.getShortestPathString(spiderNode,entry.getValue())+"  :  "+entry.getValue().shortestPathByDistance);
        }

        return spidersWeb.getShortestPathString(spiderNode, flyNode);
    }
}

