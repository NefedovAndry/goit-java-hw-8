package module_8;

public class TestMyHashMap {
    public static void main(String[] args) {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put("0", 0);
        myHashMap.put("1", 1);
        myHashMap.put("2", 2);
        myHashMap.put(null, 3);
        myHashMap.put("4", 4);
        myHashMap.put("5", 5);
        myHashMap.put("6", 6);
        myHashMap.put("7", 7);
        myHashMap.put("8", 8);
        myHashMap.put("9", 9);
        System.out.println(myHashMap);
        myHashMap.remove("6");
        myHashMap.remove("6");
        System.out.println(myHashMap);
        System.out.println(myHashMap.get("2"));
        myHashMap.put("9", 99);
        System.out.println(myHashMap);
        System.out.println(myHashMap.get("3"));
        myHashMap.clear();
        System.out.println(myHashMap);
    }
}
