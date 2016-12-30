package com.pocketmath.trader.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class RestClient {

	public static String callGet(String requestUrl, Map<String, String> headers) throws IOException {

		URL url = new URL(requestUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		// conn.setRequestProperty("Accept", "application/json");
		for (String key : headers.keySet()) {
			conn.setRequestProperty(key, headers.get(key));
		}

		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

		String output;
		StringBuffer apiResp = new StringBuffer();
		// System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			// System.out.println(output);
			apiResp.append(output);
		}

		conn.disconnect();

		return apiResp.toString();

	}

}
