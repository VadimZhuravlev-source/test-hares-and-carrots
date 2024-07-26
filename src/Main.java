import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        var solution = new Solution();
//        var solution = new Solution(1, 9);
        var map = new HashMap<Integer, Integer>(5);
        map.put(5, 1);
        map.put(4, 2);
        map.put(3, 20);
//        map.put(2, 1);
//        map.put(1, 1);
        int total = solution.solve(map);
        System.out.println(total);
    }
}