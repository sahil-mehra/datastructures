/*
 * Write an algorithm that checks if the parentheses in a string are balanced
 * Each left delimiter must have a matching right delimiter
 * Your Java implementation should include the following:
 * 1. Read the characters from a string
 * 2. When you see a left delimiter, push it to the stack
 * 3. Whenever you see a right delimiter pop the (hopefully matching) opening delimiter from the stack
 * 4. If they don't match, report a matching error
 * 5. If you can't the pop the stack because it is empty, report a missing left delimiter error
 * 6. If the stack is non-empty after all the characters of the expressions have been processed, report a missing right delimiter error.
 *
 * Check your code with the following inputs:
 * 1. "{[()]}"
 * 2. "{[(])}"
 * 3. "{{[[(())]]}}"
 * 4. "][]][][[]][]][][[["
 * 5. "(((abc))((d)))))"
 */


package project20280;

public class BracketChecker {


  public void main(String[] args) {
    String[] inputs = {
            "[]]()()", // not correct
            "c[d]", // correct
            "a{b[c]d}e", // correct
            "a{b(c]d}e", // not correct, "]" doesn't match
            "a[b{c}d]e}", // not correct, nothing matches final "}"
            "a{b(c) ", // not correct, nothing matches opening first "{"
            "][]][][[]][]][][[[", //
            "(((abc))((d)))))", //
    };
  }


}
