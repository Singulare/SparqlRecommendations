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




public class DBP2 {

	public static void main(String[] args) throws  IOException {
		File inputFile = new File(args[0]); //Ausgangsfile merge
		File outputFile = new File(args[1]); //Outputfile f�r gefilterte Ergebnisse
		File outputFile2 = new File(args[2]); //Outputfile for rejects
		
		FileOutputStream fos = new FileOutputStream(outputFile);
		FileOutputStream fos2 = new FileOutputStream(outputFile2);
		BufferedReader reader = null;
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
		BufferedWriter writer2 = new BufferedWriter (new OutputStreamWriter(fos2));
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
				Pattern pattern0= Pattern.compile(".*query=(.*)"); //allgemeiner Fall f�r codeschnipsel
				Pattern pattern1= Pattern.compile(".?0.0.0.0.*"); //Pattern f�r restliche 0.0.0.0 extraktion
				Pattern pattern2= Pattern.compile(".*>\\s(SELECT.*)"); //Prefixe vor Slect anfragen raussammeln
				Matcher matcher0 = pattern0.matcher(line);
				Matcher matcher1 = pattern1.matcher(line);
				Matcher matcher2 = pattern2.matcher(line);

				if (matcher0.find()) {
					String query = "";
					try{
					query = matcher0.group(1);
					}catch (IllegalStateException e) {
						continue;
					}
					writer.write(query);
					writer.newLine();
									}
				if (matcher2.find()) {
					String query = "";
					try{
					query = matcher2.group(1);
					}catch (IllegalStateException e) {
						continue;
					}
					writer.write(query);
					writer.newLine();
									}

				else if(matcher1.find()){
					
						writer2.write(line);
						writer2.newLine();
					
					}
				else{
					writer.write(line);
					writer.newLine();
					}
						
				
				if(i%((long)linecount/100)==0) {
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