public class Main {
  public static void main(String[] args) {
    String text = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    
    String bestCase = "A";

    long startTime = System.nanoTime(); // Start time for best case
    int index = match(text, bestCase);
    long endTime = System.nanoTime(); // End time for best case
    long elapsedTime = endTime - startTime;
    
    if (index >= 0)
      System.out.println("best-case input matched at index " + index);
    else
      System.out.println("best-case input unmatched");   
    
    String worstCase = "999";
    
    startTime = System.nanoTime(); // Start time for worst case
    index = match(text, worstCase);
    endTime = System.nanoTime(); // End time for worst case
    elapsedTime = endTime - startTime;
    
    if (index >= 0)
      System.out.println("worst-case input matched at index " + index);
    else
    System.out.println("Worst-case input unmatched");  
    System.out.println("Worst-case elapsed time: " + elapsedTime + " nanoseconds"); 
  }

  // Return the index of the first match. -1 otherwise.
  public static int match(String text, String pattern) {
    for (int i = 0; i < text.length() - pattern.length() + 1; i++) {
      if (isMatched(i, text, pattern))
        return i;
    }

    return -1;
  }
	
  // Test if pattern matches text starting at index i
  private static boolean isMatched(int i, String text, 
      String pattern) {
    for (int k = 0; k < pattern.length(); k++) {
      if (pattern.charAt(k) != text.charAt(i + k)) {
        return false;
      }
    }
    
    return true;
  }
}