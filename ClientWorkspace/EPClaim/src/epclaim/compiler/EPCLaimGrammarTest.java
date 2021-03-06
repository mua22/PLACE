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
			String folder = "F:\\epclaimcodes\\shop2\\casestudyHospital\\";
			stream = new FileReader(folder+"hospital.ep");
			PlaceGrammar parser = new PlaceGrammar(stream);
			System.out.println("Starting Compiling ...");
			PlaceObjectCollection data = parser.compile();
			//System.out.println(data+ "\nEnding ...");
			//System.out.println(data.getEnvironment().getGlobalKnowledgeCollection());
			for (Agent agent : data.getAgentsCollection().getAgentsList()) {
				agent.setEnvironment(data.getEnvironment());
				//System.out.println(agent.getKnowledgeCollection());
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
	public Environment buildEnvironment(){
		Environment env = new Environment("Travelling");
		Artifact a1 = new Artifact("a1");
		
		return env;
		
	}

}
