/**
 * epclaim.sapa
 * 
 */
package epclaim.sapa;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import JSHOP2Wrapper.JShop2Planner;
import epclaim.centralsystem.CentralSystem;
import epclaim.compiler.Action;
import epclaim.compiler.Agent;
import epclaim.compiler.grammar.ParseException;
import epclaim.planner.PLACEPlan;
import epclaim.planner.PlanNotFoundException;
import epclaim.utils.FileUtility;

/**
 * @author Usman
 *
 */
public class SapaTranslator {
	private Agent agent;
	private String domainFile;
	private String problemFile;
	private String domainStr = "";
	private String problemStr = "";
	
	public SapaTranslator(Agent agent) {
		super();
		this.agent = agent;
		String folder = System.getProperty("user.dir")+"\\JSHOP2PlannerWorkingDirectory\\"+agent.getAgentName()+"\\Sapa\\";
		FileUtility.createDire(folder);
		this.domainFile = folder+"domain.pddl";
		this.problemFile = folder+"problem.pddl";
		
	}
	public void translate(){
		this.translateDomain();
		this.write();
	}
	public void translateDomain(){
		String str = "( (domain "+this.agent.getAgentName()+")\n(:requirements :typing :durative-actions)\n";
		
		for(Action action: this.agent.getActionCollection().getActions())
		str+=this.translateAction(action);
		this.domainStr = str+"\n)";
	}
	private String translateAction(Action action){
		String str="(:durative-action "+action.getName();
		str+="\n:parameters (";
		for(String var:action.getMessage().getVariables())
			str+=var+" ";
		str+=")\n";
		str+=":condition("+action.getCondition()+")\n";
		str+=":duration (= ?duration "+action.getDuration()+")\n";
		
		str+="\n)\n";
		return str+")\n";
	}
    private void write(){
    	Writer domainWriter;
    	Writer problemWriter;
		try {
			domainWriter = new BufferedWriter(new OutputStreamWriter(
			          new FileOutputStream(this.domainFile), "utf-8"));
			domainWriter.write(this.domainStr);
			domainWriter.close();
			
			problemWriter = new BufferedWriter(new OutputStreamWriter(
			          new FileOutputStream(this.problemFile), "utf-8"));
			problemWriter.write(this.problemStr);
			problemWriter.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CentralSystem cs = new CentralSystem(new JShop2Planner());
		//String fileName = "F:\\epclaimcodes\\shop2\\casestudyHospital\\hospital.ep";
		String fileName = "F:\\epclaimcodes\\shop2\\usman\\cleaner.txt";
		try {
			cs.compile(fileName); 
			for (Agent agent : cs.getPlaceObjects().getAgentsCollection().getAgentsList()) {
				agent.setEnvironment(cs.getPlaceObjects().getEnvironment());
				SapaTranslator translator = new SapaTranslator(agent);
				translator.translate();
				//PLACEPlan plan =  cs.getPlanner().getPlan(agent);
				
				if(false) throw new PlanNotFoundException();
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

}
