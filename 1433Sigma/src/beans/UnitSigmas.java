package beans;

public class UnitSigmas {
	
	private String seqA;
	private String seqB;
	private double rfA;
	private double rfB;
	private Double y;
	private double x1;
	private double x2;
	private double x3;
	public UnitSigmas(){
		
	}
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
	public double getX1() {
		return x1;
	}
	public void setX1(double x1) {
		this.x1 = x1;
	}
	public double getX2() {
		return x2;
	}
	public void setX2(double x2) {
		this.x2 = x2;
	}
	public double getX3() {
		return x3;
	}
	public void setX3(double x3) {
		this.x3 = x3;
	}
	public String parse2String(){
		String line=seqA+"\t"+seqB+"\t"+rfA+"\t"+rfB+"\t"+x1+"\t"+x2+"\t"+x3+"\t"+(x1+x2+x3)+"\t"+y;
		return line;
	}
}
