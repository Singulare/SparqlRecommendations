package tutorial;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.OutputStreamWriter;
import java.net.URLDecoder;
import java.time.LocalDateTime;





public class Decoder {

	public static void main(String[] args) throws IOException {
//		File inputFile = new File("D:/Usewod/Completed Usewod Counting/lgd/merge.txt");
//		File outputFile = new File("D:/Usewod/Completed Usewod Counting/lgd/decoded.txt");		
		File inputFile = new File("D:/Usewod/Completed Usewod Counting/Usewod swdf/merge.txt");
		File outputFile = new File("D:/Usewod/Completed Usewod Counting/Usewod swdf/decoded.txt");
		FileOutputStream fos = new FileOutputStream(outputFile);		
		BufferedReader reader = null;
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));		
		long starttime = System.nanoTime();
		LineNumberReader  lnr = new LineNumberReader(new FileReader(inputFile));
		lnr.skip(Long.MAX_VALUE);
		double linecount = lnr.getLineNumber() + 1; //Add 1 because line index starts at 0
		// Finally, the LineNumberReader object should be closed to prevent resource leak
		lnr.close();
		double i = 0;
		System.out.println("File read in completed, execution started at "+LocalDateTime.now());
		try {
			reader = new BufferedReader(new FileReader(inputFile));
			while(reader.ready()) {
				i++;
				String line = reader.readLine();
				//catch exception für decoder Fehler
				try { 
					//decodieren der URLs
					line = URLDecoder.decode(line, "UTF-8");
				} catch (java.lang.IllegalArgumentException e) {
					
					continue;
				}
					writer.write(line);
					writer.newLine();
									
				//Prozentanzeige		
					if(i%((long)linecount/10)==0) {
						System.out.println(((int)i/linecount*100) + "% done at "+ (LocalDateTime.now()));
					}
			}				  
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(reader!=null)
				try {
					reader.close();
					writer.close();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		long endtime = System.nanoTime();
		long duration = ((endtime - starttime)/1000000000);
		System.out.println("Done collecting!"+ " this took " + duration+ " seconds.");
		
	}

		
	
	}

