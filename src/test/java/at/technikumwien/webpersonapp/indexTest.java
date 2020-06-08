package at.technikumwien.webpersonapp;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Tag("e2e-test")
public class indexTest {
    @LocalServerPort
    private long port;
    private WebDriver driver;

    @BeforeAll
    public static void setUpBeforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver(
                new ChromeOptions().setHeadless(true)
        );
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testIndex() throws Exception{
        driver.get("http://localhost:" + port);

        WebElement button = driver.findElement(By.tagName("button"));
        assertEquals("alle Personen anzeigen", button.getText());

        var lis = driver.findElements(By.tagName("li"));
        assertEquals(2, lis.size());

        button.submit();
        assertEquals("http://localhost:" + port + "/?all=true", driver.getCurrentUrl());
    }

    @Test
    public void testIndexWithAll() throws Exception{
        driver.get("http://localhost:" + port + "/?all=true");

        WebElement button = driver.findElement(By.tagName("button"));
        assertEquals("nur aktivierte Personen anzeigen", button.getText());

        var lis = driver.findElements(By.tagName("li"));
        assertEquals(3, lis.size());

        button.submit();
        assertEquals("http://localhost:" + port + "/?", driver.getCurrentUrl());
    }
}
