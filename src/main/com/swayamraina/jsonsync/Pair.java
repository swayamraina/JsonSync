package main.com.swayamraina.jsonsync;

public class Pair<U,V> {
	
	private U first;
	private V second;
	
	public Pair(U first, V second) {
		this.setFirst(first);
		this.setSecond(second);
	}
	
	public V getSecond() {
		return second;
	}
	
	public void setSecond(V second) {
		this.second = second;
	}
	
	public U getFirst() {
		return first;
	}
	
	public void setFirst(U first) {
		this.first = first;
	}
	
	public boolean isEmptyPair() {
		return (first==null && second==null) ? true : false;
	}
}

