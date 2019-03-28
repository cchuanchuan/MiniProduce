package model;
//模型类，表示一行数据
public class CsvModel {
	private Double YIELD;//收益率
	private Double DAYS_TO_MATURITY;//到期之前的时间
	private Double AMOUNT_CHF;//交易金额
	@Override
	public String toString() {
		return "Trade :YIELD=" + YIELD + "\n"+"DAYS_TO_MATURITY=" + DAYS_TO_MATURITY + "\n"+"AMOUNT_CHF=" + AMOUNT_CHF;
	}
	public CsvModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CsvModel(Double yIELD, Double dAYS_TO_MATURITY, Double aMOUNT_CHF) {
		super();
		YIELD = yIELD;
		DAYS_TO_MATURITY = dAYS_TO_MATURITY;
		AMOUNT_CHF = aMOUNT_CHF;
	}

	public Double getYIELD() {
		return YIELD;
	}
	public void setYIELD(Double yIELD) {
		YIELD = yIELD;
	}
	public Double getDAYS_TO_MATURITY() {
		return DAYS_TO_MATURITY;
	}
	public void setDAYS_TO_MATURITY(Double dAYS_TO_MATURITY) {
		DAYS_TO_MATURITY = dAYS_TO_MATURITY;
	}
	public Double getAMOUNT_CHF() {
		return AMOUNT_CHF;
	}
	public void setAMOUNT_CHF(Double aMOUNT_CHF) {
		AMOUNT_CHF = aMOUNT_CHF;
	}
	
	
	
}
