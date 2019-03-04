package DataStruct.skiplist;

public class SkipListNode<T extends Comparable<? super T>> {
    private T value;
    int level;
    SkipListNode<T>[] nodes;

    public SkipListNode(T value, int level) {
        this.value = value;
        this.level = level;
        if (level <= 0) {
            throw new IllegalArgumentException("Parameter level must be positive");
        } else {
            this.nodes = new SkipListNode[level];
        }
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
