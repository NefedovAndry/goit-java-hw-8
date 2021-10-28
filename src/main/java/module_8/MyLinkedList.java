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

        public Node(Node previous, Object element) {
            this.previous = previous;
            this.element = element;
        }
    }

    private int currentSize = 0;
    private Node currentNode = null;

    public MyLinkedList() {
        currentNode = new Node(null, null);
        currentSize = 0;
    }

    @Override
    public boolean add(Object value) {
        if (value != null) {
            currentNode = new Node(this.currentNode, value);
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
            result = currentNode.element;
            currentNode.previous = null;
            currentNode.element = null;
        } else {
            Node removingNode = nodeSearching(index);
            Node nodeAfterRemovingNode = nodeSearching(index + 1);
            result = removingNode.element;
            nodeAfterRemovingNode.previous = removingNode.previous;
        }
        currentSize--;
        return result;
    }

    @Override
    public void clear() {
        new MyLinkedList();
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public Object get(int index) {
        return nodeSearching(index).element;
    }

    private Node nodeSearching(int index) throws IllegalArgumentException {
        if (index > currentSize || index < 0) {
            throw new IllegalArgumentException("Index is out of this list bounds");
        } else {
            Node searchingNode = currentNode;
            for (int i = currentSize; i > index; i--) {
                searchingNode = searchingNode.previous;
            }
            return searchingNode;
        }
    }

    public Object[] toArray() {
        Object[] resultArray = new Object[currentSize];
        Node searchingNode = currentNode;
        for (int i = currentSize - 1; i > 0; i--) {
            resultArray[i] = searchingNode.element;
            searchingNode = searchingNode.previous;
        }
        return resultArray;
    }

    @Override
    public String toString() {
        Object[] array = toArray();
        StringBuilder result = new StringBuilder("MyLinkedList{");
        for (int i = 0; i < currentSize; i++) {
            result.append(array[i].toString());
            result.append(", ");
        }
        result.deleteCharAt(result.length() - 1);
        result.deleteCharAt(result.length() - 1);
        result.append("}");
        return result.toString();
    }
}
