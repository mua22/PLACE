import java.util.LinkedList;
import JSHOP2.*;

public class problem
{
	private static String[] defineConstants()
	{
		String[] problemConstants = new String[3];

		problemConstants[0] = "r2";
		problemConstants[1] = "r1";
		problemConstants[2] = "connected";

		return problemConstants;
	}

	private static void createState0(State s)	{
		s.add(new Predicate(3, 0, new TermList(TermConstant.getConstant(9), TermList.NIL)));
		s.add(new Predicate(2, 0, new TermList(TermConstant.getConstant(1), TermList.NIL)));
		s.add(new Predicate(4, 0, new TermList(TermConstant.getConstant(10), TermList.NIL)));
		s.add(new Predicate(4, 0, new TermList(TermConstant.getConstant(9), TermList.NIL)));
		s.add(new Predicate(0, 0, new TermList(TermConstant.getConstant(9), TermList.NIL)));
	}

	public static LinkedList<Plan> getPlans()
	{
		LinkedList<Plan> returnedPlans = new LinkedList<Plan>();
		TermConstant.initialize(12);

		Domain d = new temporal();

		d.setProblemConstants(defineConstants());

		State s = new State(9, d.getAxioms());

		JSHOP2.initialize(d, s);

		TaskList tl;
		SolverThread thread;

		createState0(s);

		tl = new TaskList(1, true);
		tl.subtasks[0] = new TaskList(3, true);
		tl.subtasks[0].subtasks[0] = new TaskList(new TaskAtom(new Predicate(0, 0, new TermList(TermConstant.getConstant(9), TermList.NIL)), false, false));
		tl.subtasks[0].subtasks[1] = new TaskList(new TaskAtom(new Predicate(1, 0, new TermList(TermConstant.getConstant(9), TermList.NIL)), false, false));
		tl.subtasks[0].subtasks[2] = new TaskList(new TaskAtom(new Predicate(1, 0, new TermList(TermConstant.getConstant(10), TermList.NIL)), false, false));

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