package projectCode20280;

public class Palindrome {

  public static boolean isPalindrome(String input) {
    int i = 0, j = (input.length() - 1);
    while(i <= j) {
      if(input.toUpperCase().charAt(i) != input.toUpperCase().charAt(j)) {
        return false;
      }
      i++;
      j--;
    }
    return true;
  }

  public static void main(String[] args) {
    String[] inputs = {"Racecar",
            "Radar",
            "Step on no pets",
            "Top spot",
            "Was it a cat I saw?",
            "eva, can I see bees in a cave?",
            "no lemon, no melon"};

    for(String input : inputs) {
      System.out.println(Palindrome.isPalindrome(input) + " -> " + input);
    }
  }

}
