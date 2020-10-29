import java.util.ArrayList;
import java.util.Random;

public class Question {

	ArrayList<Question> questions;
	
	String question;
	String[] answers;
	String correctAnswerLetter;
	
//	public Question() {
//		loadQuestions();
//	}
	
	public Question(String question, String correctAnswerLetter, String[] answers) {
		
		this.answers = new String[4];
		this.questions = new ArrayList<>();
		this.question = question;
		this.correctAnswerLetter = correctAnswerLetter;
		
		for(int i = 0; i < 4; i++) {
			this.answers[i] = answers[i];
		}
	}
	
//	public void loadQuestions() {
//		
//		String[] answers1 = {"(A) Back in the paper and toner closet on the 1st floor.",
//						    "(B) A deliverable box in the basement.",
//						    "(C) Place next to the trash can in an orderly fashion.",
//						    "(D) It needs to be delivered to the 4th floor."};
//		System.out.println("gets here");
//		questions.add(new Question("Where do you put empty toner?", "d", answers1));
//		System.out.println("then here");
//		String[] answers2 = {"(A) Put them in the paper and toner closet for Assist to pick up later.",
//							 "(B) Mark as trash and leave it neatly next to a trash can.",
//							 "(C) Leave it on your desk and call Lowell from the Operations Team to pick it up.",
//							 "(D) Mark it for return and give it to the downstair's mail room to handle."};
//		questions.add(new Question("What do you do with full waste toner bottles?", "b", answers2));
//	}
	
//	public Question getRandomQuestion() {
//		
//		Random rand = new Random();
//		return questions.get(rand.nextInt(questions.size()-1));
//	}
//	
	public String printAnswers() {
		
		String answersAsString = "";
		for(int i = 0; i < answers.length; i++) {
			answersAsString += answers[i] + " ";
		}
		
		return answersAsString.substring(0, answersAsString.length()-1);
	}
	
	public String getQuestion() { return this.question; }
	public String getAnswerLetter() { return this.correctAnswerLetter; }
	public String[] getAnswers() { return this.answers; }

	
}
