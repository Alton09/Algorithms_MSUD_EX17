package fib;

import java.math.BigInteger;
import java.util.Scanner;

public class DynProgFib
{
  private static BigInteger[] fibs;
  private static BigInteger count = BigInteger.ZERO;

  public static void main(String[] args)
  {
    int n;
    System.out.print("Enter n for which you want Fib(n) computed: ");
    Scanner keys = new Scanner( System.in );
    n = keys.nextInt();

    fibs = new BigInteger[ n+1 ];
    fibs[ 0 ] = BigInteger.ONE;
    fibs[ 1 ] = BigInteger.ONE;

    System.out.println("Fib at that n is " + fib(n) );
    System.out.println("fib(n) method call count is " + count );
  }

  private static BigInteger fib( int n )
  {
	count = count.add( BigInteger.ONE );
    // if value already in the table, return it:
    if( fibs[ n ] != null )
      return fibs[n];

      fibs[ n ] = fib( n-1 ).add( fib( n-2 ) );

      return fibs[ n ];
  }

}
