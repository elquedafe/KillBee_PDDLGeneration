package parser;

public class Link {
	String src;
	String dst;
	public Link(String src, String dst) {
		super();
		this.src = src;
		this.dst = dst;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getDst() {
		return dst;
	}
	public void setDst(String dst) {
		this.dst = dst;
	}
	
	@Override
	public String toString() {
		return "directly-connected "+src+" "+dst;
		
	}
}
