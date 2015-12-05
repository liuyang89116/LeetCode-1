/*
	Greedy
*/


1. Problem
	1.1 Jump Game I
			/*
		        Given an array of non-negative integers, you are initially positioned at the first index of the array. 
		        Each element in the array represents your maximum jump length at that position.  
		        Determine if you are able to reach the last index.
		
		        For example:
		            A = [2,3,1,1,4], return true.

		            A = [3,2,1,0,4], return false.
		    */
		    //Greedy Strategy: once find nums[i] + i >= maxCover and i <= maxCover, update the maxCover = nums[i] + i
	        public class Solution {
			    public boolean canJump(int[] nums) {
			        if (nums == null || nums.length == 0) {
			            return false;
			        }
			        int maxCover = nums[0];
			        for (int i = 1; i < nums.length; i++) {
			            if (i <= maxCover && nums[i] + i >= maxCover) {
			                maxCover = nums[i] + i;
			            }
			        }
			        return maxCover >= nums.length - 1;
			    }
			} 
	1.2 Jump Game II
			public class Solution {
			    public int jump(int[] nums) {
			        if (nums == null || nums.length == 0) {
			            return 0;
			        }
			        int maxCover = 0;
			        int steps = 0;
			        int curCover = 0;
			        for (int i = 0; i < nums.length; i++) {
			            if(i > curCover) {
			                steps++;
			                curCover = maxCover;
			            }
			            maxCover = Math.max(maxCover, nums[i] + i);
			        }
			        return steps;
			    }
			}
	1.3 Candy
			/*
		         For example:
		               Given array A = [2,3,1,1,4]
		         The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
		    */
		    //O(n) time, O(n) space
		    public class Solution {
			    public int candy(int[] ratings) {
			        if (ratings == null || ratings.length == 0) {
			            return 0;
			        }
			        int len = ratings.length;
			        int[] left = new int[len];
			        int[] right = new int[len];
			        left[0] = 1;
			        for (int i = 1; i < len; i++) {
			            if (ratings[i] > ratings[i - 1]) {
			                left[i] = left[i - 1] + 1;
			            } else {
			                left[i] = 1;
			            }
			        }
			        right[len - 1] = 1;
			        for (int i = len - 2; i >= 0; i--) {
			            if (ratings[i] > ratings[i + 1]) {
			                right[i] = right[i + 1] + 1;
			            } else {
			                right[i] = 1;
			            }
			        }
			        int sum = 0;
			        for (int i = 0; i < len; i++) {
			            sum += Math.max(right[i], left[i]); 
			        }
			        return sum;
			    }
			}
			/*
				This solution picks each element from the input array only once. First, we give a candy to the first child. Then for each child we have three cases:
				His/her rating is equal to the previous one -> give 1 candy.
				His/her rating is greater than the previous one -> give him (previous + 1) candies.
				His/her rating is less than the previous one -> don't know what to do yet, let's just count the number of such consequent cases.
				
					When we enter 1 or 2 condition we can check our count from 3. If it's not zero then we know that we were descending 
				before and we have everything to update our total candies amount: number of children in descending sequence of raitings - 
				coundDown, number of candies given at peak - prev (we don't update prev when descending). 
				Total number of candies for "descending" children can be found through arithmetic progression formula (1+2+...+countDown). 
				Plus we need to update our peak child if his number of candies is less then or equal to countDown.

				Here's a pretty concise code below.
				O(n) time, O(1) space
			*/
			public class Solution {
			    public int candy(int[] ratings) {
			        if (ratings == null || ratings.length == 0) return 0;
			        int total = 1, pre = 1, countDown = 0;
			        for (int i = 1; i < ratings.length; i++) {
			            if (ratings[i] >= ratings[i-1]) {
			                if (countDown > 0) {
			                    total += countDown*(countDown+1)/2; // arithmetic progression
			                    if (countDown >= pre) {
			                    	total += countDown - pre + 1;
			                    }
			                    countDown = 0;
			                    pre = 1;
			                }
			                pre = ratings[i] == ratings[i-1] ? 1 : pre+1;
			                total += pre;
			            } else countDown++;
			        }
			        if (countDown > 0) { // if we were descending at the end
			            total += countDown*(countDown+1)/2;
			            if (countDown >= pre) total += countDown - pre + 1;
			        }
			        return total;
			    }
			}
	1.4 Gas Station
		/*
			There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
			You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). 
			You begin the journey with an empty tank at one of the gas stations.
			Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
			Note:
				The solution is guaranteed to be unique.
		*/
		/*
			Greedy Strategy: 
				gas[i] 表示 加油站i的储油量
				cost[i] 表示从加油站i到加油站i+1所需的油量，因此gas[i] - cost[i] > 0时 意味着以这个为起点，i 一定能到i + 1
				贪心策略： 1. tank表示油箱的变化，如果从点i 到 点j 的油量diff大于0 则一直往前走，tank +=gas[i] - cost[i],
						 2. 如果小于0，则将点j设为起点,tank清空，从该点开始重新计算继续走，
						 3. total 会一直记录所有的gas - cost的差值，不会清空。
						 4. 最后判断total是否小于0，小于0表示没有这样的一个点，大于等于0则返回点j
		*/
		/*
			Proof:
			1）If there is only one gas station, it’s true.
			2）If there are two gas stations a and b, and gas(a) cannot afford cost(a), i.e., gas(a) < cost(a), then gas(b) must be greater than cost(b), 
			   i.e., gas(b) > cost(b), since gas(a) + gas(b) > cost(a) + cost(b); so there must be a way too.
			3.1）If there are three gas stations a, b, and c, where gas(a) < cost(a), i.e., we cannot travel from a to b directly, then:
			   either if gas(b) < cost(b), i.e., we cannot travel from b to c directly, then cost(c) > cost(c), 
			   so we can start at c and travel to a; since gas(b) < cost(b), gas(c) + gas(a) must be greater than cost(c) + cost(a), 
			   so we can continue traveling from a to b. Key Point: this can be considered as there is one station at c’ with gas(c’) = gas(c) + gas(a) 
			   and the cost from c’ to b is cost(c’) = cost(c) + cost(a), and the problem reduces to a problem with two stations. 
			   This in turn becomes the problem with two stations above.
			3.2）or if gas(b) >= cost(b), we can travel from b to c directly. Similar to the case above, 
			    this problem can reduce to a problem with two stations b’ and a, where gas(b’) = gas(b) + gas(c) and cost(b’) = cost(b) + cost(c). 
			    Since gas(a) < cost(a), gas(b’) must be greater than cost(b’), so it’s solved too.

			4)For problems with more stations, we can reduce them in a similar way. In fact, as seen above for the example of three stations, 
			the problem of two stations can also reduce to the initial problem with one station.
		*/
		public class Solution {
		    public int canCompleteCircuit(int[] gas, int[] cost) {
		        int tank = 0;
		        int total = 0;
		        int start = 0;
		        for (int i = 0; i < gas.length; i++) {
		            total += gas[i] - cost[i];
		            tank += gas[i] - cost[i];
		            if (tank < 0) {
		                start = i + 1;
		                tank = 0;
		            }
		        }
		        if (total < 0) {
		            return -1;
		        }
		        return start;
		    }
		}













