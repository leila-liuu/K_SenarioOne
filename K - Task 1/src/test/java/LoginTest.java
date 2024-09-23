import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest {

    WebDriver driver;
    String baseUrl = "https://stage10.kada.ai";

    // Set up WebDriver
    public void setUp() {
        // Automatically manage ChromeDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    // Test Case 1: Verify login page loads successfully
    public void testLoginPageLoads() {
        System.out.println("Test Case 1: Verify login page loads successfully");
        try {
            WebElement usernameField = driver.findElement(By.id("username"));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("kc-login"));

            if (usernameField.isDisplayed() && passwordField.isDisplayed() && loginButton.isDisplayed()) {
                System.out.println("Test Passed: Login page loaded successfully.");
            } else {
                System.out.println("Test Failed: Some elements are missing.");
            }
        } catch (Exception e) {
            System.out.println("Test Failed: Exception occurred - " + e.getMessage());
        }
    }

    // Test Case 2: Verify login with valid credentials
    public void testValidLogin() {
        System.out.println("Test Case 2: Verify login with valid credentials");
        try {
            WebElement username = driver.findElement(By.id("username"));
            WebElement password = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("kc-login"));

            username.sendKeys("user2");
            password.sendKeys("welcome2KADA");
            loginButton.click();

            String expectedUrl = "https://stage10.kada.ai/home";
            if (driver.getCurrentUrl().equals(expectedUrl)) {
                System.out.println("Test Passed: Valid login successful.");
            } else {
                System.out.println("Test Failed: Redirected to wrong page.");
            }
        } catch (Exception e) {
            System.out.println("Test Failed: Exception occurred - " + e.getMessage());
        }
    }

    // Test Case 3: Verify login with invalid username
    public void testInvalidUsername() {
        System.out.println("Test Case 3: Verify login with invalid username");
        try {
            WebElement username = driver.findElement(By.id("username"));
            WebElement password = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("kc-login"));

            username.sendKeys("invalidUser");
            password.sendKeys("welcome2KADA");
            loginButton.click();

            WebElement errorMessage = driver.findElement(By.id("errorMsg"));
            if (errorMessage.isDisplayed()) {
                System.out.println("Test Passed: Error message displayed for invalid username.");
            } else {
                System.out.println("Test Failed: Error message not displayed.");
            }
        } catch (Exception e) {
            System.out.println("Test Failed: Exception occurred - " + e.getMessage());
        }
    }

    // Test Case 4: Verify login with valid username and invalid password
    public void testInvalidPassword() {
        System.out.println("Test Case 4: Verify login with valid username and invalid password");
        try {
            WebElement username = driver.findElement(By.id("username"));
            WebElement password = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("kc-login"));

            username.sendKeys("user2");
            password.sendKeys("invalidPassword");
            loginButton.click();

            WebElement errorMessage = driver.findElement(By.id("errorMsg"));
            if (errorMessage.isDisplayed()) {
                System.out.println("Test Passed: Error message displayed for invalid password.");
            } else {
                System.out.println("Test Failed: Error message not displayed.");
            }
        } catch (Exception e) {
            System.out.println("Test Failed: Exception occurred - " + e.getMessage());
        }
    }

    // Test Case 5: Verify login with blank username and valid password
    public void testBlankUsername() {
        System.out.println("Test Case 5: Verify login with blank username");
        try {
            WebElement password = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("kc-login"));

            password.sendKeys("welcome2KADA");
            loginButton.click();

            WebElement usernameError = driver.findElement(By.id("usernameError"));
            if (usernameError.isDisplayed()) {
                System.out.println("Test Passed: Validation error for blank username.");
            } else {
                System.out.println("Test Failed: Validation error not shown.");
            }
        } catch (Exception e) {
            System.out.println("Test Failed: Exception occurred - " + e.getMessage());
        }
    }

    // Test Case 6: Verify login with valid username and blank password
    public void testBlankPassword() {
        System.out.println("Test Case 6: Verify login with valid username and blank password");
        try {
            WebElement username = driver.findElement(By.id("username"));
            WebElement loginButton = driver.findElement(By.id("kc-login"));

            username.sendKeys("user2");
            loginButton.click();

            WebElement passwordError = driver.findElement(By.id("passwordError"));
            if (passwordError.isDisplayed()) {
                System.out.println("Test Passed: Validation error for blank password.");
            } else {
                System.out.println("Test Failed: Validation error not shown.");
            }
        } catch (Exception e) {
            System.out.println("Test Failed: Exception occurred - " + e.getMessage());
        }
    }

    // Test Case 7: Verify login with both username and password blank
    public void testBlankUsernameAndPassword() {
        System.out.println("Test Case 7: Verify login with both username and password blank");
        try {
            WebElement loginButton = driver.findElement(By.id("kc-login"));
            loginButton.click();

            WebElement usernameError = driver.findElement(By.id("usernameError"));
            WebElement passwordError = driver.findElement(By.id("passwordError"));

            if (usernameError.isDisplayed() && passwordError.isDisplayed()) {
                System.out.println("Test Passed: Validation errors for both fields.");
            } else {
                System.out.println("Test Failed: Validation errors not shown.");
            }
        } catch (Exception e) {
            System.out.println("Test Failed: Exception occurred - " + e.getMessage());
        }
    }

    // Test Case 8: Verify that the password field is masked
    public void testPasswordFieldMasked() {
        System.out.println("Test Case 8: Verify that the password field is masked");
        try {
            WebElement password = driver.findElement(By.id("password"));
            if (password.getAttribute("type").equals("password")) {
                System.out.println("Test Passed: Password field is masked.");
            } else {
                System.out.println("Test Failed: Password field is not masked.");
            }
        } catch (Exception e) {
            System.out.println("Test Failed: Exception occurred - " + e.getMessage());
        }
    }

    // Test Case 9: Verify login with password exceeding character limit
    public void testPasswordExceedsLimit() {
        System.out.println("Test Case 9: Verify login with password exceeding character limit");
        try {
            WebElement username = driver.findElement(By.id("username"));
            WebElement password = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("kc-login"));

            username.sendKeys("user2");
            password.sendKeys("verylongpasswordthatexceedsthecharacterlimit");
            loginButton.click();

            WebElement errorMessage = driver.findElement(By.id("passwordError"));
            if (errorMessage.isDisplayed()) {
                System.out.println("Test Passed: Error message displayed for long password.");
            } else {
                System.out.println("Test Failed: No error message for long password.");
            }
        } catch (Exception e) {
            System.out.println("Test Failed: Exception occurred - " + e.getMessage());
        }
    }

    // Test Case 10: Verify system behavior after multiple failed login attempts
    public void testMultipleFailedLogins() {
        System.out.println("Test Case 10: Verify behavior after multiple failed login attempts");
        try {
            WebElement username = driver.findElement(By.id("username"));
            WebElement password = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("kc-login"));

            for (int i = 0; i < 5; i++) {
                username.sendKeys("user2");
                password.sendKeys("invalidPassword");
                loginButton.click();
            }

            WebElement captchaOrLock = driver.findElement(By.id("captchaOrLock"));
            if (captchaOrLock.isDisplayed()) {
                System.out.println("Test Passed: CAPTCHA or lock triggered.");
            } else {
                System.out.println("Test Failed: No CAPTCHA or lock after multiple failed attempts.");
            }
        } catch (Exception e) {
            System.out.println("Test Failed: Exception occurred - " + e.getMessage());
        }
    }
    // Close the WebDriver
    public void tearDown() {
        driver.quit();
    }
    // Main method to run all test cases
    public static void main(String[] args) {
        // Instantiate the LoginTest class
        LoginTest loginTest = new LoginTest();

        // Set up the WebDriver
        loginTest.setUp();

        // Run all the test cases
        loginTest.testLoginPageLoads();
        loginTest.testValidLogin();
        loginTest.testInvalidUsername();
        loginTest.testInvalidPassword();
        loginTest.testBlankUsername();
        loginTest.testBlankPassword();
        loginTest.testBlankUsernameAndPassword();
        loginTest.testPasswordFieldMasked();
        loginTest.testPasswordExceedsLimit();
        loginTest.testMultipleFailedLogins();

        // Close the WebDriver
        loginTest.tearDown();
    }
}