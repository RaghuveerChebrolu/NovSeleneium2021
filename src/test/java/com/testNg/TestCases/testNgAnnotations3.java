package com.testNg.TestCases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class testNgAnnotations3 {
  @Test(priority=-2)
  public void testCase1() {
	  System.out.println("inside testNgAnnotations3 testCase1");
  }
  
  @Test(priority=3,invocationCount=4)
  public void TestCase2() {
	  System.out.println("inside testNgAnnotations3 testCase2");
  }
  
  @Test(priority=0)
  public void testCase6() {
	  System.out.println("inside testNgAnnotations3 testCase6");
  }
  
  @Test(priority=-5)
  public void testCase4() {
	  System.out.println("inside testNgAnnotations3 testCase4");
  }
  
  @Test
  public void testCase5() {
	  System.out.println("inside testNgAnnotations3 testCase5");
  }
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("inside testNgAnnotations3 beforeMethod");
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("inside testNgAnnotations3 afterMethod");
  }

  @BeforeClass
  public void beforeClass() {
	  System.out.println("inside testNgAnnotations3 beforeClass");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("inside testNgAnnotations3 afterClass");
  }

  @BeforeTest
  public void beforeTest() {
	  System.out.println("inside testNgAnnotations3 beforeTest");
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("inside testNgAnnotations3 afterTest");
  }

  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("inside testNgAnnotations3 beforeSuite");
  }

  @AfterSuite
  public void afterSuite() {
	  System.out.println("inside testNgAnnotations3 afterSuite");
  }

}
