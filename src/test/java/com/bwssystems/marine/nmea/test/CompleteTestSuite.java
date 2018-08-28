package com.bwssystems.marine.nmea.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestSuite1.class})
public class CompleteTestSuite {

    @BeforeClass 
    public static void setUpClass() {      
        System.out.println("Master setup");

    }

    @AfterClass public static void tearDownClass() { 
        System.out.println("Master tearDown");
    }
    

}