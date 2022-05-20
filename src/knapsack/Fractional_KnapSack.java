package knapsack;

import java.util.*;
import java.io.*;

public class Fractional_KnapSack {

    ArrayList<Item> items;
    ArrayList<Item> itemsSorted;
    int n; // The number of items
    int capacityOfBag;
    double[] solution;
    PriorityQueue<Item> PQ;

    Fractional_KnapSack(int n, int capacityOfBag) {
        this.n = n;
        this.capacityOfBag = capacityOfBag;
        items = new ArrayList<>();
        itemsSorted = new ArrayList<>();
        solution = new double[n];
        PQ = new PriorityQueue<>(this.n, new Item());
        for (int i = 0; i < this.n; i++) {
            solution[i] = 0;
        }
    }

    Fractional_KnapSack(int n, int capacityOfBag, ArrayList<Item> items) {
        this.n = n;
        this.capacityOfBag = capacityOfBag;
        items = new ArrayList<>();
        solution = new double[n];
        this.items.addAll(items);
        PQ = new PriorityQueue<>(this.n, new Item());
        for (int i = 0; i < this.n; i++) {
            solution[i] = 0;
        }
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
        int q = this.capacityOfBag;
        for (int i = 0; i < this.items.size(); i++) {
            PQ.add(new Item(this.items.get(i).getWeight(), this.items.get(i).getValue()));
        }
        System.out.println(items.size());

//        while(!PQ.isEmpty()){
//            Item item = PQ.poll();
//            System.out.println(item.getWeight() + "  " + item.getValue());
//        }
        int index = 0;
        while (!PQ.isEmpty()) {

            Item item = PQ.poll();
            itemsSorted.add(new Item(item.getWeight(), item.getValue()));
            System.out.println(item.getValue());
            if (q >= item.getWeight()) {
                solution[index] = 1;
                q -= item.getWeight();
            } else {
                solution[index] = (double) (q) / item.getWeight();
                q = 0;
            }
            index++;
        }

    }

    public String printSolution() {
        System.out.println("The Solution is:");
        String message = "";
        double cost = 0;
        for (int i = 0; i < n; i++) {
//            System.out.print(itemsSorted.get(i).getWeight() + "    " + itemsSorted.get(i).getValue() + "   " + solution[i] + ", ");
        cost += solution[i] * itemsSorted.get(i).getValue();
        message += "(" + itemsSorted.get(i).getWeight() + ", " + itemsSorted.get(i).getValue() + ") --> " + solution[i] +"\n";

        }
        
        message = " The Value of Solution is " + cost + "\n" + message;
        return message;
        
    }

    
    
}
