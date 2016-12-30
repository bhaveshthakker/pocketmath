package com.pocketmath.trader.beans;

public class TransactionInfo {
	
	private Long timestamp;
	private String traderId;
	private double value;
	
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public String getTraderId() {
		return traderId;
	}
	public void setTraderId(String traderId) {
		this.traderId = traderId;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "TransactionInfo [timestamp=" + timestamp + ", traderId=" + traderId + ", value=" + value + "]";
	}
	
}
