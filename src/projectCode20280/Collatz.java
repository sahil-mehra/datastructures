package projectCode20280;

/*
 This is an recursive algorithm that does the following:
 1. It takes a positive integer n
 2. If n is 1, then terminate the function
 3. If n is even, divide by 2
 4. If n is odd, multiply by 3 and add 1
 */

public class Collatz {

  public static int collatz(int n) throws IllegalArgumentException {
    if(n < 1) {
      throw new IllegalArgumentException("n has to be a positive integer.");
    }
    if(n == 1) {
      return 1;
    } else if(n % 2 == 0) {
      return collatz(n / 2);
    } else {
      return collatz((n * 3) + 1);
    }
  }

  public static void main(String[] args) {
    int in = 9;
    System.out.println(in);
    System.out.println(collatz(in));
  }

}
