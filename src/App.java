import classes.BFSImplementation.GraphBSF;
import classes.Base.GraphMatrixRunner;
import classes.DFSImplementation.GraphDFS;

import java.io.FileNotFoundException;

public class App {

    public static void main(String[] args) throws Exception {

        String filePath = "assets\\pequenoG.txt";
        int edgeNumber = 52, verticeNumber = 33, initialVerticeKey = 0, finalVerticeKey = 25;

        runGraphMatrixRunnerInConsoleInterface(filePath, verticeNumber, edgeNumber);
        runBFSInConsoleInterface(filePath, edgeNumber, verticeNumber, initialVerticeKey, finalVerticeKey);
        //runDFSInConsoleInterface(filePath, edgeNumber, verticeNumber, initialVerticeKey);
    }

    //Atividade 4 
    public static void runDFSInConsoleInterface(String filePath, int edgeNumber, int verticeNumber, int initialVerticeKey) {

        if(initialVerticeKey > 12 || initialVerticeKey < 0){
            System.out.println("Put a correct vertice key");
            return;
        }
        
        System.out.println("\n");
        printConsoleHeader();
        try {
            GraphDFS graphDFS = new GraphDFS(filePath, edgeNumber, verticeNumber);

            System.out.println("> > Vértices visitados no algoritmo DFS: ");

            graphDFS.runDFSAlgorithm(initialVerticeKey);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    // Atividade 3
    private static void runBFSInConsoleInterface(String filePath, int edgeNumber, int verticeNumber,
            int initialVerticeKey, int finalVerticeKey) {
                
        if(initialVerticeKey > 12 || initialVerticeKey < 0){
            System.out.println("Put a correct vertice key");
            return;
        }
        printConsoleHeader();
        GraphBSF graphBSF;

        try {
            graphBSF = new GraphBSF(filePath, edgeNumber, verticeNumber, initialVerticeKey);
            graphBSF.printPath(finalVerticeKey);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Atividade 2
    private static void runGraphMatrixRunnerInConsoleInterface(String filePath, int verticeNumber, int edgeNumber) {
        printConsoleHeader();
        GraphMatrixRunner graphRunner;

        try {
            graphRunner = new GraphMatrixRunner(filePath, edgeNumber, verticeNumber);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.print("\nTamanho:" + graphRunner.getLength() + " ");
        System.out.println("Ordem:" + graphRunner.getOrder());

        System.out.println("\nVertices isolados:");
        System.out.println(graphRunner.isolatedVerticesNumber().toString());

        System.out.println("\nGraus de entrada de cada vértice:");
        System.out.println(graphRunner.inputDegreeForEachVertece().toString());

        System.out.println("\nGraus de saída de cada vértice:");
        System.out.println(graphRunner.exitDegreeForEachVertece());

        System.out.println();
    }

    private static void printConsoleHeader() {
        System.out.println("* Adjacent Matrix from the current graph in file :\n");
    }
}
