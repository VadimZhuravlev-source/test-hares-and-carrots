import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Solution {

    private final int numberOfApproach;
    private final int weightHareCanCarry;
    private Map<Integer, Integer> mapWeightAndQuantityOfCarrot;

    public Solution() {
        this.numberOfApproach = 10;
        this.weightHareCanCarry = 5;
    }

    public Solution(int numberOfApproach, int weightHareCanCarry) {
        this.numberOfApproach = numberOfApproach;
        this.weightHareCanCarry = weightHareCanCarry;
    }

    public int solve(Map<Integer, Integer> mapWeightAndQuantityOfCarrot) {

        this.mapWeightAndQuantityOfCarrot = mapWeightAndQuantityOfCarrot;
        if (mapWeightAndQuantityOfCarrot == null)
            return 0;

        int maxCarrotWeight = mapWeightAndQuantityOfCarrot.keySet().stream().max(Integer::compareTo).orElse(0);
        int total = 0;
        int currentApproach = numberOfApproach;
        while (currentApproach > 0) {
            List<Integer> path = maxPathByLevel(maxCarrotWeight, weightHareCanCarry);
            path.forEach(integer -> {
                Integer weight = mapWeightAndQuantityOfCarrot.get(integer);
                mapWeightAndQuantityOfCarrot.put(integer, --weight);
            });
            total = total + path.stream().reduce(Integer::sum).orElse(0);
            currentApproach--;
        }

        return total;

    }

    private List<Integer> maxPathByLevel(int maxCarrotWeight, int foundSum) {
        int level = maxCarrotWeight;
        int maxSum = 0;
        List<Integer> maxPath = new ArrayList<>();
        while (level > 0) {
            List<Integer> path = getPathClosedToFoundSum(level, foundSum);
            int sum = path.stream().reduce(Integer::sum).orElse(0);
            if (sum == foundSum) {
                return path;
            }
            if (sum > maxSum) {
                maxSum = sum;
                maxPath = path;
            }
            level--;
        }
        return maxPath;
    }

    private List<Integer> getPathClosedToFoundSum(int maxCarrotWeight, int foundSum) {

        int currentSum = foundSum;
        List<Integer> path = new ArrayList<>();

        Integer numberOfCarrots = mapWeightAndQuantityOfCarrot.get(maxCarrotWeight);

        if (numberOfCarrots != null) {
            while (currentSum - maxCarrotWeight >= 0 && numberOfCarrots > 0) {
                currentSum = currentSum - maxCarrotWeight;
                numberOfCarrots--;
                path.add(maxCarrotWeight);
            }
        }

        int newLevel = maxCarrotWeight - 1;
        if (currentSum == 0 || newLevel <= 0)
            return path;

        newLevel = Math.min(newLevel, currentSum);
        List<Integer> pathOfCurrentSum = maxPathByLevel(newLevel, currentSum);
        path.addAll(pathOfCurrentSum);

        return path;

    }

}
