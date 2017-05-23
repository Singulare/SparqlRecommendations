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
	File inputFile = new File(args[0]); //Ausgangsfile merge
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
			if(i%((long)linecount/10)==0) {
				System.out.println(((int)(i/linecount*100)) + "% done at "+ (LocalDateTime.now()));
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
	