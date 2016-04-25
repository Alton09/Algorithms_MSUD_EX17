package fib;

import java.math.BigInteger;
import java.util.Scanner;

public class BadFib
{
  public static BigInteger count = BigInteger.ZERO;

  public static void main(String[] args)
  {
    BigInteger n;
    System.out.print("Enter n for which you want Fib(n) computed: ");
    Scanner keys = new Scanner( System.in );
    n = new BigInteger( keys.nextLine() );

    System.out.println("Fib at that n is " + fib(n) );
    System.out.println("Took " + count + " calls to fib");

  }

  private static BigInteger fib( BigInteger n )
  {
    count = count.add( BigInteger.ONE );

    if( n.equals( BigInteger.ZERO ) )
      return BigInteger.ONE;
    else if( n.equals( BigInteger.ONE ) )
      return BigInteger.ONE;
    else
    {
      BigInteger n1 = n.subtract( BigInteger.ONE );
      BigInteger n2 = n1.subtract( BigInteger.ONE );

      return fib( n1 ).add( fib( n2 ) );
    }

  }

}
