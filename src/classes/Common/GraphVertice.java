package classes.Common;

import java.util.*;

public class GraphVertice {

    private Integer key;
    private int distance;
    private EnumColor color;
    private int initialTime, endTime;
    private List<GraphVertice> previous;

    public GraphVertice(Integer key, EnumColor color) {
        this.key = key;
        this.color = color;
        this.previous = new ArrayList<GraphVertice>();
    }

    public GraphVertice getPreviousVerticeWithShortestDistance() {
        if (previous.size() == 0) {
            return null;
        }

        Integer verticeKeyWithShortestDistance = this.previous.get(0).getDistance();
        Integer currentdistance = 0, verticeIndexWithShortestDistance = 0, listSize = this.previous.size();

        for (int j = 0; j < listSize; j++) {
            currentdistance = this.previous.get(j).getDistance();
            if (currentdistance < verticeKeyWithShortestDistance) {
                verticeKeyWithShortestDistance = currentdistance;
                verticeIndexWithShortestDistance = j;
            }
        }

        return this.previous.get(verticeIndexWithShortestDistance);
    }

    public GraphVertice getPreviousVerticeWithGreaterDistance() {
        if (previous.size() == 0) {
            return null;
        }

        Integer verticeKeyWithGreaterDistance = this.previous.get(0).getDistance();
        Integer currentdistance = 0, verticeIndexWithGreaterDistance = 0, listSize = this.previous.size();

        for (int j = 0; j < listSize; j++) {
            currentdistance = this.previous.get(j).getDistance();
            if (currentdistance > verticeKeyWithGreaterDistance) {
                verticeKeyWithGreaterDistance = currentdistance;
                verticeIndexWithGreaterDistance = j;
            }
        }

        return this.previous.get(verticeIndexWithGreaterDistance);
    }

    public void setColor(EnumColor color) {
        this.color = color;
    }

    public EnumColor getColor() {
        return color;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public Integer getKey() {
        return key;
    }

    public void setPrevious(GraphVertice previous) {
        this.previous.add(previous);
    }

    public List<GraphVertice> getPreviousList() {
        return this.previous;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }
    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getInitialTime() {
        return initialTime;
    }

    public void setInitialTime(int initialTime) {
        this.initialTime = initialTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GraphVertice) {
            return ((GraphVertice) obj).getKey().equals(this.getKey());
        }
        return super.equals(obj);
    }

    @Override
    public String toString() {
        String string = "{ Key: " + this.key + "} <- ";
        return string;
    }
}
