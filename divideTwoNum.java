111/*
	Divide Two Integers
	Divide two integers without using multiplication, division and mod operator.
	If it is overflow, return MAX_INT.
	Tags: Math, Binary Search
*/

public class Solution {
	public int divide (int dividend, int divisor) {
		long a = Math.abs((long)dividend);
		long b = Math.abs((long)divisor);

		long res = 0;

		/*
			a,b : a / b 

			a - b * (base)^n
				
		*/
		/*			tmp	
                 {   |   } 
			21 - 3 * 2^0 = 18  <-- (a - tmp) //res = 2^0
			|    |    |
			a    b   base
		example1:
			18 - 3 * 2^1 = 12   // res =  2^0 + 2^1
			12 - 3 * 2^2 = 0;   // res =  2^0 + 2^1 + 2^2
			res = 2^0 + 2^1 + 2^2 = 7;
		example2:
			35 - 3 * 2^0 = 32;  // res = 2^0
			32 - 3 * 2^1 = 26;  // res = 2^0 + 2^1
			26 - 3 * 2^2 = 14;  // res = 2^0 + 2^1 + 2^2 =7
			14 - 3 * 2^3 < 0;  < 0 ;break for loop，进行下一个while loop，此时a = 14, b = 3

			14 - 3 * 2^0 = 11; //  res = 2^0 + 2^1 + 2^2 + 2^0
			11 - 3 * 2^1 = 5;  //  res = 2^0 + 2^1 + 2^2 + 2^0 +2^1 = 11 //break


			
		*/
		while (a >= b) {
			for (long tmp = b, base = 1; a >= tmp; tmp <<= 1, base <<= 1) {
				res = res + base;
				a = a - tmp;
			}
		}
		//dividend和divisor按位异或之后 右移31位则只剩下符号位，再将最高位的符号位与1进行与操作确定符号（1正,0负）。
		res = (((dividend ^ divisor) >> 31) & 1) == 1 ? -res: res;
		if ( res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) 
			return Integer.MAX_VALUE;
		return (int) res;
	}
}