import java.util.Random;


public class Program {

	
	public static Integer getRandomValue(int min, int max) {
		Random random = new Random();
		random.setSeed(123131l);
		int value = random.nextInt(max);
		return value;
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(Program.getRandomValue(0, 100));
	}

}
