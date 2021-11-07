package module_8;

/*Задание 5 - HashMap#
Написать свой класс MyHashMap как аналог классу HashMap.

Нужно делать с помощью односвязной Node.

Не может хранить две ноды с одинаковыми ключами одновременно.

Методы

put(Object key, Object value) добавляет пару ключ + значение
remove(Object key) удаляет пару по ключу
clear() очищает коллекцию
size() возвращает размер коллекции
get(Object key) возвращает значение(Object value) по ключу*/

import java.util.Objects;

public class MyHashMap {

    private final static int DEFAULT_COUNT_OF_BUCKETS = 16;
    private static final int MAX_SIZE = Integer.MAX_VALUE;
    private final static float DEFAULT_LOAD_FACTOR = 0.75f;
    private int size;
    private int currentCountOfBuckets;
    private int numberOfNotEmptyBuckets;
    private Node[] bucketsArray;
    private boolean[] isBucketNotEmptyArray;
    private int[] countInBucket;

    private static class Node {
        final int hash;
        final Object key;
        Object value;
        Node previous;

        public Node(Object key, Object value, Node previous) {
            this.hash = Objects.hashCode(key);
            this.key = key;
            this.value = value;
            this.previous = previous;
        }

        public Node(Object key, Object value) {
            this.hash = Objects.hashCode(key);
            this.key = key;
            this.value = value;
            this.previous = null;
        }

        public Node(Object key) {
            this.hash = Objects.hashCode(key);
            this.key = key;
            this.value = null;
            this.previous = null;
        }
    }

    public MyHashMap() {
        clear();
    }

    public void put(Node[] array, Object key, Object value) {
        if (size < MAX_SIZE) {
            int bucketIndex = findBucketIndex(key);
            Node newNode = new Node(key, value);
            putInBucket(array, bucketIndex, newNode);
            if (!isBucketNotEmptyArray[bucketIndex]) {
                numberOfNotEmptyBuckets++;
                isBucketNotEmptyArray[bucketIndex] = true;
            }
            if (numberOfNotEmptyBuckets > currentCountOfBuckets * DEFAULT_LOAD_FACTOR || countInBucket[bucketIndex] > 8) {
                resize(currentCountOfBuckets * 2);
            }
        } else {
            throw new IllegalArgumentException("Sorry, hashMap is too big.");
        }
    }

    public void put(Object key, Object value) {
        put(bucketsArray, key, value);
    }

    private int findBucketIndex(Object key) {
        return Objects.hashCode(key) % currentCountOfBuckets;
    }

    public void putInBucket(Node[] array, int bucketIndex, Node node) {             // for resizing
        Node equalNodeInBucket = findEqualNodeInBucket(array, bucketIndex, node.key);
        if (equalNodeInBucket != null) {
            equalNodeInBucket.value = node.value;
        } else {
            Node bufferNode = array[bucketIndex];
            array[bucketIndex] = node;
            node.previous = bufferNode;
            size++;
            countInBucket[bucketIndex]++;
        }
    }

    public void putInBucket(int bucketIndex, Node node) {
        putInBucket(bucketsArray, bucketIndex, node);
    }

    private Node findEqualNodeInBucket(Node[] array, int bucketIndex, Object key) { // for resizing
        if (array[bucketIndex] != null) {
            Node bufferNode = array[bucketIndex];
            do {
                if (key == null && bufferNode.key == null) {
                    return bufferNode;
                }
                if (bufferNode.key.equals(key)) {
                    return bufferNode;
                }
                bufferNode = bufferNode.previous;
                if (bufferNode == null) {
                    break;
                }
            } while (bufferNode.previous != null);
        }
        return null;
    }

    private Node findEqualNodeInBucket(int bucketIndex, Object key) {
        return findEqualNodeInBucket(bucketsArray, bucketIndex, key);
    }

    private Node findPreviousNodeInBucket(int bucketIndex, Object key) {
        if (bucketsArray[bucketIndex] != null) {
            Node previousBufferNode = bucketsArray[bucketIndex];
            Node bufferNode = previousBufferNode.previous;
            if (bufferNode != null) {
                do {
                    if (bufferNode.key.equals(key)) {
                        return previousBufferNode;
                    }
                    previousBufferNode = bufferNode;
                    bufferNode = bufferNode.previous;
                } while (bufferNode.previous != null);
            }
        }
        return null;
    }

    public Node remove(Object key) {
        int bucketIndex = findBucketIndex(key);
        Node equalNodeInBucket = findEqualNodeInBucket(bucketIndex, key);
        Node previousSameNodeInBucket = findPreviousNodeInBucket(bucketIndex, key);
        if (equalNodeInBucket == null) {
            return null;
        } else if (previousSameNodeInBucket == null) {
            bucketsArray[bucketIndex] = null;
            size--;
            countInBucket[bucketIndex]--;
            isBucketNotEmptyArray[bucketIndex] = false;
            numberOfNotEmptyBuckets--;
            if (numberOfNotEmptyBuckets < currentCountOfBuckets / 2) {
                resize(currentCountOfBuckets - currentCountOfBuckets / 4);
            }
            return equalNodeInBucket;
        } else {
            previousSameNodeInBucket.previous = equalNodeInBucket.previous;
            size--;
            countInBucket[bucketIndex]--;
            return equalNodeInBucket;
        }
    }

    public void clear() {
        this.size = 0;
        this.numberOfNotEmptyBuckets = 0;
        this.currentCountOfBuckets = DEFAULT_COUNT_OF_BUCKETS;
        this.bucketsArray = new Node[DEFAULT_COUNT_OF_BUCKETS];
        this.isBucketNotEmptyArray = new boolean[DEFAULT_COUNT_OF_BUCKETS];
        this.countInBucket = new int[DEFAULT_COUNT_OF_BUCKETS];
    }

    public int size() {
        return size;
    }

    public Object get(Object key) {
        Node equalNodeInBucket = findEqualNodeInBucket(findBucketIndex(key), key);
        return equalNodeInBucket != null ? equalNodeInBucket.value : null;
    }

    private void resize(int count) {
        if (size < MAX_SIZE * DEFAULT_LOAD_FACTOR) {
            currentCountOfBuckets = count;
            numberOfNotEmptyBuckets = 0;
            size = 0;
            Node[] newBucketsArray = new Node[currentCountOfBuckets];
            isBucketNotEmptyArray = new boolean[currentCountOfBuckets];
            for (Node bucket : bucketsArray) {
                Node bufferNode = bucket;
                while (bufferNode != null) {
                    put(newBucketsArray, bufferNode.key, bufferNode.value);
                    bufferNode = bufferNode.previous;
                }
            }
            bucketsArray = newBucketsArray;
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("MyHashMap { size=" + size + "; "
                + "currentCountOfBuckets: " + currentCountOfBuckets + "; \n");
        if (size != 0){
            for (Node bucket : bucketsArray) {
                Node bufferNode = bucket;
                while (bufferNode != null) {
                    result.append("key=")
                            .append(bufferNode.key)
                            .append(" : value=")
                            .append(bufferNode.value)
                            .append(";\n");
                    bufferNode = bufferNode.previous;
                }
            }
        } else {
            result.deleteCharAt(result.length() - 1);
            result.append((Object) null);
        }
        result.append("}");
        return result.toString();
    }
}
