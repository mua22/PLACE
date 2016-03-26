import java.util.LinkedList;
import JSHOP2.*;

public class problem
{
	private static String[] defineConstants()
	{
		String[] problemConstants = new String[2];

		problemConstants[0] = "r1";
		problemConstants[1] = "connected";

		return problemConstants;
	}

	private static void createState0(State s)	{
		s.add(new Predicate(2, 0, new TermList(TermConstant.getConstant(0), TermList.NIL)));
		s.add(new Predicate(4, 0, new TermList(TermConstant.getConstant(3), TermList.NIL)));
		s.add(new Predicate(1, 0, new TermList(TermConstant.getConstant(8), TermList.NIL)));
	}

	public static LinkedList<Plan> getPlans()
	{
		LinkedList<Plan> returnedPlans = new LinkedList<Plan>();
		TermConstant.initialize(10);

		Domain d = new usman();

		d.setProblemConstants(defineConstants());

		State s = new State(8, d.getAxioms());

		JSHOP2.initialize(d, s);

		TaskList tl;
		SolverThread thread;

		createState0(s);

		tl = new TaskList(1, true);
		tl.subtasks[0] = new TaskList(1, true);
		tl.subtasks[0].subtasks[0] = new TaskList(new TaskAtom(new Predicate(0, 0, new TermList(TermConstant.getConstant(0), TermList.NIL)), false, false));

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