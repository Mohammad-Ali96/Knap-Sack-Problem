package knapsack;
import java.util.*;
public class Item implements Comparator<Item>{
    private int weight;
    private int value;

    Item(){
        
    }
    
    Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compare(Item item1, Item item2) {     
        double first = (double)(item1.value)/item1.weight;
        double second = (double)(item2.value)/item2.weight;
        
        if(first<second){
            return 1;
        }

        if(first>second){
            return -1;
        }
        return 0;
        
    }
    
    
    
}
