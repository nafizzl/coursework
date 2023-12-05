public class Edge implements Comparable<Edge>{
    int u;
    int v;
    int weight;

    public Edge(int a, int b, int num) {
        u = a;
        v = b;
        weight = num;
    }

    public int getU() {
        return this.u;
    }

    public int getV() {
        return this.v;
    }

    public int getWeight() {
        return this.weight;
    }

    @Override
    public int compareTo(Edge other){
        return Integer.compare(this.weight, other.weight);
    }
}