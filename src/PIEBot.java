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
	
	//-----------------------------------------------------------------------------------------------------------------
	//create a PIEBot, set up the driver, and navigate to the PIE login webpage
	public PIEBot() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\jamauro\\git\\PIEChatBot\\lib\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(2000, 2000));
        baseUrl = "https://tcciub.pie.iu.edu/Authentication?previousUrl=%2F";
        commands = new ArrayList<String>();
        chatIcons = new ChatIcons();
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
		commands.add("hello");
		commands.add("icon");
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
	
	//TODO Command Backlog
	/*
	 * Current commands:
	 * !icon - add the rest of the chat icons
	 * 
	 * New commands:
	 * !compliment - sends a random compliment to a random person (or a separate compliment to all people in chat at the time)
	 * 
	 * Automation ideas:
	 * automatically pull quiz/training questions and ask certain people in chat (tag them) and record their response times
	 * 
	 * */
	
	public void sendResponse(String command) {
		System.out.println("enters sendResponse");
		WebElement chatBox = driver.findElement(By.xpath("//*[@id=\"chatMessageText\"]"));
		WebElement sendButton = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[2]/div/div/div[1]/div[2]/div[2]/form/button"));
		Actions sendResponse = new Actions(driver);
		System.out.println("the command was: " + command);
		if(command.equals("hello")) {
			System.out.println("Hello command was detected.");
			chatBox.sendKeys("Hello! :)");
		}else if(command.equals("icon")) {
			System.out.println("Icon command was detected.");
			String randomChatIcon = chatIcons.getRandomChatIcon();
			chatBox.sendKeys(randomChatIcon);
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
        	
        	//get the most recent chat message
        	newestMessage = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[2]/div/div/div[1]/div[2]/div[2]/chat-messages-list/div/li[1]/div[1]"));
        	String messageContents = newestMessage.getText();
        	messageContents = messageContents.trim();
        	messageWords = messageContents.split(" ");

        	System.out.println("Message words: " + Arrays.toString(messageWords));
        	
        	//if the PIEBot has been called
        	if(messageWords[0].equals("!piebot")) {
        		System.out.println("saw piebot");
        		if(messageWords.length == 1) {
        			System.out.println("in length of 1");
        			//handle when !piebot is called with no command
        		}else if(commands.contains(messageWords[1])){
        			System.out.println("gets to right before calling sendResponse()");
        			sendResponse(messageWords[1].toLowerCase());
        		}
        	}
        }
	}
	
	//run PIEBot
    public static void main(String[] args) throws InterruptedException {
    	PIEBot piebot = new PIEBot();
    	piebot.run();
    }

}