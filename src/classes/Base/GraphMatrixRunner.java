package classes.Base;

import java.io.*;
import java.util.*;
import interfaces.IGraphRunner;

public class GraphMatrixRunner implements IGraphRunner {
    protected int[] content;
    private int[][] adjacencyMatrix;
    private int edgeNumber, verticeNumber;

    public GraphMatrixRunner(String pathSourceCode, int edgeNumber, int verticeNumber) throws FileNotFoundException {
        super();
        this.edgeNumber = edgeNumber;
        this.verticeNumber = verticeNumber;
        this.content = new int[verticeNumber * verticeNumber];
        this.adjacencyMatrix = new int[verticeNumber][verticeNumber];
        adjustProperties(pathSourceCode);
    }

    public int getOrder() {
        return verticeNumber;
    }

    public int getLength() {
        return edgeNumber;
    }

    private void adjustProperties(String pathSourceCode) throws FileNotFoundException {
        fillContent(pathSourceCode);
        organizeVerticesAndEdges();
        printMatrix();
    }

    private void fillContent(String pathSourceCode) throws FileNotFoundException {
        String contenString = InputFileReader.returnStringFromFile(pathSourceCode);
        content = parseStringArrayToIntArray(contenString.split(" "));
    }

    public void printMatrix() {
        printMatrixHeadInConsole(this.adjacencyMatrix);
        printMatrixLinesInConsole(this.adjacencyMatrix);
    }

    private void organizeVerticesAndEdges() {
        for (int i = 2; i < content.length; i = i + 2) {
            if (i + 1 < content.length) {
                this.adjacencyMatrix[content[i]][content[i + 1]] = 1;
            }
        }
    }

    private int[] parseStringArrayToIntArray(String[] stringArray) {
        int[] contentArray = new int[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            if(!stringArray[i].isEmpty())
                contentArray[i] = Integer.parseInt(stringArray[i]);
        }
        return contentArray;
    }

    private boolean checkIfColumnsIsZero(boolean isCurrentVerticeIsolated, int k) {
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (adjacencyMatrix[i][k] != 0) {
                isCurrentVerticeIsolated = false;
                break;
            }
        }
        return isCurrentVerticeIsolated;
    }

    private boolean checkIfTheLineIsZero(boolean isCurrentVerticeIsolated, int k) {
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (adjacencyMatrix[k][i] != 0) {
                isCurrentVerticeIsolated = false;
                break;
            }
        }
        return isCurrentVerticeIsolated;
    }

    private void printMatrixLinesInConsole(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            if (i < 10) {
                System.out.print((i) + "   ");
            } else {
                System.out.print((i) + "  ");
            }
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + "  ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    private void printMatrixHeadInConsole(int[][] matrix) {
        System.out.print("  ");
        for (int i = 0; i < matrix.length; i++) {
            if (i < 10) {
                System.out.print("  " + (i));
                continue;
            }
            System.out.print(" " + (i));
        }
        System.out.println();
    }

    public Map<Integer, Integer> inputDegreeForEachVertece() {
        Map<Integer, Integer> inputDegrees = new Hashtable<Integer, Integer>();

        int counter = 0;
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                if (adjacencyMatrix[j][i] == 1) {
                    counter++;
                }
            }
            inputDegrees.put(i, counter);
            counter = 0;
        }

        return inputDegrees;
    }

    public Map<Integer, Integer> exitDegreeForEachVertece() {
        Map<Integer, Integer> inputDegrees = new Hashtable<Integer, Integer>();

        int counter = 0;
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                if (adjacencyMatrix[i][j] == 1) {
                    counter++;
                }
            }
            inputDegrees.put(i, counter);
            counter = 0;
        }

        return inputDegrees;
    }

    public List<Integer> isolatedVerticesNumber() {
        List<Integer> isolatedVerticesList = new ArrayList<Integer>();
        boolean isCurrentVerticeIsolated = true;

        for (int i = 0; i < adjacencyMatrix.length; i++) {

            isCurrentVerticeIsolated = checkIfTheLineIsZero(isCurrentVerticeIsolated, i);

            if (isCurrentVerticeIsolated)
                isCurrentVerticeIsolated = checkIfColumnsIsZero(isCurrentVerticeIsolated, i);

            if (isCurrentVerticeIsolated)
                isolatedVerticesList.add(i);
        }
        return isolatedVerticesList;
    }
    
    public List<Integer> getAdjacentList(Integer key) {
        List<Integer> adjacentList  = new ArrayList<Integer>();

        //for Nexts
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (adjacencyMatrix[key][i] == 1) {
                adjacentList.add(i);
            }
        }
        //for arrowed
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (adjacencyMatrix[i][key] == 1 && !adjacentList.contains(i)) {
                adjacentList.add(i);
            }
        }
        return adjacentList;
    }
    public List<Integer> getArrowedList(Integer key) {
        List<Integer> adjacentList  = new ArrayList<Integer>();
        //for arrowed
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (adjacencyMatrix[i][key] == 1 && !adjacentList.contains(i)) {
                adjacentList.add(i);
            }
        }
        return adjacentList;
    }
    public List<Integer> getSucessorList(Integer key) {
        List<Integer> sucessorsList  = new ArrayList<Integer>();

        //for Nexts
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (adjacencyMatrix[key][i] == 1) {
                sucessorsList.add(i);
            }
        }

        return sucessorsList;
    }
}
