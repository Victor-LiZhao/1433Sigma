package beans;

public class PCValue {
	private Character aa;
	private Double h;
	private Double vsc;
	private Double p1;
	private Double p2;
	private Double sasa;
	private Double ncisc;
	public PCValue(){}
	public PCValue(String line){
		String s[]=line.split(" ");
		aa=Character.valueOf(s[0].charAt(0));
		h=Double.valueOf(s[1]);
		vsc=Double.valueOf(s[2]);
		p1=Double.valueOf(s[3]);
		p2=Double.valueOf(s[4]);
		sasa=Double.valueOf(s[5]);
		ncisc=Double.valueOf(s[6]);
	}
	public Character getAa() {
		return aa;
	}
	public void setAa(Character aa) {
		this.aa = aa;
	}
	public Double getH() {
		return h;
	}
	public void setH(Double h) {
		this.h = h;
	}
	public Double getVsc() {
		return vsc;
	}
	public void setVsc(Double vsc) {
		this.vsc = vsc;
	}
	public Double getP1() {
		return p1;
	}
	public void setP1(Double p1) {
		this.p1 = p1;
	}
	public Double getP2() {
		return p2;
	}
	public void setP2(Double p2) {
		this.p2 = p2;
	}
	public Double getSasa() {
		return sasa;
	}
	public void setSasa(Double sasa) {
		this.sasa = sasa;
	}
	public Double getNcisc() {
		return ncisc;
	}
	public void setNcisc(Double ncisc) {
		this.ncisc = ncisc;
	}
	
}
