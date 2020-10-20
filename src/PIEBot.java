import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class PIEBot {
	
	//this is a test
    public static void main(String[] args) throws InterruptedException {
    	
        // declaration and instantiation of objects/variables
    	System.setProperty("webdriver.chrome.driver", "C:\\Users\\jamauro\\git\\PIEChatBot\\lib\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(2000, 2000));
        String baseUrl = "https://tcciub.pie.iu.edu/Authentication?previousUrl=%2F";	
        
        // launch Chrome and direct it to the Base URL
        driver.get(baseUrl);

        // get the value of the title
        String title = driver.getTitle();
        System.out.println(title);
        
        
        //"Sign In Using IU Login" button
        WebElement signInButton= driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[2]/div/div/p/button[1]"));
     
        //Navigate to the sign in button and click it
        Actions action = new Actions(driver);
        action.moveToElement(signInButton).perform();
        action.moveToElement(signInButton).click().perform();
     
        Connection login = new Connection();
        
        while(login.getCredentialsGiven() == false) {
        	//do nothing
        	System.out.println("here");
        }
        
        String username = login.getUsername();
        char[] password = login.getPassword();
        
        
	    //enter username
        WebElement usernameEnter = driver.findElement(By.xpath("//*[@id=\"username\"]"));
        usernameEnter.sendKeys(username);
     
        //enter password
        WebElement passwordEnter = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        String passwordAsString = String.valueOf(password);
        password = null;
        passwordEnter.sendKeys(passwordAsString);
        passwordAsString = null;
     
        //"Log in" button
        WebElement loginButton= driver.findElement(By.xpath("//*[@id=\"login-button\"]"));
       
        //Navigate to the log in button and click it
        Actions action2 = new Actions(driver);
        action2.moveToElement(loginButton).perform();
        action2.moveToElement(loginButton).click().perform();
     
        TimeUnit.SECONDS.sleep(10);
        
        WebElement newestMessage;
        String[] messageWords;
        while(true) {
        	TimeUnit.SECONDS.sleep(2);
        	
        	newestMessage = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[2]/div/div/div[1]/div[2]/div[2]/chat-messages-list/div/li[1]/div[1]"));
        	String messageContents = newestMessage.getText();
        	messageContents = messageContents.trim();
        	messageWords = messageContents.split(" ");

        	if(messageWords.length > 1) {
        	
	        	System.out.println("first: " + messageWords[0]);
	        	System.out.println("second: " + messageWords[1]);
	        	
	        	if(messageWords[0].equals("!piebot")) {
	        		if(messageWords[1].equals("hello")) {
	//        			sendMessage("Hello! :)"); MAKE A SEND MESSAGE METHOD AND REWORK THE DESIGN***
	        			WebElement message = driver.findElement(By.xpath("//*[@id=\"chatMessageText\"]"));
	        			message.sendKeys("Hello! :)");
	        			
	        			WebElement sendButton = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[2]/div/div/div[1]/div[2]/div[2]/form/button"));
	        			Actions action3 = new Actions(driver);
	        			action3.moveToElement(sendButton).perform();
	        			action3.moveToElement(sendButton).click().perform();
	        		}
	        	}
        	}
        	
        }
        
        //gets top message but might not work for images? check that, might be ...div[1]/img
//        WebElement chatMessage = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[2]/div/div/div[1]/div[2]/div[2]/chat-messages-list/div/li[1]/div[1]"));
//        System.out.println(chatMessage.getText());
        
        //close Chrome
        //driver.close();
       
    }

}