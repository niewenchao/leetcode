package graph.sp;

/**
 * 加权有向边
 */
public class DirectedEdge implements Comparable<DirectedEdge> {
    private int v;
    private int w;
    private double weight;

    public DirectedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight(){
        return weight;
    }

    public int from(){
        return v;
    }

    public int to(){
        return w;
    }

    @Override
    public int compareTo(DirectedEdge that){
        if(this.weight < that.weight) return -1;
        else if(this.weight == that.weight) return 0;
        else return 1;
    }

    @Override
    public String toString() {
        return v + "->" + w;
    }
}
