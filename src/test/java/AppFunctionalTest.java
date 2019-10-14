package laboratorio;

import static org.junit.Assert.assertEquals;

import java.io.File;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testcontainers.DockerClientFactory;
import org.testcontainers.containers.BrowserWebDriverContainer;

public class AppFunctionalTest {
	@Rule
	public BrowserWebDriverContainer chrome = new BrowserWebDriverContainer()
			.withRecordingMode(BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL, new File("target"))
			.withDesiredCapabilities(DesiredCapabilities.chrome());

	@Test
	public void tittlePageOk() throws Exception {
		RemoteWebDriver driver = chrome.getWebDriver();

		driver.get("http://" + DockerClientFactory.instance().dockerHostIpAddress() + ":8888/laboratorio/");
		assertEquals("Laboratorio - Docker CI/CD", driver.getTitle());
		
		Thread.sleep(2000);
		
		assertEquals("HELLO, WORLD!", driver.findElement(By.xpath("//*[@id=\"welcome-message\"]")).getText());
		
		Thread.sleep(2000);
	}
}
