package model;
//模型类，表示一行数据
public class BondModel {
	private Double Y;//收益率
	private Double D;//到期之前的时间
	private Double A;//交易金额
	public BondModel(Double y, Double d, Double a) {
		super();
		Y = y;
		D = d;
		A = a;
	}
	public BondModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "[Y=" + Y + ", D=" + D + ", A=" + A + "]";
	}
	public Double getY() {
		return Y;
	}
	public void setY(Double y) {
		Y = y;
	}
	public Double getD() {
		return D;
	}
	public void setD(Double d) {
		D = d;
	}
	public Double getA() {
		return A;
	}
	public void setA(Double a) {
		A = a;
	}
}
