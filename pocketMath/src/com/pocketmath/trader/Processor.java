package com.pocketmath.trader;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import com.pocketmath.trader.beans.TraderInfo;
import com.pocketmath.trader.beans.TransactionInfo;
import com.pocketmath.trader.util.RestClient;

public class Processor {

	public static void main(String[] args) throws IOException {

		// Rest API URLs and Creds
		String getTradersUrl = "https://fvjkpkflnc.execute-api.us-east-1.amazonaws.com/prod/traders";
		String getTransactionsUrl = "https://fvjkpkflnc.execute-api.us-east-1.amazonaws.com/prod/transactions";
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("x-api-key", "gaqcRZE4bd58gSAJH3XsLYBo1EvwIQo88IfYL1L5");

		ObjectMapper mapper = new ObjectMapper();

		String traderApiResponse = RestClient.callGet(getTradersUrl, headers);
		List<TraderInfo> traderInfoList = new ArrayList<TraderInfo>();
		traderInfoList = mapper.readValue(traderApiResponse, TypeFactory.collectionType(List.class, TraderInfo.class));

		Map<String, List<TraderInfo>> cityWiseTranderMap = getCityWiseTraderMap(traderInfoList);

		// Challenge 1
		sortAndDisplayTradersForGivenCity(cityWiseTranderMap.get("Singapore"));

		String transactionApiResponse = RestClient.callGet(getTransactionsUrl, headers);
		List<TransactionInfo> transactionInfoList = new ArrayList<TransactionInfo>();
		transactionInfoList = mapper.readValue(transactionApiResponse,
				TypeFactory.collectionType(List.class, TransactionInfo.class));

		// Challenge 2
		TransactionInfo highestTransactionInfo = getTransactionWithHighestValue(transactionInfoList);
		System.out.println();
		System.out.println("Highest Transaction : " + highestTransactionInfo);

		// Challenge 3
		sortAndDisplayTransactionsOfGivenYear(transactionInfoList, 2016);

		// Challenge 4
		double avgTransacVal = getAverageTransacVal(cityWiseTranderMap.get("Beijing"), transactionInfoList);
		System.out.println();
		System.out.println("The average transaxction value " + avgTransacVal);

	}

	private static Map<String, List<TraderInfo>> getCityWiseTraderMap(List<TraderInfo> traderInfoList) {
		Map<String, List<TraderInfo>> cityWiseTraderMap = new HashMap<>();

		for (TraderInfo traderInfo : traderInfoList) {
			if (cityWiseTraderMap.containsKey(traderInfo.getCity())) {
				List<TraderInfo> traderList = cityWiseTraderMap.get(traderInfo.getCity());
				traderList.add(traderInfo);
			} else {
				List<TraderInfo> traderList = new ArrayList<TraderInfo>();
				traderList.add(traderInfo);
				cityWiseTraderMap.put(traderInfo.getCity(), traderList);
			}
		}
		return cityWiseTraderMap;
	}

	private static double getAverageTransacVal(List<TraderInfo> traderInfoList,
			List<TransactionInfo> transactionInfoList) {

		Set<String> traderIds = new HashSet<String>(); // Using hashset, so that
														// while fetching the
														// value, it can do with
														// hash and does not
														// have to traverse the
														// whole list

		for (TraderInfo traderInfo : traderInfoList) {
			traderIds.add(traderInfo.getId());
		}

		double transacValueTotal = 0;
		int noOfTransac = 0;

		for (TransactionInfo transactionInfo : transactionInfoList) {
			if (traderIds.contains(transactionInfo.getTraderId())) {
				transacValueTotal += transactionInfo.getValue();
				noOfTransac++;
			}
		}

		// System.out.println("The totalTrasac value is " + transacValueTotal);
		// System.out.println("The number of traders are " + noOfTransac);
		return transacValueTotal / noOfTransac;
	}

	private static void sortAndDisplayTransactionsOfGivenYear(List<TransactionInfo> transactionInfoList,
			int inputYear) {

		List<TransactionInfo> transactionInfoListForGivenYear = new ArrayList<TransactionInfo>();
		for (TransactionInfo transactionInfo : transactionInfoList) {
			Date date = new Date(transactionInfo.getTimestamp() * 1000); // 1000
																			// to
																			// make
																			// it
																			// in
																			// milliseconds
			DateFormat df = new SimpleDateFormat("yyyy");
			int year = Integer.parseInt(df.format(date));
			if (year == inputYear) {
				transactionInfoListForGivenYear.add(transactionInfo);
			}
		}

		Collections.sort(transactionInfoListForGivenYear, new Comparator<TransactionInfo>() {

			@Override
			public int compare(TransactionInfo object1, TransactionInfo object2) {
				return Double.compare(object2.getValue(), object1.getValue());
			}

		});
		System.out.print("Sorted transactions in the year 2016 : ");
		for (TransactionInfo transactionInfo : transactionInfoListForGivenYear) {
			System.out.print(transactionInfo.getValue() + " ");
		}

	}

	private static TransactionInfo getTransactionWithHighestValue(List<TransactionInfo> transactionInfoList) {

		TransactionInfo maxTransaction = transactionInfoList.get(0);

		for (TransactionInfo transactionInfo : transactionInfoList) {
			if (maxTransaction.getValue() < transactionInfo.getValue()) {
				maxTransaction = transactionInfo;
			}
		}
		return maxTransaction;
	}

	private static void sortAndDisplayTradersForGivenCity(List<TraderInfo> traderInfoList) {

		Collections.sort(traderInfoList, new Comparator<TraderInfo>() {

			@Override
			public int compare(TraderInfo object1, TraderInfo object2) {
				return object1.getName().compareTo(object2.getName());
			}

		});

		System.out.print("Sorted trader names : ");
		for (TraderInfo eachTrader : traderInfoList) {
			System.out.print(eachTrader.getName() + " ");
		}
	}

}

