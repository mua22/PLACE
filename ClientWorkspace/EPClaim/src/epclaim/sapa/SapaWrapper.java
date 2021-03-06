/**
 * epclaim.sapa
 * 
 */
package epclaim.sapa;

import java.io.*;

import sapa.planStringConverter.PlanStringConverter;
import edu.asu.sapa.Planner;
import edu.asu.sapa.parsing.Domain;
import edu.asu.sapa.parsing.PDDL21Parser;
import edu.asu.sapa.parsing.ParseException;
import edu.asu.sapa.parsing.Problem;
import epclaim.planner.PLACEPlan;


/**
 * @author Usman
 *
 */
public class SapaWrapper {

	/**
	 * @param args
	 */
	private String domainFile;
	private String problemFile;
	
	public SapaWrapper(String domainFile, String problemFile) {
		super();
		this.domainFile = domainFile;
		this.problemFile = problemFile;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SapaWrapper sw = new SapaWrapper("F:\\sapa\\examples\\depots\\DepotsSimpleTime.pddl", "F:\\sapa\\examples\\depots\\pfile1.pddl");
		//SapaWrapper sw = new SapaWrapper("F:\\sapa\\examples\\gripper\\domain.pddl", "F:\\sapa\\examples\\gripper\\prolem.pddl");
		sw.getPlan();
	}
	public void getPlan(){
		PDDL21Parser parser21 = new PDDL21Parser(System.in);
		Planner sapa = new Planner();
		Domain domain;// = new Domain();
	    Problem prob;// = new Problem();
	    println(this.domainFile);
	    println(this.problemFile);
		FileInputStream pddl_file;
		try {
			pddl_file = new FileInputStream(this.domainFile);
			PDDL21Parser.ReInit(pddl_file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			domain = PDDL21Parser.parse_domain_pddl();
			System.out.println(";;Domain file succesfully read !!" + " num actions = " + domain.numAction());
	    } catch(ParseException e) {
			System.out.println("Exception while parsing domain file!!");
			e.printStackTrace();
			return;
	    }

	    /**** Parse the problem file ****/
	    try {
			pddl_file = new java.io.FileInputStream(this.problemFile);
			PDDL21Parser.ReInit(pddl_file);
	    } catch(java.io.FileNotFoundException e) {
			System.out.println("Problem file " + this.problemFile + " not found !!!");
			return;
	    }
	    try {
			prob = PDDL21Parser.parse_problem_pddl();
			System.out.println(";;Problem file succesfully read !!");
	    } catch(ParseException e) {
			System.out.println("Exception while parsing problem file!!");
			e.printStackTrace();
			return;
	    }
	    sapa.solve(domain, prob);
	    byte[] bytes = sapa.getOrigPlan().getBytes();
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        InputStreamReader isr = new InputStreamReader(bais);
    PlanStringConverter parser = new PlanStringConverter(isr);
    PLACEPlan plan;
	try {
		plan = parser.getPlan();
		println("\n\n\n"+plan);
	} catch (sapa.planStringConverter.ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	    
	}
	
	private void println(String str){
		System.out.println(str);
	}
}
