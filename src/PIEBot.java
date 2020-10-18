import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class PIEBot {


    public static void main(String[] args) throws InterruptedException {
    	
        // declaration and instantiation of objects/variables
    	System.setProperty("webdriver.chrome.driver", "C:\\Users\\jake_\\eclipse-workspace\\PIEChatBot\\lib\\chromedriver.exe");
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
     
        //get user input for username and password
	    Scanner sc = new Scanner(System.in);
	    System.out.print("Username: ");
	    String username = sc.nextLine();
	    System.out.println("Password: ");
	    String password = sc.nextLine();
	    sc.close();
        
	    //enter username
        WebElement usernameEnter = driver.findElement(By.xpath("//*[@id=\"username\"]"));
        usernameEnter.sendKeys(username);
     
        //enter password
        WebElement passwordEnter = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        passwordEnter.sendKeys(password);
     
        //"Log in" button
        WebElement loginButton= driver.findElement(By.xpath("//*[@id=\"login-button\"]"));
       
        //Navigate to the log in button and click it
        Actions action2 = new Actions(driver);
        action2.moveToElement(loginButton).perform();
        action2.moveToElement(loginButton).click().perform();
     
        //close Chrome
        //driver.close();
       
    }

}