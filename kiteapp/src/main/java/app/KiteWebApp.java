package app;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class KiteWebApp {

	public ChromeDriver driver = null;
	static int trade1 = 1;
	String spltAmt[];
	String Amount, profitAmt;
	float MaxLoss_Deduction;
	static float tempVar = 0;

	@Test
	public void run() throws InterruptedException {
		for (int j = 1; j <=1; j++) {
			fn_LimitmtCheck();
			fn_TotalOrderCheck();
			fn_ProfitCheck();
			Thread.sleep(20000);
		}
	}

	public void fn_LimitmtCheck() throws InterruptedException {
		try {
			if (driver == null) {
				System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver_109.exe");
				ChromeOptions ops = new ChromeOptions();
				ops.addArguments("--remote-allow-origins=*");
				ops.addArguments("--disable-notifications");
				// ops.addArguments("--headless");
				driver = new ChromeDriver(ops);
				driver.navigate().to("https://kite.zerodha.com/");
				driver.manage().window().maximize();
				driver.findElement(By.id("userid")).sendKeys("xb5025");
				driver.findElement(By.id("password")).sendKeys("Aaruthran@2018");
				driver.findElement(By.xpath("//button[@type='submit']")).click();
				Thread.sleep(10000);
			}

			driver.findElement(By.xpath("//span[text()='Positions']")).click();
			Thread.sleep(2000);
			Amount = driver.findElement(By.xpath("(//table[tbody])[1]/tfoot[1]/tr[1]/td[4]")).getText();
			if (Float.parseFloat(Amount) <= 10000) {
				driver.findElement(By.xpath("(//table[tbody])[1]/thead/tr/th//label/span")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//button[@type='button']")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//span[text()='Exit']")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//span[@class='user-id']")).click();
				driver.findElement(By.xpath("//*[text()=' Console']")).click();
				Thread.sleep(3000);

				Set<String> TotalWindows = driver.getWindowHandles();
				for (String window : TotalWindows) {
					driver.switchTo().window(window);
					System.out.println("fn_LimitmtCheck >> Title of the page:" + driver.getTitle());

					if (driver.getTitle().contentEquals("Dashboard / Console")) {
						driver.findElement(By.xpath("//span[text()='Account']")).click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("//div[text()='Segments']")).click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("//label[@for='NSE_EQ']")).click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("//label[@for='BSE_EQ']")).click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("//label[@for='NSE_FO']")).click();
						Thread.sleep(2000);
						// driver.findElement(By.xpath("//button[contains(text(),'Continue')]")).click();
						driver.close();
					}
				}
				Set<String> tWin = driver.getWindowHandles();
				for (String win : tWin) {
					driver.switchTo().window(win);
					if (driver.getTitle().contentEquals("Positions / Kite")) {
						break;
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fn_TotalOrderCheck() throws InterruptedException {
		try {
			driver.findElement(By.xpath("//span[text()='Orders']")).click();
			Thread.sleep(3000);
			List<WebElement> lstElemnt = driver.findElements(By.xpath("//div[@class='page-nav']/a"));
			System.out.println("fn_TotalOrderCheck >> Total menu's:" + lstElemnt.size());
			for (int i = 1; i <= lstElemnt.size(); i++) {
				if (driver.findElement(By.xpath("(//div[@class='page-nav']/a)[" + i + "]")).getText()
						.equalsIgnoreCase("Orders")) {
					break;
				} else {
					driver.findElement(By.xpath("(//div[@class='page-nav']/a)[" + i + "]")).click();
				}
			}
			String prefix1 = driver.findElement(By.xpath("(//h3[@class='page-title small'])[1]")).getText();
			Thread.sleep(3000);
			System.out.println("fn_TotalOrderCheck >> Captured Text :" + prefix1);
			String[] oldText = prefix1.split("\\)");
			Thread.sleep(3000);
			String[] NewText = oldText[0].split("\\(");
			System.out.println("fn_TotalOrderCheck >> Total Executed Orders Count :" + NewText[1].trim());
			if (Float.parseFloat(NewText[1].trim()) >= 60) {
				driver.findElement(By.xpath("//span[text()='Positions']")).click();
				Thread.sleep(5000);
				driver.findElement(By.xpath("(//table[tbody])[1]/thead/tr/th//label/span")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//button[@type='button']")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//span[text()='Exit']")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//span[@class='user-id']")).click();
				driver.findElement(By.xpath("//*[text()=' Console']")).click();
				Thread.sleep(3000);

				Set<String> TotalWindows = driver.getWindowHandles();
				for (String window : TotalWindows) {
					driver.switchTo().window(window);
					System.out.println("fn_LimitmtCheck >> Title of the page:" + driver.getTitle());

					if (driver.getTitle().contentEquals("Dashboard / Console")) {
						driver.findElement(By.xpath("//span[text()='Account']")).click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("//div[text()='Segments']")).click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("//label[@for='NSE_EQ']")).click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("//label[@for='BSE_EQ']")).click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("//label[@for='NSE_FO']")).click();
						Thread.sleep(2000);
						// driver.findElement(By.xpath("//button[contains(text(),'Continue')]")).click();
						driver.close();

					}
				}
				Set<String> tWin = driver.getWindowHandles();
				for (String win : tWin) {
					driver.switchTo().window(win);
					if (driver.getTitle().contentEquals("Positions / Kite")) {
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void fn_ProfitCheck() throws InterruptedException {
		try {
			driver.findElement(By.xpath("//span[text()='Positions']")).click();
			Thread.sleep(2000);
			profitAmt = driver.findElement(By.xpath("(//table[tbody])[1]/tfoot[1]/tr[1]/td[4]")).getText();
			Thread.sleep(2000);
			if (tempVar < Float.parseFloat(profitAmt)) {
				tempVar = Float.parseFloat(profitAmt);
			}
			Thread.sleep(2000);
			MaxLoss_Deduction = tempVar - 5000;
			System.out.println("fn_ProfitCheck >> After Deduction : " + MaxLoss_Deduction);
			Thread.sleep(2000);
			if (Float.parseFloat(profitAmt) < MaxLoss_Deduction) {
				driver.findElement(By.xpath("(//table[tbody])[1]/thead/tr/th//label/span")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//button[@type='button']")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//span[text()='Exit']")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//span[@class='user-id']")).click();
				driver.findElement(By.xpath("//*[text()=' Console']")).click();
				Thread.sleep(3000);

				Set<String> TotalWindows = driver.getWindowHandles();
				for (String window : TotalWindows) {
					driver.switchTo().window(window);
					System.out.println("fn_LimitmtCheck >> Title of the page:" + driver.getTitle());

					if (driver.getTitle().contentEquals("Dashboard / Console")) {
						driver.findElement(By.xpath("//span[text()='Account']")).click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("//div[text()='Segments']")).click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("//label[@for='NSE_EQ']")).click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("//label[@for='BSE_EQ']")).click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("//label[@for='NSE_FO']")).click();
						Thread.sleep(2000);
						// driver.findElement(By.xpath("//button[contains(text(),'Continue')]")).click();
						driver.close();

					}
				}
				Set<String> tWin = driver.getWindowHandles();
				for (String win : tWin) {
					driver.switchTo().window(win);
					if (driver.getTitle().contentEquals("Positions / Kite")) {
						break;
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
