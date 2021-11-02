package module_8;

public class TestMyLinkedList {
    public static void main(String[] args) {
        MyList myLinkedList = new MyLinkedList();
        myLinkedList.add(0);
        myLinkedList.add(1);
        myLinkedList.add(2);
        myLinkedList.add(3);
        myLinkedList.add(4);
        myLinkedList.add(5);
        myLinkedList.add(6);
        myLinkedList.add(7);
        myLinkedList.add(8);
        myLinkedList.add(9);
        System.out.println(myLinkedList);
        myLinkedList.remove(6);
        myLinkedList.remove(6);
        System.out.println(myLinkedList);
        System.out.println(myLinkedList.get(6));
        myLinkedList.remove(6);
        myLinkedList.remove(6);
        System.out.println(myLinkedList);
        myLinkedList.remove(0);
        myLinkedList.remove(0);
        myLinkedList.remove(0);
        myLinkedList.remove(0);
        myLinkedList.remove(0);
        myLinkedList.remove(0);
        myLinkedList.remove(0);
        System.out.println(myLinkedList);
        myLinkedList.clear();
        System.out.println(myLinkedList);
    }
}
