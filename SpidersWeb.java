package DijkistraShortestPath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SpidersWeb {

    Map<String, WebNode> spidersWebMap = new HashMap<>();
    final String radialList = "ABCDEFGH";
    final String trackList = "01234";

    public SpidersWeb() {

        char radialRef;
        int track;
        String webNodeKey;

        for (radialRef = 'A'; radialRef <= 'H'; radialRef++) {                  //the radials
            for (track = 1; track <= 4; track++) {                                      //the WebNodes
                webNodeKey = radialRef + String.valueOf(track);
                spidersWebMap.put(webNodeKey, new WebNode(webNodeKey));
            }
        }
        spidersWebMap.put("A0", new WebNode("A0"));  //the hub of the web
    }

    String getRadialRef(String webNodeKey) {
        return webNodeKey.substring(0, 1);
    }

    String getTrackRef(String webNodeKey) {
        return webNodeKey.substring(1, 2);
    }

    String getShortestPathString(WebNode spider, WebNode fly) {

        StringBuilder sb = new StringBuilder();

        WebNode pathNode = fly;
        sb.insert(0, fly.webNodeKey);
        while (!(pathNode.equals(spider))) {
            sb.insert(0, "-");
            sb.insert(0, spidersWebMap.get(pathNode.webNodeKey).shortestPathNeighbourWebNodeKey);
            pathNode = spidersWebMap.get(spidersWebMap.get(pathNode.webNodeKey).shortestPathNeighbourWebNodeKey);

        }

        return sb.toString();
    }

    ArrayList<String> getAdjacentWebNodes(String webNodeKey) {
        // given a specific webNodeKey return an ArrayList of adjacent
        // webNodeKeys e.g. B3 - return A3,C3,B2,B4
        // or for e.g. A0 (the hub) return A1,B1,C1,D1,E1,F1,G1,H1

        ArrayList<String> webNodeKeyList = new ArrayList<>();
        String radialRef = getRadialRef(webNodeKey);
        String trackRef = getTrackRef(webNodeKey);

        switch (trackRef) {
            case "4":
                webNodeKeyList.add(getNextWebNodeKey(radialRef, trackRef));
                webNodeKeyList.add(getLastWebNodeKey(radialRef, trackRef));
                webNodeKeyList.add(getInnerWebNodeKey(radialRef, trackRef));
                break;
            case "3":
            case "2":
            case "1":
                webNodeKeyList.add(getNextWebNodeKey(radialRef, trackRef));
                webNodeKeyList.add(getLastWebNodeKey(radialRef, trackRef));
                webNodeKeyList.add(getInnerWebNodeKey(radialRef, trackRef));
                webNodeKeyList.addAll(getOuterWebNodeKey(radialRef, trackRef));
                break;
            case "0":
                webNodeKeyList.addAll(getOuterWebNodeKey(radialRef, trackRef));
        }
        return webNodeKeyList;
    }

    String getNextWebNodeKey(String radialRef, String trackRef) {
        int nextRadialIndex = ((radialList.indexOf(radialRef) + 1) % radialList.length());
        return radialList.charAt(nextRadialIndex) + trackRef;
    }

    String getLastWebNodeKey(String radialRef, String trackRef) {
        int nextRadialIndex = ((radialList.indexOf(radialRef) + radialList.length() - 1) % radialList.length());
        return radialList.charAt(nextRadialIndex) + trackRef;
    }

    String getInnerWebNodeKey(String radialRef, String trackRef) {
        if (trackRef.equals("1")) {
            return "A0";
        } else
            return radialRef + (trackList.indexOf(trackRef) - 1);
    }

    ArrayList<String> getOuterWebNodeKey(String radialRef, String trackRef) {
        ArrayList<String> outerWebNodeKeyList = new ArrayList<>();
        if (trackRef.equals("0")) {
            outerWebNodeKeyList.add("A1");
            outerWebNodeKeyList.add("B1");
            outerWebNodeKeyList.add("C1");
            outerWebNodeKeyList.add("D1");
            outerWebNodeKeyList.add("E1");
            outerWebNodeKeyList.add("F1");
            outerWebNodeKeyList.add("G1");
            outerWebNodeKeyList.add("H1");
        } else {
            outerWebNodeKeyList.add(radialRef + (trackList.indexOf(trackRef) + 1));
        }
        return outerWebNodeKeyList;
    }
}
