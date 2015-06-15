package beans;

public class Node {

	private String seq;
	private double rf;
	private int rank;
	private int flag;
	
	public Node(){
		this.flag=0;
	}
	
	public Node(String line){
		String s[]=line.split("\t");
		this.seq=s[0];
		this.rf=Double.valueOf(s[1]);
		this.rank=Integer.valueOf(s[2]);
		this.flag=0;
	}
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public double getRf() {
		return rf;
	}
	public void setRf(double rf) {
		this.rf = rf;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
}
