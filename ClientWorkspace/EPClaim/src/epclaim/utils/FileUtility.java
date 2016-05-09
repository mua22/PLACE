package epclaim.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileUtility {
	
	 
	/**
	 * Creates the directory if its not already presented
	 * @param directoryName
	 */
	public static void createDire(String directoryName){
		File theDir = new File(directoryName);

		// if the directory does not exist, create it
		if (!theDir.exists()) {
		    System.out.println("creating directory: " + directoryName);
		    boolean result = false;

		    try{
		        theDir.mkdir();
		        result = true;
		    } 
		    catch(SecurityException se){
		        //handle it
		    }        
		    if(result) {    
		        System.out.println("DIR created");  
		    }
		}
	}
	public static String fileToString(File file){
		FileInputStream fis = null;
        String str = "";

        try {
            fis = new FileInputStream(file);
            int content;
            while ((content = fis.read()) != -1) {
                // convert to char and display it
                str += (char) content;
            }

            //System.out.println("After reading file");
            //System.out.println(str);
            return str;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null)
                    fis.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
		return str;
	}
}
