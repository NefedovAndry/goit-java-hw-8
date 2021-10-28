package module_8;

/*Задание 2 - LinkedList#
Написать свой класс MyLinkedList как аналог классу LinkedList.

Нельзя использовать массив. Каждый элемент должен быть отдельным объектом-посредником (Node - нода), который хранит
ссылку на предыдущий и следующий элемент коллекции (двухсвязный список).

Методы

add(Object value) добавляет элемент в конец
remove(int index) удаляет элемент под индексом
clear() очищает коллекцию
size() возвращает размер коллекции
get(int index) возвращает элемент под индексом*/

public class MyLinkedList implements MyList {

    private static class Node {
        Node previous;
        Object element;
        Node next;

        public Node(Node previous, Object element, Node next) {
            this.previous = previous;
            this.element = element;
            this.next = next;
        }
    }

    private int currentSize = 0;
    private Node currentHeadNode = null;
    private Node currentTailNode = null;

    public MyLinkedList() {
        currentHeadNode = new Node(null, null, null);
        currentTailNode = new Node(null, null, null);
    }

    @Override
    public boolean add(Object value) {
        if (value != null) {
            if (currentSize == 0) {
                Node newNode = new Node(null, value, null);
                currentHeadNode = newNode;
                currentTailNode = newNode;
            } else if (currentSize == 1) {
                currentTailNode = new Node(currentHeadNode, value, null);
            } else {
                currentTailNode = new Node(this.currentTailNode, value, null);
            }
            currentSize++;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object remove(int index) throws IllegalArgumentException {
        if (index > currentSize || index < 0) {
            throw new IllegalArgumentException("Index is out of this list bounds");
        }
        Object result;
        if (currentSize == 1) {
            result = currentTailNode.element;
            new MyLinkedList();
        } else {
            Node removingNode = nodeSearching(index);
            result = removingNode.element;
            removingNode.previous.next = removingNode.next;
            removingNode.next.previous = removingNode.previous;
        }
        currentSize--;
        return result;
    }

    @Override
    public void clear() {
        new MyLinkedList();
        currentSize = 0;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public Object get(int index) {
        return nodeSearching(index).element;
    }

    private Node nodeSearching(int index) {
        return currentSize % index > 0 ? fromTailNodeSearching(index) : fromHeadNodeSearching(index);
    }

    private Node fromTailNodeSearching(int index) throws IllegalArgumentException {
        if (index > currentSize || index < 0) {
            throw new IllegalArgumentException("Index is out of this list bounds");
        } else {
            Node searchingNode = currentTailNode;
            for (int i = currentSize - 1; i > index; i--) {
                searchingNode = searchingNode.previous;
            }
            return searchingNode;
        }
    }

    private Node fromHeadNodeSearching(int index) throws IllegalArgumentException {
        if (index > currentSize || index < 0) {
            throw new IllegalArgumentException("Index is out of this list bounds");
        } else {
            Node searchingNode = currentHeadNode;
            for (int i = 0; i < index; i++) {
                searchingNode = searchingNode.next;
            }
            return searchingNode;
        }
    }

    @Override
    public String toString() {
        Node bufferNode = currentHeadNode;
        StringBuilder result = new StringBuilder("MyLinkedList{");
        for (int i = 0; i < currentSize; i++) {
            result.append(bufferNode.element.toString());
            result.append(", ");
            bufferNode = bufferNode.next;
        }
        result.deleteCharAt(result.length() - 1);
        result.deleteCharAt(result.length() - 1);
        result.append("}");
        return result.toString();
    }
}
