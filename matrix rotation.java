import java.io.*;
import java.util.*;

public class Solution {

    public static void matrixRotation(List<List<Integer>> a, int r) {

        int m = a.size();
        int n = a.get(0).size();

        for (int layer = 0; layer < Math.min(m, n) / 2; layer++) {

            int top = layer;
            int left = layer;
            int bottom = m - 1 - layer;
            int right = n - 1 - layer;

            List<Integer> list = new ArrayList<>();

            // Top
            for (int j = left; j <= right; j++)
                list.add(a.get(top).get(j));

            // Right
            for (int i = top + 1; i <= bottom; i++)
                list.add(a.get(i).get(right));

            // Bottom
            for (int j = right - 1; j >= left; j--)
                list.add(a.get(bottom).get(j));

            // Left
            for (int i = bottom - 1; i > top; i--)
                list.add(a.get(i).get(left));

            int len = list.size();
            int k = r % len;

            int index = k;

            // Top
            for (int j = left; j <= right; j++) {
                a.get(top).set(j, list.get(index));
                index = (index + 1) % len;
            }

            // Right
            for (int i = top + 1; i <= bottom; i++) {
                a.get(i).set(right, list.get(index));
                index = (index + 1) % len;
            }

            // Bottom
            for (int j = right - 1; j >= left; j--) {
                a.get(bottom).set(j, list.get(index));
                index = (index + 1) % len;
            }

            // Left
            for (int i = bottom - 1; i > top; i--) {
                a.get(i).set(left, list.get(index));
                index = (index + 1) % len;
            }
        }

        // Print matrix
        for (List<Integer> row : a) {
            for (int j = 0; j < row.size(); j++) {
                System.out.print(row.get(j));
                if (j < row.size() - 1)
                    System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();
        int r = sc.nextInt();

        List<List<Integer>> matrix = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                row.add(sc.nextInt());
            }

            matrix.add(row);
        }

        matrixRotation(matrix, r);

        sc.close();
    }
}