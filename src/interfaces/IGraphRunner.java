package interfaces;
import java.util.List;
import java.util.Map;

public interface IGraphRunner {
    public void printMatrix();
    public int getOrder();
    public int getLength();
    public Map<Integer, Integer> inputDegreeForEachVertece();
    public Map<Integer, Integer> exitDegreeForEachVertece();
    public List<Integer> isolatedVerticesNumber();

}
