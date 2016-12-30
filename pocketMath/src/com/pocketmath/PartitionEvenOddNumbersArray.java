package com.pocketmath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PartitionEvenOddNumbersArray {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("How many numbers will you input? : ");
		Integer inputLength = Integer.parseInt(br.readLine());
		
		if(inputLength <= 0) {
			System.out.println("Wrong input size. Minimum input size should be 1");
			return;
		}
		
		System.out.println("Enter all numbers please :");
		int[] arr = new int[inputLength];
		
		for (int index = 0; index < inputLength; index++) {
			arr[index] = Integer.parseInt(br.readLine());
		}

		partitionEvenOdd(arr);

		System.out.println("Modified array is : ");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}

	}

	/*
	 * arr : input integer array
	 * Algo : 
	 * 1) Initialize start and end index with 0 and last element's index
	 * 2) increment start till it gets an odd number, decrement end till it gets an even number
	 * 3) if start < end, swap the elements of arr[start] and arr[end]
	 * 4) if start > end, return
	 * 5) else repeat step 2
	 */
	public static void partitionEvenOdd(int arr[]) {
		int start = 0;
		int end = arr.length - 1;

		while (start < end) {
			while (arr[start] % 2 == 0) {
				start++;
			}

			while (arr[end] % 2 == 1 && start < end) {
				end--;
			}

			//Swap the elements of arr[start] and arr[end]
			if (start < end) {
				int temp = arr[start];
				arr[start] = arr[end];
				arr[end] = temp;
				start++;
				end--;
			}
		}

	}

}
