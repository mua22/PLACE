package epclaim.centralsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import JSHOP2Wrapper.JSHOP2Translator;
import JSHOP2Wrapper.JShop2Planner;
import epclaim.compiler.Agent;
import epclaim.compiler.Environment;
import epclaim.compiler.PlaceObjectCollection;
import epclaim.compiler.grammar.ParseException;
import epclaim.compiler.grammar.PlaceGrammar;
import epclaim.planner.IPlanner;
import epclaim.planner.PlanNotFoundException;

public class CentralSystem {
	private Environment env;
	private IPlanner planner;
	private PlaceObjectCollection placeObjects;
	private PlaceGrammar parser;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CentralSystem cs = new CentralSystem(new JShop2Planner());
		//String fileName = "F:\\epclaimcodes\\shop2\\casestudyHospital\\hospital.ep";
		String fileName = "F:\\epclaimcodes\\shop2\\usman\\cleaner.txt";
		try {
			cs.compile(fileName); 
			for (Agent agent : cs.getPlaceObjects().getAgentsCollection().getAgentsList()) {
				agent.setEnvironment(cs.getPlaceObjects().getEnvironment());
				cs.getPlanner().getPlan(agent);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PlanNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public CentralSystem(IPlanner planner){
		this.planner = planner;
		placeObjects = new PlaceObjectCollection();
	}
	public void compile(String fileName) throws FileNotFoundException, ParseException{
		System.out.println("Starting Compiling ...");
		FileReader stream = new FileReader(new File(fileName));
		//PLACEGrammar is a self generated class by JavaCC
		parser = new PlaceGrammar(stream); 
		placeObjects = parser.compile();
		System.out.println("Finished Compiling ...");
	}

	public PlaceObjectCollection getPlaceObjects() {
		return placeObjects;
	}

	public IPlanner getPlanner() {
		return planner;
	}
	
}
