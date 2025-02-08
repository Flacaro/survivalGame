package Model;

public class Enemy extends Event {

	private double[] attack;
	private String type;
	private String description;
	private double[] attacks;
	private double[] damage;
	private int level;

	@Override
	public void updateQuantity(String name, int qnt) {

	}

	@Override
	public void setUp(String type, Mode mode) {

	}
}