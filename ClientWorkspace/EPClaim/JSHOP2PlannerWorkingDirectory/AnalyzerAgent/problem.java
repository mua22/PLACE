import java.util.LinkedList;
import JSHOP2.*;

public class problem
{
	private static String[] defineConstants()
	{
		String[] problemConstants = new String[7];

		problemConstants[0] = "r2";
		problemConstants[1] = "tv_placed";
		problemConstants[2] = "off";
		problemConstants[3] = "tv_status";
		problemConstants[4] = "r1";
		problemConstants[5] = "connected";
		problemConstants[6] = "b5";

		return problemConstants;
	}

	private static void createState0(State s)	{
		s.add(new Predicate(0, 0, new TermList(TermConstant.getConstant(1), TermList.NIL)));
	}

	public static LinkedList<Plan> getPlans()
	{
		LinkedList<Plan> returnedPlans = new LinkedList<Plan>();
		TermConstant.initialize(8);

		Domain d = new analyzeragent();

		d.setProblemConstants(defineConstants());

		State s = new State(1, d.getAxioms());

		JSHOP2.initialize(d, s);

		TaskList tl;
		SolverThread thread;

		createState0(s);

		tl = new TaskList(1, true);
		tl.subtasks[0] = new TaskList(1, true);
		tl.subtasks[0].subtasks[0] = new TaskList(new TaskAtom(new Predicate(1, 0, new TermList(TermConstant.getConstant(7), TermList.NIL)), false, false));

		thread = new SolverThread(tl, 1);
		thread.start();

		try {
			while (thread.isAlive())
				Thread.sleep(500);
		} catch (InterruptedException e) {
		}

		returnedPlans.addAll( thread.getPlans() );

		return returnedPlans;
	}

	public static LinkedList<Predicate> getFirstPlanOps() {
		return getPlans().getFirst().getOps();
	}
}