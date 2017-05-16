package tutorial;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;





public class InputReader {
	public static void main(String[] args) throws FileNotFoundException, Exception {
		File inputFile = new File(args[0]);			
		int endline =Integer.parseInt(args[1]);
		long i = 0;
		BufferedReader reader = null;
			long starttime = System.nanoTime();
		try {
			reader = new BufferedReader(new FileReader(inputFile));
			while(reader.ready()) {
				
				String line = reader.readLine();
//				Pattern pattern2 = Pattern.compile(".*(construct|CONSTRUCT|Construct).*");
//				Matcher matcher2 = pattern2.matcher(line);
//				if (matcher2.find()){
				System.out.println(line);
//				}
				if (++i==endline) break;
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
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		long endtime = System.nanoTime();
		long duration = ((endtime - starttime)/1000000000);
		System.out.println("Done reading!"+ " this took " + duration+ " seconds.");
		
	
	}
	
}
		
	
	
