package DataStruct.skiplist;

public class NodeValue implements Comparable<NodeValue> {
    int value;

    public NodeValue(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(NodeValue o) {
        return value - o.value;
    }

    @Override
    public String toString() {
        return "" + value;
    }
}
