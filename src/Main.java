import java.util.List;

import javax.swing.JOptionPane;

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
				
				String number = JOptionPane.showInputDialog("Qual o numero desejado?");
				
				String menssage = JOptionPane.showInputDialog("Qual a mensagem?");

				driver.get("https://web.whatsapp.com/send?phone=+55" + number);

				while (true) {
					try {
						Thread.sleep(100);
						List<WebElement> text = driver.findElements(By.className("_2S1VP"));
						text.get(0).sendKeys(menssage);
						text.get(0).sendKeys(Keys.ENTER);
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
