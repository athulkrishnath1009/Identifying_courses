package testCases;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.WebDevelopmentPage;
import testBase.BaseClass;
import utilities.ExcelUtil;

public class TC_001_SearchForWebDevelopmentCourses extends BaseClass {
    
    @Test(groups = {"regression"})
    public void SearchForWebDevelopmentCourses() throws InterruptedException, IOException {
        try {
            // Creating an instance of HomePage
            HomePage hp = new HomePage(driver);
            
            // Performing search operation
            hp.search(p.getProperty("searchElement1"));
            logger.info("Navigating to Home Page");
            
            // Creating an instance of WebDevelopmentPage
            WebDevelopmentPage wdp = new WebDevelopmentPage(driver);
            logger.info("Searching for 'Web Development' courses");
            
            // Generating a timestamp for the output file
            String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
            String filePath = System.getProperty("user.dir") + "\\OutputData\\Exceloutputfile_" + timeStamp + ".xlsx";
            
            // Defining headers and data array for Excel output
            String[] headers = {"Course Number", "Title", "Rating", "Length"};
            String[][] data = new String[2][4];
            
            // Checking if any product is visible
            if (!wdp.isVisibileProduct()) {
                logger.info("No visible product found, applying filters");
                
                // Applying filters
                wdp.tickEnglishCheckBox();
                logger.info("English checkbox ticked");
                wdp.tickBeginnersCheckBox();
                logger.info("Beginners checkbox ticked");
                
                // Looping through the first two courses and storing their details
                for (int i = 0; i < 2; i++) {
                    String title = wdp.getCourseTitle(i);
                    String rating = wdp.getCourseRating(i);    
                    String courseLength = wdp.getCourseLength(i);
                    
                    data[i][0] = String.valueOf(i + 1);
                    data[i][1] = title;
                    data[i][2] = rating;
                    data[i][3] = courseLength;
                    
                    System.out.println("Course " + (i + 1) + ": " + title + "\nRating: " + rating + "\n" + courseLength);
                    logger.info("Course {}: Title: {}, Rating: {}, Length: {}", i + 1, title, rating, courseLength);
                }
                
                // Taking a screenshot of the courses
                takeScreenshot("courses");
                logger.info("Screenshot taken for courses");
                
            } else {
                // If a product is visible, applying difficulty filter
                wdp.clickDifficulty();
                logger.info("Visible product found, applying difficulty filter");
                wdp.tickBeginnersCheckBox();
                logger.info("Beginners checkbox ticked");
                wdp.clickResult();
                logger.info("Clicked on result");
                
                // Looping through the first two courses and storing their details
                for (int i = 0; i < 2; i++) {
                    String title = wdp.getCourseTitle(i);
                    String rating = wdp.getCourseRating(i);    
                    String courseLength = wdp.getCourseLength(i);
                    
                    data[i][0] = String.valueOf(i + 1);
                    data[i][1] = title;
                    data[i][2] = rating;
                    data[i][3] = courseLength;
                    
                    System.out.println("Course " + (i + 1) + ": " + title + "\nRating: " + rating + "\n" + courseLength);
                    logger.info("Course {}: Title: {}, Rating: {}, Length: {}", i + 1, title, rating, courseLength);
                }
                
                // Taking a screenshot of the courses
                takeScreenshot("courses");
                logger.info("Screenshot taken for courses");
            }
            
            // Writing the course details to an Excel file
            ExcelUtil.writeToExcel(filePath, "Web Development Courses", headers, data);
            
            // Asserting that search results are visible
            Assert.assertTrue(wdp.isSearchResultsVisible(), "Search results are not visible");
            logger.info("Assertion passed: Search results are visible");
            logger.info("Test case: SearchForWebDevelopmentCourses completed");
            
        } catch (Exception e) {
            // Logging the error and failing the test case if an exception occurs
            logger.error("An error occurred during the test case execution: ", e);
            Assert.fail("Test case failed due to an exception: " + e.getMessage());
        }
    }
}
