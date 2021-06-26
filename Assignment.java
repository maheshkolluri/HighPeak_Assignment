import java.io.*;
import java.util.*;

class Items{
    String name;
    int price;

    public Items(String name, int price){
        this.name = name;
        this.price = price;
    }

    public String toString(){
        return this.name + ": "+ this.price;
    }
}


// class Assignment is created
public class Assignment {
    public static void main(String arga[]) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt"); // input file is taken as input.txt
        Scanner scan = new Scanner(fis);                        // scanner is used for file reading
        int no_of_emp = Integer.parseInt(scan.nextLine().split(": ")[1]); 
                        scan.nextLine(); scan.nextLine(); scan.nextLine();

        ArrayList<Items> goodies = new ArrayList<Items>();

        while(scan.hasNextLine()){
            String current[] = scan.nextLine().split(": ");
            goodies.add(new Items(current[0], Integer.parseInt(current[1])));  
        }
        scan.close();

        // sort the prices for getting max and min prices

        Collections.sort(goodies, new Comparator<Items>() {             
            public int compare(Items a, Items b) { 
              return a.price - b.price; 
            } 
          });
        

        int min_dif = goodies.get(goodies.size()-1).price;
        int min_index = 0;
        for (int i = 0; i < goodies.size()-no_of_emp+1; i++){
            int dif = goodies.get(no_of_emp+i-1).price-goodies.get(i).price;

            if (dif<=min_dif){
                min_dif = dif;
                min_index = i;
            }
        }


        // writes the output to the file named as output.txt
        FileWriter fw = new FileWriter("output.txt");
        fw.write("Here the goodies that are selected for distribution are:\n");
        for (int i = min_index; i<min_index + no_of_emp; i++){
            fw.write(goodies.get(i).toString() + "\n");
        }
        // gives the price difference
        fw.write("\nAnd the difference between the chosen goodie with highest price and the lowest price is " + min_dif);
        fw.close();
                    
    }
    
}
