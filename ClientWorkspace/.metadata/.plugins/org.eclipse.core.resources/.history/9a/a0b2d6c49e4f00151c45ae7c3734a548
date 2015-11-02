package epclaim.compiler;

import java.io.FileNotFoundException;
import java.io.FileReader;

import JSHOP2Wrapper.JSHOP2Translator;
import epclaim.compiler.grammar.*;
//import epclaim.compiler.grammar.ParseException;

public class EPCLaimGrammarTest {

	public static void main(String[] args) {
		FileReader stream;
		try {
			String folder = "F:\\epclaimcodes\\shop2\\adnancasestudy\\";
			stream = new FileReader(folder+"agent.ep");
			PlaceGrammar parser = new PlaceGrammar(stream);
			System.out.println("Starting Compiling ...");
			PlaceObjectCollection data = parser.compile();
			//System.out.println(data+ "\nEnding ...");
			
			for (Agent agent : data.getAgentsCollection().getAgentsList()) {
				JSHOP2Translator translator = new JSHOP2Translator(agent,folder);
				translator.translate();
				System.out.println(agent.getName()+" Translated...");
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

	}

}
