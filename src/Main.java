import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.Cookie;

public class Main {

	public static void main(String[] args) {
		//Initializing driver for chrome and opening the website
		WebDriver driver = new ChromeDriver();
		driver.get("http://10.0.1.86/tatoc");
		//traversing through pages
		driver.findElement(By.linkText("Basic Course")).click();
		driver.findElement(By.className("greenbox")).click();
		
		String s1 = driver.switchTo().frame("main").findElement(By.id("answer")).getAttribute("class");
		while(true) {
			String s2 = driver.switchTo().frame("child").findElement(By.id("answer")).getAttribute("class");
			driver.switchTo().parentFrame();
			if(s1.equals(s2)) {
				break;
			}
			driver.findElement(By.linkText("Repaint Box 2")).click();
		}
		driver.findElement(By.linkText("Proceed")).click();
       
		
		Actions act=new Actions(driver);
		WebElement to = driver.findElement(By.id("dropbox"));
		WebElement from = driver.findElement(By.id("dragbox"));
		act.dragAndDrop(from,to).build().perform();
		driver.findElement(By.linkText("Proceed")).click();
		
		driver.findElement(By.linkText("Launch Popup Window")).click();
		
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		WebElement textbox= driver.findElement(By.id("name"));
		textbox.sendKeys("karan");
		driver.findElement(By.id("submit")).click();
		
		driver.switchTo().window(tabs2.get(0));
		driver.findElement(By.linkText("Proceed")).click();
		
		driver.findElement(By.linkText("Generate Token")).click();
		String str = driver.findElement(By.id("token")).getText();
		driver.manage().addCookie(new Cookie("Token",str.substring(7)));
		driver.findElement(By.linkText("Proceed")).click();
		//driver.quit();

	}

}
