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
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




public class Bio2RDF {

	public static void main(String[] args) throws  IOException {
		File inputFile = new File(args[0]); //Ausgangsfile merge
		File outputFile = new File(args[1]); //Outputfile für gefilterte Ergebnisse
		File outputFile2 = new File(args[2]); //Outputfile für Ausschuss
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
		
		System.out.println("File read in completed, execution started at "+LocalDateTime.now());
		try {
			reader = new BufferedReader(new FileReader(inputFile));
			while(reader.ready()) {
				i++;
				String line = reader.readLine();
				Pattern pattern = Pattern.compile(".+ \"GET\\s+\\/sparql\\/\\??query=(.*)\\s?HTTP"); //Filters everything from Select to describe
				Pattern pattern0= Pattern.compile(".+ \"GET\\s+\\/sparql\\?query=(.*)"); //ASK query is not One-lined
				Pattern pattern2 = Pattern.compile(".*HTTP"); //Cleanup for ASK queries that end with that HTTP protocol stuff
				Matcher matcher = pattern.matcher(line);
				Matcher matcher0 = pattern0.matcher(line);
				Matcher matcher2 = pattern2.matcher(line);
				//Filter out left over coding bits and just extract the query form (select||describe||ask)
				if (matcher.find()) {
					String query = "";
					try {
						query = matcher.group(1);
					} catch (IllegalStateException e) { //catch exception for some illegal program cancels
						continue;
					}
					//write to output file
					writer.write(query);
					writer.newLine();
					}
				//special case for ASK as the left over coding bits vary from the other
				else if (matcher0.find()){
					String query = "";
					try {
						query = matcher0.group(1);
					} catch (IllegalStateException e) {
						continue;
					}
					
					writer.write(query);
					writer.newLine();
				}
						//extract the http tails from ASK queries
						else if (matcher2.find()) { 
							writer2.write(line);
							writer2.newLine();							 
							 }
						else {//rest is normally written into output file
							writer.write(line);
							writer.newLine();
						}
				if(i%((long)linecount/10)==0) {
					System.out.println(((int)(i/linecount*100)) + "% done at "+ (LocalDateTime.now()));
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
		System.out.println("Done collecting!"+ " this took " + duration+ " seconds.");
		
	}
	

		
	
	}