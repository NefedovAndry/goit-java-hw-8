package module_8;

/*Задание 4 - Stack#
Написать свой класс MyStack как аналог классу Stack, который работает по принципу LIFO (last-in-first-out).

Можно делать либо с помощью Node либо с помощью массива.

Методы

push(Object value) добавляет элемент в конец
remove(int index) удаляет элемент под индексом
clear() очищает коллекцию
size() возвращает размер коллекции
peek() возвращает первый элемент в стеке (LIFO)
pop() возвращает первый элемент в стеке и удаляет его из коллекции*/

public class MyStack {

    private static final int MAX_SIZE = Integer.MAX_VALUE;
    private int size = 0;
    private Node headNode;
    private Node tailNode;

    private static class Node {
        final Object value;
        Node previous;

        public Node(Object value, Node previous) {
            this.value = value;
            this.previous = previous;
        }

        public Node(Object value) {
            this.value = value;
            this.previous = null;
        }

        public Node() {
            this.value = null;
            this.previous = null;
        }
    }

    public MyStack() {
        clear();
    }

    public boolean push(Object value) {
        if (size >= MAX_SIZE) {
            throw new IllegalArgumentException("Sorry, stack is too big.");
        }
        if (value != null) {
            Node newNode = new Node(value);
            if (size == 0) {
                headNode = newNode;
            } else {
                newNode.previous = tailNode;
            }
            tailNode = newNode;
            size++;
            return true;
        } else {
            throw new NullPointerException("Trying to push null-value");
        }
    }

    public Object remove(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Illegal index: " + index);
        }
        Object result;
        Node bufferNode = tailNode;
        if (index == 1) {
            result = bufferNode.value;
            clear();
        } else {
            for (int i = 0; i < (size - (index + 1)) - 1; i++) {
                bufferNode = bufferNode.previous;
            }
            result = bufferNode.previous.value;
            bufferNode.previous = bufferNode.previous.previous;
            size--;
        }
        return result;
    }

    public void clear() {
        headNode = new Node();
        tailNode = new Node();
        size = 0;
    }

    public int size() {
        return size;
    }

    public Object peek() {
        return tailNode.value;
    }

    public Object poll() {
        if (size == 0) {
            return null;
        }
        Object result = tailNode.value;
        tailNode = tailNode.previous;
        size--;
        return result;
    }

    private StringBuilder buildString (Node node, StringBuilder result) {
        if (node.previous == null) {
            return result.append(node.value)
                    .append(", ");
        }
        return buildString(node.previous, result).append(node.value)
                .append(", ");
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("MyStack {size=" + size + "; ");
        buildString(tailNode, result);
        result.deleteCharAt(result.length() - 1);
        result.deleteCharAt(result.length() - 1);
        result.append("}");
        return result.toString();
    }
}
