
package com.iodb.vod.selenium;

import com.iodb.vod.VodApplication;
import com.iodb.vod.selenium.support.SeleniumTest;
import com.iodb.vod.selenium.support.TestUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "server.port=9000", webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = VodApplication.class)
@SeleniumTest(driver = FirefoxDriver.class, baseUrl = "http://localhost:9000")
public class LoginPageControllerTest {
    
    @Autowired
    private WebDriver driver;
     
    @Before
    public void setUp() throws Exception {
       Assert.assertNotNull(driver);
    }
    
    @After
    public void tearDown() throws Exception {
        driver.findElement(By.cssSelector("input[type='submit'][value='Wyloguj']")).click();
        driver.get("http://localhost:9000");
    }

    @Test
    public void loginTest() {
        //main page
        driver.findElement(By.cssSelector("input[type='submit'][value='Serwis VOD']")).click();
        
        //login page
        driver.findElement(By.cssSelector("input[type='text'][name='username']")).sendKeys("test");
        driver.findElement(By.cssSelector("input[type='password'][name='password']")).sendKeys("pass");
        driver.findElement(By.cssSelector("input[type='submit'][value='Zaloguj']")).click();
        
        //user page
        Assert.assertEquals("http://localhost:9000/home", driver.getCurrentUrl());
        
        String banner = driver.findElement(By.xpath ("//h3[contains (text(),'Witaj')]")).getText();
        
        Assert.assertEquals("Witaj test!", banner);
    }
    
    @Test
    public void registerTest(){
        //main page
        driver.findElement(By.cssSelector("input[type='submit'][value='Serwis VOD']")).click();
        
        //login page
        driver.findElement(By.cssSelector("input[type='submit'][value='Zarejestruj sie']")).click();
        
        //register page
        
        //login exist test
        String dummyPass = TestUtils.testString();
        String dummyEmail = TestUtils.testString();
        
        driver.findElement(By.cssSelector("input[type='text'][name='login']")).sendKeys("test");
        driver.findElement(By.cssSelector("input[type='password'][name='password']")).sendKeys(dummyPass);
        driver.findElement(By.cssSelector("input[type='text'][name='email']")).sendKeys(dummyEmail);
        driver.findElement(By.cssSelector("input[type='submit'][value='Utwórz konto']")).click();
        
        String banner = driver.findElement(By.xpath ("//div[contains (text(),'Login lub email już jest zajęte.')]")).getText();
        Assert.assertEquals("Login lub email już jest zajęte.", banner);
        
        //login correct registration
        String dummyLogin = TestUtils.testString();
       
        driver.findElement(By.cssSelector("input[type='text'][name='login']")).sendKeys(dummyLogin);
        driver.findElement(By.cssSelector("input[type='password'][name='password']")).sendKeys(dummyPass);
        driver.findElement(By.cssSelector("input[type='text'][name='email']")).sendKeys(dummyEmail);
        driver.findElement(By.cssSelector("input[type='submit'][value='Utwórz konto']")).click();
        
        //loginPage
        driver.findElement(By.cssSelector("input[type='text'][name='username']")).sendKeys(dummyLogin);
        driver.findElement(By.cssSelector("input[type='password'][name='password']")).sendKeys(dummyPass);
        driver.findElement(By.cssSelector("input[type='submit'][value='Zaloguj']")).click();
        
        //user page
        Assert.assertEquals("http://localhost:9000/home", driver.getCurrentUrl());
        
        String banner2 = driver.findElement(By.xpath ("//h3[contains (text(),'Witaj')]")).getText();
        
        Assert.assertEquals("Witaj " + dummyLogin + "!", banner2);
    }
}