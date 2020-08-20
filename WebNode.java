package DijkistraShortestPath;



import static java.lang.Double.MAX_VALUE;

public class WebNode {

    public static final double R4 = 3.061;
    public static final double R3 = 2.296;
    public static final double R2 = 1.531;
    public static final double R1 = 0.765;

    String webNodeKey;
    String shortestPathNeighbourWebNodeKey;
    double shortestPathByDistance = MAX_VALUE;

    public WebNode(String webNodeKey){
        this.webNodeKey = webNodeKey;
    }

    String getWebNodeRadix(WebNode webNodeKey){
        return webNodeKey.webNodeKey.substring(0,1);
    }

    String getWebNodeIndex(WebNode webNodeKey){
        return webNodeKey.webNodeKey.substring(1,2);
    }

    double getDistanceBetweenWebNodes(WebNode webNodeKeyA, WebNode webNodeKeyB) {

        if ((webNodeKeyA.getWebNodeIndex(webNodeKeyA).equals("0")) || (webNodeKeyB.getWebNodeIndex(webNodeKeyB).equals("0"))) return 1.0;
        if (webNodeKeyA.getWebNodeRadix(webNodeKeyA).equals(webNodeKeyB.getWebNodeRadix(webNodeKeyB))) {
            return 1.0;
        } else
            switch (webNodeKeyA.getWebNodeIndex(webNodeKeyA)) {
                case "4":
                    return R4;
                case "3":
                    return R3;
                case "2":
                    return R2;
                case "1":
                    return R1;
            }

        return 0;
    }

    void setShortestPathByDistance(WebNode webNodeKeyA, WebNode webNodeKeyB) {

        double distance=getDistanceBetweenWebNodes(webNodeKeyA,webNodeKeyB);

        if (webNodeKeyA.shortestPathByDistance + distance < webNodeKeyB.shortestPathByDistance) {
            webNodeKeyB.shortestPathByDistance = webNodeKeyA.shortestPathByDistance + distance;
            webNodeKeyB.shortestPathNeighbourWebNodeKey = webNodeKeyA.webNodeKey;
        }
    }
}


