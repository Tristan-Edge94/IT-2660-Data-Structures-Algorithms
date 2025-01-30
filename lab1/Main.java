//import java.util.*;
/*
 * IT-2660 - Lab 1
 * Student Name: Tristan Edge
 */

 public class Main {
  public static void main(String[] args) {
      System.out.println("hello, world!");

      Lab1 lab = new Lab1();
      System.out.println("Increment: " + lab.increment(1));

      // Create the array
      int[] nums = {5, 9, 3, 12, 7, 3, 11, 5};
      
      // Output array in order using while loop
      int i = 0;
      System.out.print("Array in order: ");
      while (i < nums.length) {
          System.out.print(nums[i] + " ");
          i++;
      }
      System.out.println();

      // Output array in reverse using for loop
      System.out.print("Array in reverse: ");
      for (int j = nums.length - 1; j >= 0; j--) {
          System.out.print(nums[j] + " ");
      }
      System.out.println();

      // Output first and last values of the array
      System.out.println("First value: " + nums[0]);
      System.out.println("Last value: " + nums[nums.length - 1]);

      // Calling methods from Lab1
      System.out.println("Max of 10 and 20: " + lab.max(10, 20));
      System.out.println("Min of 10 and 20: " + lab.min(10, 20));
      System.out.println("Sum of array: " + lab.sum(nums));
      System.out.println("Average of array: " + lab.average(nums));
      System.out.println("Max in array: " + lab.max(nums));
      System.out.println("Min in array: " + lab.min(nums));
  }
}

class Lab1 {
  public int increment(int num) {
      return ++num;
  }

  public int max(int a, int b) {
      if (a > b) return a;
      return b;
  }

  public int min(int a, int b) {
      if (a < b) return a;
      return b;
  }

  public int sum(int[] nums) {
      int sum = 0;
      for (int num : nums) {
          sum += num;
      }
      return sum;
  }

  public double average(int[] nums) {
      int sum = 0;
      for (int num : nums) {
          sum += num;
      }
      return (double) sum / nums.length;
  }

  public int max(int[] nums) {
      int max = nums[0];
      for (int i = 1; i < nums.length; i++) {
          if (nums[i] > max) {
              max = nums[i];
          }
      }
      return max;
  }

  public int min(int[] nums) {
      int min = nums[0];
      for (int i = 1; i < nums.length; i++) {
          if (nums[i] < min) {
              min = nums[i];
          }
      }
      return min;
  }
}