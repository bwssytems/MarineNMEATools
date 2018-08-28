package com.bwssystems.marine.nmea;

public class SentenceConvert {

   public static String doReplace(String aSentence, String target, String replaceTarget) {
        String newSentence;
        if(aSentence.contains(target)) {
            if(aSentence.contains("*"))
                aSentence = aSentence.substring(0, aSentence.indexOf("*"));
            newSentence = aSentence.replace(target, replaceTarget);
            newSentence = appendChecksum(newSentence);
        }
        else {
            if(!aSentence.contains("*")) {
                newSentence = appendChecksum(aSentence);
            }
            else
                newSentence = aSentence;
        }

            
        return newSentence;
   }
   
   public static String appendChecksum(String theSentence) {
       String appendSentence;
       
       appendSentence = theSentence + String.format("*%02x", buildChecksum(theSentence)).toUpperCase();
              
       return appendSentence;
   }

    public static int buildChecksum(String theSentence) {
        int check = 0;
        byte [] dataSentence = theSentence.substring(1).getBytes();
        for(int i = 0; i < dataSentence.length; i++) {
             check = check ^ Byte.toUnsignedInt(dataSentence[i]);
        }
        
        return check;
    }
}
