import java.util.ArrayList;

public class MergeTwoSortedLists {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    static ArrayList<Integer> merge_one_into_another(ArrayList<Integer> first, ArrayList<Integer> second) {

        int i = first.size() - 1, j = i, end = second.size() - 1;
        while (i >= 0 && j >= 0) {
            if (first.get(i) > second.get(j)) {
                second.set(end--, first.get(i--));
            } else {
                second.set(end--, second.get(j--));
            }
        }

        while (i >= 0) {
            second.set(end--, first.get(i--));
        }

        return second;
    }
}