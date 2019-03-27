package leetcode.utils;

import java.math.BigDecimal;

/**
 * 用于正数的十进制装换成其他进制
 */
public class BaseConversion {

  public static String coversionScale(int number, int scale) {
    if (number < scale) {
      return String.valueOf(number);
    }
    StringBuilder sb = new StringBuilder();
    BigDecimal[] a = new BigDecimal[1];
    a[0] = BigDecimal.valueOf(number);

    BigDecimal divisor = BigDecimal.valueOf(scale);
    do {
      a = a[0].divideAndRemainder(divisor);
      sb.insert(0, a[1]);
    } while (a[0].compareTo(divisor) >= 0);
    if (!a[0].equals(BigDecimal.ZERO)) {
      sb.insert(0, a[0]);
    }

    return sb.toString();
  }

}
