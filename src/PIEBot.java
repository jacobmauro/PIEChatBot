import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class PIEBot {
	
	private WebDriver driver;
	private String baseUrl;
	private ArrayList<String> commands;
	private ChatIcons chatIcons;
	private QuizBot quizBot;
	private Question currentQuestion;
	
	//-----------------------------------------------------------------------------------------------------------------
	//create a PIEBot, set up the driver, and navigate to the PIE login webpage
	public PIEBot() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\jamauro\\git\\PIEChatBot\\lib\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(2000, 2000));
        baseUrl = "https://tcciub.pie.iu.edu/Authentication?previousUrl=%2F";
        commands = new ArrayList<String>();
        chatIcons = new ChatIcons();
        quizBot = new QuizBot();
	}
	
	//-----------------------------------------------------------------------------------------------------------------
	//run the PIEBot
	public void run() {
		loadCommands();
		driver.get(baseUrl);
		signIn();
	}
	
	//-----------------------------------------------------------------------------------------------------------------
	//load list of commands
	public void loadCommands() {
		commands.add("no command given");
		commands.add("hello");
		commands.add("icon");
		commands.add("commands");
		commands.add("help");
		commands.add("answer");
		commands.add("quiz");
	}
	
	//-----------------------------------------------------------------------------------------------------------------
	//get username and password from user to authenticate and navigate to PIE's home page
	public void signIn() {

        //Navigate to the sign in button and click it
		WebElement signInButton= driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[2]/div/div/p/button[1]"));
        Actions action = new Actions(driver);
        action.moveToElement(signInButton).perform();
        action.moveToElement(signInButton).click().perform();
     
        //Create a new connection and wait until credentials are given
        Connection login = new Connection();
        while(login.getCredentialsGiven() == false) {
        	//do nothing
        	System.out.println("waiting for credentials");
        }
        
        String username = login.getUsername();
        char[] password = login.getPassword();
        System.out.println(username);
        
	    //Enter username
        WebElement usernameEnter = driver.findElement(By.xpath("//*[@id=\"username\"]"));
        usernameEnter.sendKeys(username);
     
        //Enter and clear password
        WebElement passwordEnter = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        String passwordAsString = String.valueOf(password);
        password = null;
        passwordEnter.sendKeys(passwordAsString);
        passwordAsString = null;
     
        //Navigate to the log in button and click it
        WebElement loginButton= driver.findElement(By.xpath("//*[@id=\"login-button\"]"));
        Actions action2 = new Actions(driver);
        action2.moveToElement(loginButton).perform();
        action2.moveToElement(loginButton).click().perform();
     
        //Safety wait if page takes a little bit to log in
        try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			System.out.println("ERROR: Sleep");
			e.printStackTrace();
		}
        
        //begin monitoring the chat after signing in
        monitorChat();
	}
	
	//-----------------------------------------------------------------------------------------------------------------
	//Send a response based on the given command
	
	/* Credit for ideas:
	 * 
	 * !icon - Amanda Gucwa
	 * !compliment - Wesley Clifford
	 * 
	 * automated quizzing - Jackson Hawk
	 * */
	
	//TODO Command Backlog
	/*
	 * Current command updates:
	 * !icon - add the rest of the chat icons
	 * 
	 * New command ideas:
	 * !compliment - sends a random compliment to a random person (or a separate compliment to all people in chat at the time)
	 * !joke - sends a random joke
	 * 
	 * Automation ideas:
	 * automatically pull quiz/training questions and ask certain people in chat (tag them) and record their response times
	 * send a message at midnight
	 * 
	 * */
	
	//if there is a command and no argument given
	public void sendResponse(String command) {

		WebElement chatBox = driver.findElement(By.xpath("//*[@id=\"chatMessageText\"]"));
		WebElement sendButton = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[2]/div/div/div[1]/div[2]/div[2]/form/button"));
		Actions sendResponse = new Actions(driver);
		
		//"!piebot" - handle when no command is given with a !piebot call
		if(command.equals("no command given")) {
			String errorMsg = "PIEBot requires an argument after the !piebot call. Type \"!piebot commands\" "
				           	+ "to see a list of commands, or type \"!piebot help\" for help using PIEBot.";
			chatBox.sendKeys(errorMsg);
			
		//"!piebot hello" - tell the user hello! :)
		}else if(command.equals("hello")) {
			chatBox.sendKeys("Hello! :)");
			
		//"!piebot icon" - send a random chat icon
		}else if(command.equals("icon")) {
			String randomChatIcon = chatIcons.getRandomChatIcon();
			chatBox.sendKeys(randomChatIcon);
			
		//"!piebot commands" - enumerate all current PIEBot commands
		}else if(command.equals("commands")) {
			String stringOfCommands = getStringOfCommands();
			chatBox.sendKeys("The available PIEBot commands are: " + stringOfCommands);
			
		//"!piebot help" - give an explanation on how PIEBot works
		}else if(command.equals("help")) {
			String help = "PIEBot is a PIE chat bot currently being worked on by Jacob Mauro. The bot is called in format \"!piebot <command>.\" "
						+ "To see all available commands, type \"!piebot commands.\" If you are interested in assisting in the development of "
						+ "PIEBot, contact Jacob at jamauro@iu.edu!";
			chatBox.sendKeys(help);
		
		//testing for QuizBot
		}else if(command.equals("quiz")) {
			System.out.println("enters quiz!");
			currentQuestion = quizBot.getRandomQuestion();
			String questionString = currentQuestion.getQuestion();
			String answers = currentQuestion.printAnswers();
			
			String quiz = questionString + " " + answers;
			System.out.println("here is the quiz: " + quiz);
			chatBox.sendKeys(quiz);
		}
		
		sendResponse.moveToElement(sendButton).perform();
		sendResponse.moveToElement(sendButton).click().perform();
	}
	
	//if there is a command and an argument given
	public void sendResponse(String command, String argument) {
		
		WebElement chatBox = driver.findElement(By.xpath("//*[@id=\"chatMessageText\"]"));
		WebElement sendButton = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[2]/div/div/div[1]/div[2]/div[2]/form/button"));
		Actions sendResponse = new Actions(driver);
		
		if(command.equals("answer")) {
			
			//if the given argument is a valid answer
			if(argument.toLowerCase().equals("a") || argument.toLowerCase().equals("b") || argument.toLowerCase().equals("c") || argument.toLowerCase().equals("d")) {
				
				//if the answer is correct
				if(argument.equals(currentQuestion.getAnswerLetter())) {
					chatBox.sendKeys("Correct answer!");
				//if the answer is wrong
				}else {
					chatBox.sendKeys("Incorrect answer.");
				}
			}
			
		}
		
		sendResponse.moveToElement(sendButton).perform();
		sendResponse.moveToElement(sendButton).click().perform();
	}
	
	//-----------------------------------------------------------------------------------------------------------------
	//Monitor the chat and respond to any messages beginning with !piebot
	public void monitorChat() {
		
		WebElement newestMessage;
        String[] messageWords;
        
        //run indefinitely
        while(true) {
        	System.out.println("monitoring");
        	//wait 2 seconds between each check of the chat
        	try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				System.out.println("ERROR: sleep");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	//determine if a random quiz should be given
        	System.out.println("curr: " + quizBot.getCurrentMinute());
        	System.out.println("rand: " + quizBot.getRandomMinute());
        	if(quizBot.getCurrentMinute() == quizBot.getRandomMinute()) {
        		System.out.println("quiz time!");
        		quizBot.randomizeMinute();
        		System.out.println("randomized minute: " + quizBot.getRandomMinute());
        		sendResponse("quiz");
        	}
        	
        	//get the most recent chat message
        	newestMessage = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[2]/div/div/div[1]/div[2]/div[2]/chat-messages-list/div/li[1]/div[1]"));
        	String messageContents = newestMessage.getText();
        	messageContents = messageContents.trim();
        	messageWords = messageContents.split(" ");

        	System.out.println("Message words: " + Arrays.toString(messageWords));
        	
        	//if the PIEBot has been called
        	if(messageWords[0].toLowerCase().equals("!piebot")) {
 
        		//if there is no command given
        		if(messageWords.length == 1) {
        			sendResponse("no command given");
        			
        		//if there is a command given with no argument
        		}else if(messageWords.length == 2 && commands.contains(messageWords[1])){
        			sendResponse(messageWords[1].toLowerCase());
        		
        		//if there is a command given with an argument
        		}else if(messageWords.length == 3 && commands.contains(messageWords[1])) {
        			sendResponse(messageWords[1].toLowerCase(), messageWords[2].toLowerCase());
        		}
        	}
        }
	}
	
	//-----------------------------------------------------------------------------------------------------------------
	//returns a string with all available commands
	public String getStringOfCommands() {
		
		String stringOfCommands = "";
		for(int i = 0; i < commands.size(); i++) {
			
			//don't add behind-the-scenes commands (add them to the if statement as they are created, rework this if there become too many)
			if(!commands.get(i).equals("no command given")) {
				stringOfCommands += commands.get(i) + ", ";
			}
		}
		
		//return string of commands without last comma and space
		return stringOfCommands.substring(0, stringOfCommands.length()-2);
	}
	
	//-----------------------------------------------------------------------------------------------------------------
	//run PIEBot
    public static void main(String[] args) throws InterruptedException {
    	PIEBot piebot = new PIEBot();
    	piebot.run();
    }

}