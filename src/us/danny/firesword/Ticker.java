package us.danny.firesword;

public class Ticker {
	
	private final int max;
	private int tick;
	
	public Ticker(int max) {
		if(max < 0) {
			throw new RuntimeException("ticker max < 0!");
		}
		this.max = max;
		tick = max;
	}
	
	public boolean isTime() {
		--tick;
		if(tick <= 0) {
			tick = max;
			return true;
		}
		return false;
	}
	
	//returns the result of the next call of isTime() without updating the ticker
	public boolean peekIsTime() {
		return tick <= 1;
	}
	
	public void tick() {
		--tick;
		if(tick <= 0) {
			tick = max;
		}
	}
}
