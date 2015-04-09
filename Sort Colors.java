/*
	Sort Colors
	Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

	Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

	Note:
	You are not suppose to use the library's sort function for this problem.
*/

public class Solution {

	// public void sortColors(int[] A) {
    //     if (A == null || A.length <= 1) {
    //         return ;
    //     }
    //     sortColors(A, 0, A.length - 1);
    // }
    // public void sortColors(int[] A, int low, int high) {
    //     if (low < high) {
    //         int q = partition(A, low, high);
    //         sortColors(A, low, q - 1);
    //         sortColors(A, q + 1, high);
    //     }
    // }
    
    // public void swap(int[] A, int i ,int j) {
    //     int temp = A[i];
    //     A[i] = A[j];
    //     A[j] = temp;
    // }
    
    // public int partition(int[] A, int p, int r) {
    //     int x = A[r];
    //     int i = p - 1;
    //     for (int j = p; j <= r - 1; j++) {
    //         if (A[j] <= x) {
    //             i = i + 1;
    //             swap(A, i, j);
    //         }
    //     }
    //     swap(A, i + 1, r);
    //     return i + 1;
    // }
    
    //Quick Sort
    public void sortColors(int[] A) {
    	if (A.length <= 1 || A == null) {
    		return;
    	}

    	sortColors(A, 0, A.length - 1);
    }

    public void sortColors(int[] A, int low, int high) {
    	if (low < high) {
    		int pivot = partition(A, low, high);
    		sortColors(A, low, pivot - 1);
    		sortColors(A, pivot + 1, high);
    	}
    }

    public int partition(int[] A, int low, int high) {
    	int pivot = A[high];
    	int i = low - 1;
    	for (int j = low; j < high; j++) {
    		if (A[j] <= pivot) {
    			i++;
    			swap(A, i, j);
    		}
    	}
    	swap(A, i + 1, high);
    	return i + 1;
    }

    public void swap(int[] A, int i, int j) {
    	int temp = A[i];
    	A[i] = A[j];
    	A[j] = temp;
    }
    
    //Merge Sort
    // public void sortColors(int[] A) {
    //     if (A == null || A.length <= 1) {
    //         return;
    //     }
    //     sortColors(A, 0, A.length - 1);
    // }
    // public static int[] sortColors(int[] A, int low, int high) {
    //     int mid = (low + high) / 2;
    //     if (low < high) {
    //         sortColors(A, low, mid);
    //         sortColors(A, mid + 1, high);
    //         merge(A, low, mid, high);
    //     }
    //     return A;
    // }
    
    // public static void merge(int[] A, int low, int mid, int high) {
    //     int[] temp = new int[high - low + 1];
    //     int i = low;
    //     int j = mid + 1;
    //     int k = 0;
        
    //     while (i <= mid && j <= high) {
    //         if (A[i] < A[j]) {
    //             temp[k++] = A[i++];
    //         } else {
    //             temp[k++] = A[j++];
    //         }
    //     }
        
    //     while (i <= mid) {
    //         temp[k++] = A[i++];
    //     }
    //     while (j <= high) {
    //         temp[k++] = A[j++];
    //     }
        
    //     for (int l = 0; l < temp.length; l++) {
    //         A[l + low] = temp[l];
    //     }
    // }

    //double pointer
    public void sortColors(int[] A) {
    	if (A == null || A.lengtn <= 1) {
    		return ;
    	}
    	int pl = 0;
    	int pr = A.length - 1;
    	int i = 0;
    	while (i <= pr) {
    		if (A[i] == 0) {
    			swap(A, i, pl);
    			pl++;
    			i++;
    		} else if (A[i] == 1) {
    			i++;
    		} else {
    			swap(A, i, pr);
    			pr--;
    		}
    	}
    }
}