package tutorial;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class linecounter {
	public static void main(String[] args) throws IOException {
//	File inputFile = new File("D:/Usewod/Completed Usewod Counting/lgd/merge.txt");
//	File inputFile = new File("D:/Usewod/Completed Usewod Counting/DBP/3-3/merge.txt");
//	File inputFile = new File("D:/Usewod/Completed Usewod Counting/DBP/3-4/merge.txt");
//	File inputFile = new File("D:/Usewod/Completed Usewod Counting/DBP/3-5-1/merge.txt");
//	File inputFile = new File("D:/Usewod/Completed Usewod Counting/DBP/3-6/merge.txt");
//	File inputFile = new File("D:/Usewod/Completed Usewod Counting/DBP/3-8/merge.txt");
//	File inputFile = new File("D:/Usewod/Completed Usewod Counting/Usewod bio2rdf/merge.txt");
//	File inputFile = new File("D:/Usewod/Completed Usewod Counting/Usewod swdf/merge.txt");
//	File inputFile = new File("D:/Usewod/Completed Usewod Counting/Usewod Openbiomed/merge.txt");
	File inputFile = new File("D:/Usewod/Completed Usewod Counting/Usewod swdf/decoded.txt");
	LineNumberReader  lnr = new LineNumberReader(new FileReader(inputFile));
	lnr.skip(Long.MAX_VALUE);
	long linecount = lnr.getLineNumber() + 1; //Add 1 because line index starts at 0
	// Finally, the LineNumberReader object should be closed to prevent resource leak
	lnr.close();
	BufferedReader reader = null;
	long starttime = System.nanoTime();
	double i = 0;
	int z = 0;
	int y = 0;
	int w = 0;
	int x = 0;
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
	try{
		reader = new BufferedReader(new FileReader(inputFile));
		while(reader.ready()) {
			i++;
			String line = reader.readLine();
			Pattern pattern = Pattern.compile("^.*(select|SELECT|Select).*$");
			Pattern pattern1 = Pattern.compile("^.*(ask|ASK|Ask).*$");
			Pattern pattern2 = Pattern.compile("^.*(construct|CONSTRUCT|Construct).*$");
			Pattern pattern3 = Pattern.compile("^.*(describe|DESCRIBE|Describe).*$");
			Matcher matcher = pattern.matcher(line);
			Matcher matcher1 = pattern1.matcher(line);
			Matcher matcher2 = pattern2.matcher(line);
			Matcher matcher3 = pattern3.matcher(line);
			
			if (matcher.find()) {
				
				z++;
				
			}
			if (matcher1.find()) {
				
				y++;
				
			}
			if (matcher2.find()) {
	
				x++;
	
			}
			if (matcher3.find()) {
	
				w++;
	
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
		}System.out.println("Number of Lines in File : "+linecount+ "\n Number of Select : " +z+"\n Number of ASK: "+y+ "\n Number of Construct : " +x+"\n Number of Describe : "+w);
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
	