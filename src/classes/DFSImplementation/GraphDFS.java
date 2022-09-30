package classes.DFSImplementation;

import java.io.FileNotFoundException;
import java.util.*;

import classes.Base.GraphMatrixRunner;
import classes.Common.*;

public class GraphDFS extends GraphMatrixRunner {
    private int timer;
    private List<GraphVertice> vertices;
    
    public GraphDFS(String pathSourceCode, int edgeNumber, int verticeNumber) throws FileNotFoundException {
        super(pathSourceCode, edgeNumber, verticeNumber);
        this.vertices = new ArrayList<GraphVertice>();
        fillVerticesList();
    }

    public void fillVerticesList() {
        int counter = 0;
        GraphVertice currentVertice;

        for (int currentInt : this.content) {
            currentVertice = new GraphVertice(currentInt, EnumColor.WHITE);
            if (!this.vertices.contains(currentVertice) && counter > 1)
                this.vertices.add(currentVertice);

            counter++;
        }
    }

    public void runDFSAlgorithm(int initialKey) {
        DFS_Start(initialKey);
    }

    private void DFS_Start(int initalKey) {
        GraphVertice initialVertice = getVertice(initalKey);

        for (GraphVertice currentGraphVertice : vertices) {
            currentGraphVertice.setColor(EnumColor.WHITE);
            currentGraphVertice.setInitialTime(-1);
            currentGraphVertice.setEndTime(-1);
        }
        this.timer = 1;

        DFS_Visit(initialVertice);
    }

    private void DFS_Visit(GraphVertice vertice) {
        printVertice(vertice);
        vertice.setColor(EnumColor.GRAY);
        this.timer++;
        vertice.setInitialTime(this.timer);

        List<GraphVertice> currentAdjacentList = parseListIntergerToGraphicVerticeList(getAdjacentList(vertice.getKey()));
        for (GraphVertice currentVertice : currentAdjacentList) {
            if (currentVertice.getColor() == EnumColor.WHITE) {
                currentVertice.setPrevious(vertice);
                DFS_Visit(currentVertice);
            }
        }
        vertice.setColor(EnumColor.BLACK);
        this.timer++;
        vertice.setEndTime(this.timer);
    }
    private GraphVertice getVertice(int key) {
        int verticeIndex;
        GraphVertice vertice;
        verticeIndex = this.vertices.indexOf(new GraphVertice(key, EnumColor.WHITE));
        vertice = vertices.get(verticeIndex);
        return vertice;
    }

    public List<GraphVertice> parseListIntergerToGraphicVerticeList(List<Integer> verticesInIntergerForm) {
        GraphVertice currentVertice;
        List<GraphVertice> adjacentsVertices = new ArrayList<GraphVertice>();

        for (Integer integerVertice : verticesInIntergerForm) {
            currentVertice = new GraphVertice(integerVertice, EnumColor.WHITE);
            if (this.vertices.contains(currentVertice)) {
                currentVertice = getVertice(currentVertice.getKey());
                adjacentsVertices.add(currentVertice);
            }
        }
        return adjacentsVertices;
    }

    private void printVertice(GraphVertice vertice) {
        System.out.println(vertice.toString());
    }
    
}
