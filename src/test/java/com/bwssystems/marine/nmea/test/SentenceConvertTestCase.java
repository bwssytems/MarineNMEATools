package com.bwssystems.marine.nmea.test;

import org.junit.Assert;
import org.junit.Test;

import com.bwssystems.marine.nmea.SentenceConvert;

public class SentenceConvertTestCase {


	@Test
    public void testReplacePositive() {
        String testSentence = "$APHDM,182.00,M";
        String targetSentence = "$HCHDM,182.00,M";
                    
        targetSentence = SentenceConvert.appendChecksum(targetSentence);
        
        String assertSentence = SentenceConvert.doReplace(testSentence, "$AP", "$HC");
        Assert.assertEquals(assertSentence, targetSentence); 
    }
    
	@Test
    public void testReplacePositive2() {
        String testSentence = "$APGLL,4153.08462,N,08736.70262,W,155702.00,A,D*7A";
        String targetSentence = "$GPGLL,4153.08462,N,08736.70262,W,155702.00,A,D*7A";
                    
        String assertSentence = SentenceConvert.doReplace(testSentence, "$AP", "$GP");
        Assert.assertEquals(assertSentence, targetSentence); 
    }
    
    @Test
    public void testReplaceNoChange() {
        String testSentence = "$GPVTG,,T,,M,0.040,N,0.075,K,D*20";
        String assertSentence = SentenceConvert.doReplace(testSentence, "$AP", "$HC");
        Assert.assertEquals(assertSentence, testSentence);
    }
    
    @Test
    public void testReplaceCheckSum() {
        String testSentence = "$GPVTG,,T,,M,0.040,N,0.075,K,D";
        String targetSentence = "$GPVTG,,T,,M,0.040,N,0.075,K,D*20";
        String assertSentence = SentenceConvert.doReplace(testSentence, "$AP", "$HC");
        Assert.assertEquals(assertSentence, targetSentence);
    }
}
