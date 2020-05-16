package List;

import javax.print.DocFlavor;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class List {

    private int[] numbers;
    private int capacity;
    private int size;

    public List (int capacity) {
        this.capacity = capacity;
        this.numbers = new int[capacity];
        this.size = 0;
    }

    public void addNumber (int number) {
        if ((size == capacity)) {
            System.out.println("List is full. You can't add new element.");
        } else {
            numbers[size] = number;
            size++;
        }
    }

    public int find (int number){
        int position = -1;
        for (int i = size - 1; i >= 0; i--){
            if (numbers[i] == number){
                position = i;
            }
        }
        return position;
    }

    public void write() {
        System.out.println("Lista: ");
        System.out.println("     Size: " + this.size);
        System.out.println("     Capacity: " + this.capacity);
        System.out.print("     Numbers: ");
        for (int i = 0; i < size; i++) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println();
    }

    public void cancelFirst (int number){
        int position = find(number);
        if (position != -1){
            for (int i = position; i < size - 1; i++){
                numbers[i] = numbers[i + 1];
            }
        }
        numbers[size - 1] = 0;
        size--;
    }

    public void cancelRepeat (){
        Map <Integer,Integer> map = new HashMap<>();
        for (int number : numbers){
            if (map.containsKey(number)){
                int value = map.get(number);
                value++;
                map.put(number,value);
            } else {
                map.put(number,1);
            }
        }
        for (Map.Entry<Integer,Integer> entry : map.entrySet()){
            int numberOfRepetitions = entry.getValue();
            if (numberOfRepetitions > 1){
                for (int i = 0; i < numberOfRepetitions - 1; i++){
                    cancelFirst(entry.getKey());
                }
            }
        }
    }

    public void reverse () {
        int[] reversedList = new int[numbers.length];
        int i = numbers.length - 1;
        for (int number : numbers){
            reversedList[i] = number;
            i--;
        }
        int j = 0;
        for (int number : reversedList){
            numbers[j] = number;
            j++;
        }
    }

    public void saveNumbersToFile (String filePath){
        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
            for (int number : numbers){
                bufferedWriter.write(number);
                bufferedWriter.write(", ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}