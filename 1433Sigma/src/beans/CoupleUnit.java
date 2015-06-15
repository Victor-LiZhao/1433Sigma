package beans;

public class CoupleUnit {
	
	private String seqA;
	private String seqB;
	private double rfA;
	private double rfB;
	private Double y;
	private double m1;
	private double m2;
	private double m3;
	public String getSeqA() {
		return seqA;
	}
	public void setSeqA(String seqA) {
		this.seqA = seqA;
	}
	public String getSeqB() {
		return seqB;
	}
	public void setSeqB(String seqB) {
		this.seqB = seqB;
	}
	public double getRfA() {
		return rfA;
	}
	public void setRfA(double rfA) {
		this.rfA = rfA;
	}
	public double getRfB() {
		return rfB;
	}
	public void setRfB(double rfB) {
		this.rfB = rfB;
	}
	public Double getY() {
		return y;
	}
	public void setY(Double y) {
		this.y = y;
	}
	public double getM1() {
		return m1;
	}
	public void setM1(double m1) {
		this.m1 = m1;
	}
	public double getM2() {
		return m2;
	}
	public void setM2(double m2) {
		this.m2 = m2;
	}
	public double getM3() {
		return m3;
	}
	public void setM3(double m3) {
		this.m3 = m3;
	}
	public String parse2String() {
		String line=seqA+"\t"+seqB+"\t"+Double.valueOf(m1).floatValue()+"\t"+Double.valueOf(m2).floatValue()+"\t"+Double.valueOf(m3).floatValue()+"\t"+rfA+"\t"+rfB+"\t"+Double.valueOf(y).floatValue();
		return line;
	}
	
}
