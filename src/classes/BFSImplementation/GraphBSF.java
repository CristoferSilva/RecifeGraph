package classes.BFSImplementation;

import java.util.*;

import classes.Base.GraphMatrixRunner;
import classes.Common.EnumColor;
import classes.Common.GraphVertice;

public class GraphBSF extends GraphMatrixRunner {
    private List<GraphVertice> vertices;
    private GraphVertice initialVertice;

    public GraphBSF(String pathSourceCode, int edgeNumber, int verticeNumber, int initalKey) throws Exception {

        super(pathSourceCode, edgeNumber, verticeNumber);
        this.vertices = new ArrayList<GraphVertice>();
        fillVerticesList();
        BFSAlgorithm(initalKey);

    }

    private GraphVertice getVertice(int key) {
        int verticeIndex;
        GraphVertice vertice;
        verticeIndex = this.vertices.indexOf(new GraphVertice(key, EnumColor.WHITE));
        vertice = vertices.get(verticeIndex);
        return vertice;
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

    private void BFSAlgorithm(int initialKey) throws Exception {
        GraphVertice currentVertice;
        List<GraphVertice> currentAdjacentList;
        Stack<GraphVertice> stack = new Stack<GraphVertice>();

        if (this.content.length == 0)
            throw new Exception("The graph is empty");

        this.initialVertice = getVertice(initialKey);
        this.initialVertice.setColor(EnumColor.GRAY);
        this.initialVertice.setDistance(0);

        stack.push(this.initialVertice);

        while (!stack.empty()) {
            currentVertice = stack.pop();
            currentAdjacentList = parseListIntergerToGraphicVerticeList(getSucessorList(currentVertice.getKey()));
            for (GraphVertice graphVertice : currentAdjacentList) {
                if (graphVertice.getColor() == EnumColor.WHITE) {
                    graphVertice.setColor(EnumColor.GRAY);
                    graphVertice.setDistance(currentVertice.getDistance() + 1);
                    graphVertice.setPrevious(currentVertice);
                    stack.push(graphVertice);
                }

                if (!graphVertice.getPreviousList().contains(currentVertice))
                    graphVertice.setPrevious(currentVertice);

            }
            currentVertice.setColor(EnumColor.BLACK);
        }
    }

    public void printPath(int key) throws Exception {
        GraphVertice graphVertice = new GraphVertice(key, EnumColor.WHITE);

        if (!this.vertices.contains(graphVertice))
            throw new Exception("This Vertice does not exist!");

        graphVertice = getVertice(key);

        System.out.println("The shortest path from " + "(" + graphVertice.getKey() + ") " + "to" + " (" + this.initialVertice.getKey() + ") is:" );

        printPath(graphVertice);
    }

    private void printPath(GraphVertice finalVertice) {
        if (finalVertice.equals(this.initialVertice)) {
            System.out.print(this.initialVertice);
        } else {
            if (finalVertice.getPreviousVerticeWithShortestDistance() == null) {
                System.out.println("There is not a path");
            } else {
                printPath(finalVertice.getPreviousVerticeWithShortestDistance());
                System.out.print(finalVertice);
            }
        }
    }

}
