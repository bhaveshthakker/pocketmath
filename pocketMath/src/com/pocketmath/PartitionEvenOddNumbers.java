package com.pocketmath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PartitionEvenOddNumbers {

	public static void main(String[] args) throws IOException {

		List<Integer> inputList = null;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("How many numbers will you input? : ");
		Integer inputLength = Integer.parseInt(br.readLine());
		
		System.out.print("Enter all numbers please :");
		inputList = new ArrayList<Integer>(inputLength);

		for (int index = 0; index < inputLength; index++) {
			inputList.add(Integer.parseInt(br.readLine()));
		}

		partitionEvenOdd(inputList);

		System.out.print("Modified list is : " + inputList);
	}

	public static void partitionEvenOdd(List<Integer> inputList) {
		int start = 0, end = inputList.size() - 1;
		while (start < end) {
			while (inputList.get(start) % 2 == 0 && start < end)
				start++;

			while (inputList.get(end) % 2 == 1 && start < end)
				end--;

			if (start < end) {
				int temp = inputList.get(start);
				inputList.set(start, inputList.get(end));
				inputList.set(end, temp);
				start++;
				end--;
			}
		}
	}

}
