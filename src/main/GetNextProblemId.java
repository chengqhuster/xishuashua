import java.util.Arrays;
import java.util.Random;

public class GetNextProblemId {
    static int LEETCODE_NUM = 974;
    static int[] LOCKED_PROBLEM_IDS = {};
    static int[] SOLVED_PROBLEM_IDS = {};

    public static void main(String[] args) {
        Random random = new Random();
        int id = random.nextInt(LEETCODE_NUM) + 1;
        while(Arrays.binarySearch(LOCKED_PROBLEM_IDS, id) == 0
                && Arrays.binarySearch(SOLVED_PROBLEM_IDS, id) == 0) {
            id = random.nextInt(LEETCODE_NUM) + 1;
        }
        System.out.println(id);
    }
}
