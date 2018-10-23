import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Main {

	private static WebDriver driver;

	public static void main(String[] args) {

		System.setProperty("webdriver.gecko.driver", "C:\\chromedriver\\geckodriver.exe");
		driver = new FirefoxDriver();

		driver.get("https://web.whatsapp.com/");

		new Thread() {
			public void run() {
				while (true) {
					List<WebElement> elems = driver.findElements(By.className("_2EZ_m"));
					if (elems.size() == 0) {
						break;
					} else {
						System.out.println("Escanei o QR code!");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}

				driver.get("https://web.whatsapp.com/send?phone=+5583996283690");

				while (true) {
					try {
						Thread.sleep(100);
						List<WebElement> text = driver.findElements(By.className("_2S1VP"));
						text.get(0).sendKeys("dasdasdas");
						text.get(0).sendKeys(Keys.ENTER);
						break;
					} catch (IndexOutOfBoundsException e) {
						System.out.println("Conversa Carregando!");
					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			};
		}.start();

	}

}
