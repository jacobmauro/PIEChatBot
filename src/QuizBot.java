import java.util.Calendar;
import java.util.Random;

public class QuizBot {

	Calendar currentTime;
	int randomMinute;
	
	public QuizBot() {
		
		currentTime = Calendar.getInstance();
		randomizeMinute();
	}
	
	public void randomizeMinute() {
		Random rand = new Random();
		randomMinute = rand.nextInt(60);
	}
	
	public int getRandomMinute() { return 45/*randomMinute*/; }
	public int getCurrentMinute() { return currentTime.get(Calendar.MINUTE); }

}
