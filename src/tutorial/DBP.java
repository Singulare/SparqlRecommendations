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
import java.util.regex.Matcher;
import java.util.regex.Pattern;




public class DBP {

	public static void main(String[] args) throws IOException {
		System.out.println("Program startet at "+LocalDateTime.now());
		File inputFile = new File("D:/Usewod/Completed Usewod Counting/DBP/3-3/merge.txt");
		File outputFile = new File("D:/Usewod/Completed Usewod Counting/DBP/3-3/clean.txt");
		File outputFile2 = new File("D:/Usewod/Completed Usewod Counting/DBP/3-3/rejects.txt");
		FileOutputStream fos = new FileOutputStream(outputFile);
		FileOutputStream fos2 = new FileOutputStream(outputFile2);
		BufferedReader reader = null;
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
		BufferedWriter writer2 = new BufferedWriter(new OutputStreamWriter(fos2));
		long starttime = System.nanoTime();
		LineNumberReader  lnr = new LineNumberReader(new FileReader(inputFile));
		lnr.skip(Long.MAX_VALUE);
		double linecount = lnr.getLineNumber() + 1; //Add 1 because line index starts at 0
		// Finally, the LineNumberReader object should be closed to prevent resource leak
		lnr.close();
		double i = 0;
		boolean f = false;
		boolean g = false;
		boolean h = false;
		boolean j = false;
		boolean k = false;
		boolean l = false;
		boolean m = false;
		boolean n = false;
		boolean o = false;
		System.out.println("File read in completed, execution started at "+LocalDateTime.now());
		try {
			reader = new BufferedReader(new FileReader(inputFile));
			while(reader.ready()) {
				i++;
				String line = reader.readLine();
				Pattern pattern = Pattern.compile(".+\"GET\\s+\\/sparql/?\\?query=(\\S+)\\s?HTTP.+");
				Matcher matcher = pattern.matcher(line);
				if (matcher.find()) {
					String query = matcher.group(1);
					//TODO 
					//PREFIX lines noch rausschmeißen
					try {
						query = URLDecoder.decode(query, "UTF-8");
					} catch (java.lang.IllegalArgumentException e) {
						// do nothing
						continue;
					}
					writer.write(query);
					writer.newLine();
									}
						else  { 
							writer2.write(line);
							writer2.newLine();							 
							 }
				
				if((i/linecount)>=0.10f && !f == true){	
					System.out.println("10% done at "+ (LocalDateTime.now()));
					f = true;
				continue;
				}
				if((i/linecount)>=0.20f && !g ==true){
					System.out.println("20% done at "+ (LocalDateTime.now()));
					g = true;
					continue;
				}
				if((i/linecount)>=0.30f && !h ==true){
					System.out.println("30% done at "+ (LocalDateTime.now()));
					h = true;
					continue;
				}
				if((i/linecount)>=0.40f && !j ==true){
					System.out.println("40% done at "+ (LocalDateTime.now()));
					j = true;
					continue;
				}
				if((i/linecount)>=0.50f && !k ==true){
					System.out.println("50% done at "+ (LocalDateTime.now()));
					k = true;
					continue;
				}
				if((i/linecount)>=0.60f && !l ==true){
					System.out.println("60% done at "+ (LocalDateTime.now()));
					l = true;
					continue;
				}
				if((i/linecount)>=0.70f && !m ==true){
					System.out.println("70% done at "+ (LocalDateTime.now()));
					m = true;
					continue;
				}
				if((i/linecount)>=0.80f && !n ==true){
					System.out.println("80% done at "+ (LocalDateTime.now()));
					n = true;
					continue;
				}
				if((i/linecount)>=0.90f && !o ==true){
					System.out.println("90% done at "+ (LocalDateTime.now()));
					o = true;
					continue;
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
					writer2.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		long endtime = System.nanoTime();
		long duration = ((endtime - starttime)/1000000000);
		System.out.println("Done collecting!"+ " this took " + duration+ " seconds and was finished at " + LocalDateTime.now());
		
	}

		
	
	}

