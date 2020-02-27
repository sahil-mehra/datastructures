package projectCode20280;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TripleSum {

    public static int countTripleSums(int[] arr){
        if(arr.length < 3){
            return 0;
        }

        int counter = 0;

        for(int i = 0; i + 3 <= arr.length; ++i){
            if(arr[0] + arr[1] + arr[2] == 0){
                counter++;
            }
        }
        return counter;
    }

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Integer> list = new ArrayList<>();

        Scanner scan = new Scanner(System.in);

        // Todo a while loop to check for new line to close scan


        while (scan.hasNext()){
            list.add(scan.nextInt());
        }

        int[] arr = list.stream().mapToInt(i->i).toArray();
        System.out.println(Arrays.toString(arr));
        System.out.println(countTripleSums(arr));
    }

}
