package DataStruct.skiplist;

public class Test {

    public static void main(String[] args) {
        SkipList skipList = new SkipList<NodeValue>(4);
        skipList.insert(new NodeValue(1));
        skipList.insert(new NodeValue(5));
        skipList.insert(new NodeValue(2));
        skipList.insert(new NodeValue(3));
        skipList.insert(new NodeValue(4));
        skipList.chartDisplay();

        skipList.remove(new NodeValue(1));
        skipList.remove(new NodeValue(4));
        skipList.chartDisplay();
    }
}
