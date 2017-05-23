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
import java.net.URL;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




public class Main {

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
		try {
			reader = new BufferedReader(new FileReader(inputFile));
			while(reader.ready()) {
				String line = reader.readLine();
//				Pattern pattern = Pattern.compile(".+\"GET\\s+\\/sparql/?\\?query=(\\S+)\\s?HTTP.+");
//				Pattern pattern1= Pattern.compile(".+\"GET\\s+\\/sparql\\?default-graph-uri=&query=(\\S+)\\s?HTTP.+");
				Pattern pattern2 = Pattern.compile(".+\"GET\\s+(\\S+)\\s?HTTP.+");
				//TODO Pattern1 scheint richtig zu sein, schmeißt aber :
				//"GET /sparql?default-graph-uri=&query=SELECT
				//"GET /sparql?default-graph-uri=&query=select
				//.+\"GET\\s+\\/sparql\?default-graph-uri=&query=(\\S+)\\s?HTTP.+
//				Matcher matcher = pattern.matcher(line);
//				Matcher matcher1 = pattern1.matcher(line);
				Matcher matcher2 = pattern2.matcher(line);
//				if (matcher.find()||matcher1.find()) {
//					String query = "";
//					try {
//						query = matcher.group(1);
//					} catch (IllegalStateException e) {
//						System.out.println(matcher.groupCount());
//						System.out.println(line);
//						continue;
//					}
//					query = URLDecoder.decode(query, "UTF-8");
//					writer.write(query);
//					writer.newLine();
//									}
//						else  { 
//							writer2.write(line);
//							writer2.newLine();							 
//							 }
				if (matcher2.find()) {
				URL url = new URL("http://myhost.com"+matcher2.group(1));
				String query = url.getQuery();
				if(query==null) {System.out.println(matcher2.group(1));continue;}
			    String[] pairs = query.split("&");
			    for (String pair : pairs) {
			        int idx = pair.indexOf("=");
			        System.out.println(URLDecoder.decode(pair.substring(0, idx), "UTF-8") + " = " + URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
			    }
			    
				}if(i%((long)linecount/10)==0) {
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

