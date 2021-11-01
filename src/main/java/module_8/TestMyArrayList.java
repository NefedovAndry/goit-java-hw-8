package module_8;

public class TestMyArrayList {
    public static void main(String[] args) {
        MyList myArrayList = new MyArrayList();
        myArrayList.add(0);
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        myArrayList.add(4);
        myArrayList.add(5);
        myArrayList.add(6);
        myArrayList.add(7);
        myArrayList.add(8);
        myArrayList.add(9);
        System.out.println(myArrayList.toString());
        myArrayList.remove(6);
        myArrayList.remove(6);
        System.out.println(myArrayList.toString());
        System.out.println(myArrayList.get(5));
        myArrayList.remove(6);
        myArrayList.remove(6);
        System.out.println(myArrayList.toString());
        myArrayList.clear();
        System.out.println(myArrayList.toString());
    }
}
