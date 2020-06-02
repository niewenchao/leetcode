package graph.mst;

/**
 * 最小生成树权重边结构
 */
public class Edge implements Comparable<Edge> {
    private int v;
    private int w;
    private double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight(){
        return weight;
    }

    public int either(){
        return v;
    }

    public int another(int t){
        if(v == t) return w;
        else if(w == t) return v;
        else throw new RuntimeException("Inconsistent Edge");
    }

    @Override
    public int compareTo(Edge that){
        if(this.weight < that.weight) return -1;
        else if(this.weight == that.weight) return 0;
        else return 1;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "v=" + v +
                ", w=" + w +
                ", weight=" + weight +
                '}';
    }
}
