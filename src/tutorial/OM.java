package tutorial;

import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




public class OM {

	public static void main(String[] args) throws IOException {
		final Runnable runnable = 
			     (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
		
		
		File inputFile = new File("D:/Usewod/Completed Usewod Counting/Usewod Openbiomed/merge.txt");
		File outputFile = new File("D:/Usewod/Completed Usewod Counting/Usewod Openbiomed/clean.txt");
		File outputFile2 = new File("D:/Usewod/Completed Usewod Counting/Usewod Openbiomed/rejects.txt");
		FileOutputStream fos = new FileOutputStream(outputFile);
		FileOutputStream fos2 = new FileOutputStream(outputFile2);
		BufferedReader reader = null;
		//BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
		PrintWriter cleanWriter = new PrintWriter(fos);
		PrintWriter rejectsWriter = new PrintWriter(fos2);
		//BufferedWriter writer2 = new BufferedWriter(new OutputStreamWriter(fos2));
		long starttime = System.nanoTime();
		LineNumberReader  lnr = new LineNumberReader(new FileReader(inputFile));
		lnr.skip(Long.MAX_VALUE);
		double linecount = lnr.getLineNumber() + 1; //Add 1 because line index starts at 0
		// Finally, the LineNumberReader object should be closed to prevent resource leak
		lnr.close();
		double i = 0;
		try {
			reader = new BufferedReader(new FileReader(inputFile));
			while(reader.ready()) {
				i++;
				String line = reader.readLine().trim();
				Pattern pattern = Pattern.compile("# timestamp");
				Pattern pattern1 = Pattern.compile("------------");
				Pattern pattern2 = Pattern.compile("^.*(select|SELECT).*$");
				Matcher matcher = pattern.matcher(line);
				Matcher matcher1 = pattern1.matcher(line);
				Matcher matcher2 = pattern2.matcher(line);
				//TODO
				//falsches Matching : PREFIX wird komplett übernommen anstatt nur die group
				//mit "PREFIX" als 3tes Pattern verliert man etliche SELECT queries die der
				//PREFIX zeile angeschlossen sind
				if (matcher.find()|matcher1.find()) {
					
//					writer2.write(line);
//					writer2.newLine();
					rejectsWriter.println(line);
									}
				else if (!matcher2.find())  {
//					String query = matcher2.group(1);
//					if (query==null || query.trim().equals("")) {
						rejectsWriter.println(line);
//					} else {
//						cleanWriter.println(query);
//					}
//					writer.write(query);
//					writer.newLine();
					} else{
//							writer.write(line);
//							writer.newLine();
							cleanWriter.println(line);
							 }
				
									
				
				if(i%((long)linecount/10)==0) {
					System.out.println(((int)i/linecount*100) + "% done at "+ (LocalDateTime.now()));
				}
										
				}
									
								  
				
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(reader!=null)
				try {
					reader.close();
//					writer.close();
//					writer2.close();
					rejectsWriter.close();
					cleanWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		long endtime = System.nanoTime();
		long duration = ((endtime - starttime)/1000000000);
		System.out.println("Done collecting!"+ " this took " + duration+ " seconds.");

		if (runnable != null) runnable.run();
		else System.out.println("Hell,no");
		
	}

	
	
	}

