package knapsack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Zer_One_KnapSack {

    ArrayList<Item> items;
    int n; // The number of items
    int capacityOfBag;
    int[] solution;
    int[][] S;

    public Zer_One_KnapSack(int n, int capacityOfBag) {
        this.n = n;
        this.capacityOfBag = capacityOfBag;
        items = new ArrayList<>();
        solution = new int[n];
        S = new int[this.n + 1][this.capacityOfBag + 1];
        for (int i = 0; i < this.n; i++) {
            solution[i] = 0;
        }

    }

    Zer_One_KnapSack(int n, int capacityOfBag, ArrayList<Item> items) {
        this.n = n;
        this.capacityOfBag = capacityOfBag;
        items = new ArrayList<>();
        solution = new int[n];
        this.items = items;
        for (int i = 0; i < this.n; i++) {
            solution[i] = 0;
        }
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public int[] getSolution() {
        return solution;
    }

    public String printSolution() {
        this.findSolution();
        System.out.println("The Solution is:");
        String message = "";
        int cost = 0;
        for (int i = 0; i < n; i++) {
//            System.out.print(itemsSorted.get(i).getWeight() + "    " + itemsSorted.get(i).getValue() + "   " + solution[i] + ", ");
            cost += solution[i] * items.get(i).getValue();
            message += "(" + items.get(i).getWeight() + ", " + items.get(i).getValue() + ") --> " + solution[i] + "\n";

        }

        message = " The Value of Solution is " + cost + "\n" + message;
        return message;

    }

    public void inputItems() throws IOException {
        System.out.println("Please input each item like w v");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < this.n; i++) {
            String s = br.readLine();
            String[] ss = s.split(" ");
            Item item = new Item(Integer.parseInt(ss[0]), Integer.parseInt(ss[1]));
            items.add(item);
        }

    }

    public void findOptimalSolution() {
        for (int u = 0; u <= this.capacityOfBag; u++) {
            S[0][u] = 0;
        }
        for (int k = 1; k <= this.n; k++) {
            for (int u = 1; u <= this.capacityOfBag; u++) {
                int t = -1;
                if (this.items.get(k - 1).getWeight() > u) {
                    t = 0;
                } else {
                    t = S[k - 1][u - this.items.get(k - 1).getWeight()] + this.items.get(k - 1).getValue();
                }

                if (t > S[k - 1][u]) {
                    S[k][u] = t;
                } else {
                    S[k][u] = S[k - 1][u];
                }

            }
        }

        System.out.println("--- " + S[n][capacityOfBag]);

    }

    public void findSolution() {

        int r = this.capacityOfBag;
        for (int k = this.n; k >= 1; k--) {
            if (r >= this.items.get(k - 1).getWeight()) {

                if (S[k - 1][r] < S[k - 1][r - this.items.get(k - 1).getWeight()] + this.items.get(k - 1).getValue()) {
                    solution[k - 1] = 1;
                    r -= this.items.get(k - 1).getWeight();
                } else {
                    this.solution[k - 1] = 0;
                }

            } else {
                this.solution[k - 1] = 0;
            }
        }
    }

}
