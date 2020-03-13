package io.kyoumatrix.singletontestcontainers;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.DefaultRecordingFileFactory;

import java.io.File;

@SpringBootTest
class SingletonBrowserContainerTest {

	SingletonBrowserContainer CHROME_BROWSER = SingletonBrowserContainer.getInstance();

	BrowserWebDriverContainer CHROME_CONTAINER_INSTANCE = new BrowserWebDriverContainer<>("selenium/standalone-chrome:latest")
			.withCapabilities(DesiredCapabilities.chrome())
			.withRecordingMode(BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL, new File("./target/"))
			.withRecordingFileFactory(new DefaultRecordingFileFactory());



	@Test
	void contextLoads() {
		CHROME_BROWSER.setBROWSER_CONTAINER(CHROME_CONTAINER_INSTANCE);
		CHROME_BROWSER.getBROWSER_CONTAINER().start();

	}

	@Test
	public void browserCreated(){

		Assert.assertTrue(CHROME_BROWSER.getBROWSER_CONTAINER().isCreated());
		System.out.println("");
		System.out.println("____________________________[  CHROME  ]________________________________");
		System.out.println("");

		System.out.println("CONTAINER_ID : "+CHROME_BROWSER.getBROWSER_CONTAINER().getContainerId());
		System.out.println("DOCKER_IMAGE : "+CHROME_BROWSER.getBROWSER_CONTAINER().getDockerImageName());

		System.out.println("____________________________[  CHROME  ]________________________________");
		System.out.println("");

	}



}
