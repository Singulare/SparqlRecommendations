package tutorial;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




public class SWDF {

	public static void main(String[] args) throws FileNotFoundException {
		File inputFile = new File("D:/Usewod/Completed Usewod Counting/Usewod swdf/merge.txt");
		File outputFile = new File("D:/Usewod/Completed Usewod Counting/Usewod swdf/clean.txt");
		File outputFile2 = new File("D:/Usewod/Completed Usewod Counting/Usewod swdf/rejects.txt");
		FileOutputStream fos = new FileOutputStream(outputFile);
		FileOutputStream fos2 = new FileOutputStream(outputFile2);
		BufferedReader reader = null;
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
		BufferedWriter writer2 = new BufferedWriter(new OutputStreamWriter(fos2));
		long starttime = System.nanoTime();
	
		try {
			reader = new BufferedReader(new FileReader(inputFile));
			while(reader.ready()) {
				String line = reader.readLine();
				Pattern pattern = Pattern.compile(".+\"GET\\s+\\/sparql/?\\?query=(\\S+)\\s?HTTP.+");
				Matcher matcher = pattern.matcher(line);
				if (matcher.find()) {
					String query = matcher.group(1);
					query = URLDecoder.decode(query, "UTF-8");
					writer.write(query);
					writer.newLine();
									}
						else  { 
							writer2.write(line);
							writer2.newLine();							 
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

