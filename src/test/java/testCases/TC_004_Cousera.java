package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_004_Cousera extends BaseClass {
    
    @Test(groups = {"smoke"})
    public void openCousera() {
        try {
            // Creating an instance of HomePage
            HomePage hp = new HomePage(driver);
            
            // Expected title of the Coursera homepage
            String expectedTitle = "Coursera | Degrees, Certificates, & Free Online Courses";
            
            // Getting the actual title of the current page
            String actualTitle = hp.title();
            System.out.print(actualTitle);
            
            // Asserting that the actual title matches the expected title
            Assert.assertEquals(expectedTitle, actualTitle);
            
        } catch (Exception e) {
            // Failing the test case if an exception occurs
            Assert.fail("Failed to load Coursera " + e.getMessage());
        }
    }
}
