package tutorial;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.sparql.algebra.Algebra;
import org.apache.jena.sparql.algebra.Op;
import org.apache.jena.sparql.algebra.OpVisitorBase;
import org.apache.jena.sparql.algebra.OpWalker;
import org.apache.jena.sparql.algebra.op.OpBGP;


public class Helloworld {

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		
		Model m = ModelFactory.createDefaultModel();
		String S = "http://example.com/test/" ;
		
		Resource r = m.createResource( S + "r");
		Property p = m.createProperty( S + "p");
		
		r.addProperty( p,  "hello world", XSDDatatype.XSDstring);
		m.write( System.out, "N-Triples");
		File inputFile = new File(args[0]);
		BufferedReader reader = null;
		BGPVisitor bgpVisitor = new BGPVisitor();
		try {
			reader = new BufferedReader(new FileReader(inputFile));
			while(reader.ready()) {
				String line = reader.readLine();
				Pattern pattern = Pattern.compile(".+\"GET\\s+\\/sparql/\\?query=(\\S+)\\s+HTTP.+");
				Matcher matcher = pattern.matcher(line);
				if (matcher.find()) {
					String query = matcher.group(1);
					query = URLDecoder.decode(query, "UTF-8");
					
					Query q = QueryFactory.create(query);
					Op operator = Algebra.compile(q);
					OpWalker walker = new OpWalker();
					walker.walk(operator, bgpVisitor);
					
				    System.out.println(q);
				    //System.out.println("###############################################");
				} else {
					continue;
				}
				//^.+"GET\s+\/sparql/\?query=(\S+)\s+HTTP.+$
//				System.out.println(line);
				//if (++i==1000) break;
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
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		System.out.println("Done collecting!");
		
		for(OpBGP bgp: bgpVisitor.getBGPs()) {
			System.out.println(bgp.toString());
		}
	}


}

class BGPVisitor extends OpVisitorBase {
	
	private Set<OpBGP> bgps;
	
	public BGPVisitor() {
		bgps = new HashSet<OpBGP>();
	}
	
	@Override
	public void visit(OpBGP opBGP) {
		// TODO Auto-generated method stub
		bgps.add(opBGP);
		super.visit(opBGP);
	}
	
	public Set<OpBGP> getBGPs() {
		return bgps;
	}
	
}