import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class QuizBot {

	Calendar currentTime;
	int randomMinute;
	Question question;
	ArrayList<Question> questions;
	
	int testMinute = 31;
	
	public QuizBot() {
		
		questions = new ArrayList<>();
		currentTime = Calendar.getInstance();
//		randomizeMinute(); **UNCOMMENT THIS AFTER TESTING**
		loadQuestions();
	}
	
	public void randomizeMinute() {
		Random rand = new Random();
		randomMinute = rand.nextInt(60);
		
		testMinute = -1;
	}
	
//	public Question getRandomQuestion() {
//		
//		return question.getRandomQuestion();
//	}
	
	public Question getRandomQuestion() {
	
		Random rand = new Random();
		return questions.get(rand.nextInt(questions.size()-1));
	}

//	public String printAnswers() {
//	
//		String answersAsString = "";
//		for(int i = 0; i < answers.length; i++) {
//			answersAsString += answers[i] + " ";
//		}
//	
//		return answersAsString.substring(0, answersAsString.length()-1);
//	}
	
	public void loadQuestions() {
		
		String[] answers1 = {"(A) Back in the paper and toner closet on the 1st floor.",
						    "(B) A deliverable box in the basement.",
						    "(C) Place next to the trash can in an orderly fashion.",
						    "(D) It needs to be delivered to the 4th floor."};
		System.out.println("gets here");
		questions.add(new Question("Where do you put empty toner?", "d", answers1));
		System.out.println("then here");
		String[] answers2 = {"(A) Put them in the paper and toner closet for Assist to pick up later.",
							 "(B) Mark as trash and leave it neatly next to a trash can.",
							 "(C) Leave it on your desk and call Lowell from the Operations Team to pick it up.",
							 "(D) Mark it for return and give it to the downstair's mail room to handle."};
		questions.add(new Question("What do you do with full waste toner bottles?", "b", answers2));
	}
	
	
	
	public int getRandomMinute() { return testMinute/*randomMinute*/; }
//	public int getCurrentMinute() { return currentTime.get(Calendar.MINUTE); }
	public int getCurrentMinute() {
		Calendar currentTime = Calendar.getInstance();
		return currentTime.get(Calendar.MINUTE);
	}

}
