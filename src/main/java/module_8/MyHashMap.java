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
    private int currentSize;
    private int currentCountOfBuckets;
    private int numberOfNotEmptyBuckets;
    private Node[] bucketsArray;
    private boolean[] isEmptyBucketArray;

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
        this.currentSize = 0;
        this.numberOfNotEmptyBuckets = 0;
        this.currentCountOfBuckets = DEFAULT_COUNT_OF_BUCKETS;
        this.bucketsArray = new Node[DEFAULT_COUNT_OF_BUCKETS];
        this.isEmptyBucketArray = new boolean[DEFAULT_COUNT_OF_BUCKETS];
        booleanArrayReload(isEmptyBucketArray);
    }

    private void booleanArrayReload(boolean[] array) {
        for (boolean item : array) {
            item = true;
        }
    }

    public void put(Node[] array, Object key, Object value) {
        if (currentSize < MAX_SIZE) {
            Node newNode = new Node(key, value);
            putInBucket(array, findBucketIndex(key), newNode);
            currentSize++;
            if (isEmptyBucketArray[findBucketIndex(key)]) {
                numberOfNotEmptyBuckets++;
                isEmptyBucketArray[findBucketIndex(key)] = false;
            }
            if (numberOfNotEmptyBuckets > currentCountOfBuckets * DEFAULT_LOAD_FACTOR) {
                resize(currentCountOfBuckets * 2);
            }
        } else {
            throw new IllegalArgumentException("Sorry, hashMap too big.");
        }
    }

    public void put(Object key, Object value) {
        put(bucketsArray, key, value);
    }

    private int findBucketIndex(Object key) {
        return Objects.hashCode(key) % currentCountOfBuckets;
    }

    public void putInBucket(Node[] array, int bucketIndex, Node node) {             // for resizing
        Node sameNodeInBucket = findEqualNodeInBucket(array, bucketIndex, node.key);
        if (sameNodeInBucket != null) {
            sameNodeInBucket.value = node.value;
        } else {
            Node bufferNode = array[bucketIndex];
            array[bucketIndex] = node;
            node.previous = bufferNode;
        }
    }

    public void putInBucket(int bucketIndex, Node node) {
        putInBucket(bucketsArray, bucketIndex, node);
    }

    private Node findEqualNodeInBucket(Node[] array, int bucketIndex, Object key) { // for resizing
        if (array[bucketIndex] != null) {
            Node bufferNode = array[bucketIndex];
            while (bufferNode.previous != null) {
                if (bufferNode.key.equals(key)) {
                    return bufferNode;
                }
                bufferNode = bufferNode.previous;
            }
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
                while (bufferNode.previous != null) {
                    if (bufferNode.key.equals(key)) {
                        return previousBufferNode;
                    }
                    previousBufferNode = bufferNode;
                    bufferNode = bufferNode.previous;
                }
            }
        }
        return null;
    }

    public Node remove(Object key) {
        Node sameNodeInBucket = findEqualNodeInBucket(findBucketIndex(key), key);
        Node previousSameNodeInBucket = findPreviousNodeInBucket(findBucketIndex(key), key);
        if (sameNodeInBucket != null && previousSameNodeInBucket != null) {
            previousSameNodeInBucket.previous = sameNodeInBucket.previous;
            currentSize--;
            return sameNodeInBucket;
        } else if (previousSameNodeInBucket == null) {
            bucketsArray[findBucketIndex(key)] = null;
            currentSize--;
            isEmptyBucketArray[findBucketIndex(key)] = true;
            numberOfNotEmptyBuckets--;
            if (numberOfNotEmptyBuckets < currentCountOfBuckets / 2) {
                resize(currentCountOfBuckets - currentCountOfBuckets / 4);
            }
        }
        return sameNodeInBucket;
    }

    public void clear() {
        new MyHashMap();
    }

    public int size() {
        return currentSize;
    }

    public Object get(Object key) {
        Node sameNodeInBucket = findEqualNodeInBucket(findBucketIndex(key), key);
        return sameNodeInBucket != null ? sameNodeInBucket.value : null;
    }

    private void resize(int count) {
        currentCountOfBuckets = count;
        numberOfNotEmptyBuckets = 0;
        Node[] newBucketsArray = new Node[currentCountOfBuckets];
        isEmptyBucketArray = new boolean[currentCountOfBuckets];
        booleanArrayReload(isEmptyBucketArray);
        for (Node bucket : bucketsArray) {
            Node bufferNode = bucket;
            while (bufferNode != null) {
                put(newBucketsArray, bufferNode.key, bufferNode.value);
                bufferNode = bufferNode.previous;
            }
        }
        bucketsArray = newBucketsArray;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("MyHashMap { size=" + currentSize + "; \n");
        for (Node bucket : bucketsArray) {
            Node bufferNode = bucket;
            while (bufferNode != null) {
                result.append("key=")
                        .append(bufferNode.key.toString())
                        .append(" : value=")
                        .append(bufferNode.value.toString())
                        .append(";\n");
                bufferNode = bufferNode.previous;
            }
        }
        result.append("}");
        return result.toString();
    }
}
