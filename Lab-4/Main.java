import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        System.out.println("Unsorted Array ---------------------------------------------------");
        ArrayList<Integer> integerList = Lab4.getList();
        Lab4.outputList(integerList);

        // Measure Bubble Sort time
        System.out.println("\n\nBubble sort results ----------------------------------------------");
        ArrayList<Integer> bubbleListCopy = new ArrayList<>(integerList); // Copy list to avoid modifying original
        long startTime = System.nanoTime();
        ArrayList<Integer> bubbleSortedList = Lab4.bubbleSort(bubbleListCopy);
        long endTime = System.nanoTime();
        Lab4.outputList(bubbleSortedList);
        System.out.println("\nBubble Sort Execution Time: " + (endTime - startTime) / 1_000_000.0 + " ms");

        // Measure Insertion Sort time
        System.out.println("\n\nInsertion sort results -------------------------------------------");
        ArrayList<Integer> insertionListCopy = new ArrayList<>(integerList); // Copy list again for insertion sort
        startTime = System.nanoTime();
        ArrayList<Integer> insertionSortedList = Lab4.insertionSort(insertionListCopy);
        endTime = System.nanoTime();
        Lab4.outputList(insertionSortedList);
        System.out.println("\nInsertion Sort Execution Time: " + (endTime - startTime) / 1_000_000.0 + " ms");
    }
}


class Lab4 {
    public static ArrayList<Integer> insertionSort(ArrayList<Integer> integerList) {
        // Step 1 - Implement insertion sort algorithm here
        for (int i = 1; i < integerList.size(); i++) {
            int key = integerList.get(i);
            int j = i - 1;

            // Move elements that are greater than key to one position ahead
            while (j >= 0 && integerList.get(j) > key) {
                integerList.set(j + 1, integerList.get(j));
                j--;
            }
            integerList.set(j + 1, key);
        }
        return integerList;
    }

    public static ArrayList<Integer> bubbleSort(ArrayList<Integer> integerList) {
        // Step 2 - Implement the bubble sort algorithm here
        int n = integerList.size(); // Define `n` correctly
        boolean swapped; // Declare `swapped`

        for (int i = 0; i < n - 1; i++) {
            swapped = false; // Initialize swapped at the start of each iteration

            for (int j = 0; j < n - i - 1; j++) {
                if (integerList.get(j) > integerList.get(j + 1)) {
                    // Swap using Collections.swap()
                    Collections.swap(integerList, j, j + 1);
                    swapped = true;
                }
            }
            // If no two elements were swapped in the inner loop, the list is already sorted
            if (!swapped) break;
        }
        return integerList;
    }

    public static ArrayList<Integer> getList() {
        ArrayList<Integer> integerList = new ArrayList<>();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader("integers.txt"))) {
            while ((line = br.readLine()) != null) {
                integerList.add(Integer.parseInt(line.trim())); // Trim to avoid errors
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return integerList;
    }

    public static void outputList(ArrayList<Integer> integerList) {
        for (int num : integerList) {
            System.out.print(num + " ");
        }
        System.out.println(); // Ensure new line after printing the list
    }
}
