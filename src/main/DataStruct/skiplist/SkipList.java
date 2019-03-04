package DataStruct.skiplist;

import java.util.Random;

public class SkipList<T extends Comparable<? super T>> {
    private int max_level;
    private SkipListNode<T> head;
    private SkipListNode<T> tail;
    private int node_count;
    private Random random;

    public SkipList(int max_level) {
        this.max_level = max_level;
        if (max_level <= 0) {
            throw new IllegalArgumentException("Parameter max_level must be positive");
        } else {
            head = new SkipListNode<>(null, max_level);
            tail = new SkipListNode<>(null, max_level);
            for (int i = 0; i < max_level; i++) {
                head.nodes[i] = tail;
            }
        }
        node_count = 0;
        random = new Random();
    }

    public boolean isEmpty() {
        return node_count == 0;
    }

    public int getSize() {
        return node_count;
    }

    private SkipListNode<T> find(T value) {
        SkipListNode<T> tempNode = head;
        int level = head.level - 1;
        while (level >= 0) {
            while (tempNode.nodes[level] != tail && tempNode.nodes[level].getValue().compareTo(value) <= 0) {
                if (tempNode.nodes[level].getValue().compareTo(value) == 0) {
                    return tempNode.nodes[level];
                } else {
                    tempNode = tempNode.nodes[level];
                }
            }
            level--;
        }
        return null;
    }
    private int getRandomLevel() {
        int initLevel = 1;
        while (initLevel < max_level) {
            if (random.nextInt(2) == 0) {
                initLevel++;
            } else {
                break;
            }
        }
        return initLevel;
    }

    public SkipListNode<T> insert(T value) {
        SkipListNode<T> node = find(value);
        if (node != null) {
            return node;
        } else {
            int level = getRandomLevel();
            SkipListNode[] preNodes = new SkipListNode[level];
            for (int i = level - 1; i >= 0; i--) {
                SkipListNode<T> tempNode = head;
                while (tempNode.nodes[i] != tail && tempNode.nodes[i].getValue().compareTo(value) < 0) {
                    tempNode = tempNode.nodes[i];
                }
                preNodes[i] = tempNode;
            }
            SkipListNode<T> newNode = new SkipListNode<>(value, level);
            for (int i = 0; i< level; i++) {
                newNode.nodes[i] = preNodes[i].nodes[i];
                preNodes[i].nodes[i] = newNode;
            }
            node_count++;
            return newNode;
        }
    }

    public void remove(T value) {
        SkipListNode<T> tempNode = head;
        int level = head.level - 1;
        while (level >= 0) {
            while (tempNode.nodes[level] != tail && tempNode.nodes[level].getValue().compareTo(value) <= 0) {
                if (tempNode.nodes[level].getValue().compareTo(value) == 0) {
                    SkipListNode<T> temp = tempNode.nodes[level];
                    tempNode.nodes[level] = tempNode.nodes[level].nodes[level];
                    temp.nodes[level] = null;
                    if (level == 0) {
                        node_count--;
                    }
                    break;
                }
                tempNode = tempNode.nodes[level];
            }
            level--;
        }
    }

    public void chartDisplay() {
        int level = head.level - 1;
        while (level >= 0) {
            SkipListNode<T> tempNode = head;
            System.out.print("HEAD----");
            while (tempNode.nodes[level] != tail) {
                System.out.print(tempNode.nodes[level].getValue().toString() + "----");
                tempNode = tempNode.nodes[level];
            }
            System.out.print("TAIL\n");
            level--;
        }
    }
}
