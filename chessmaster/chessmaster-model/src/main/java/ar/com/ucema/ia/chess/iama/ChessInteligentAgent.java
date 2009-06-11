package ar.com.ucema.ia.chess.iama;import java.util.List;import java.util.Properties;import aima.search.framework.GraphSearch;import aima.search.framework.Problem;import aima.search.framework.Search;import aima.search.framework.SearchAgent;import aima.search.informed.AStarSearch;import ar.com.ucema.ia.chess.model.ChessGame;public class ChessInteligentAgent extends SearchAgent {	public ChessInteligentAgent(Problem arg0, Search arg1) throws Exception {		super(arg0, arg1);	}	public void play() {		try {			Problem problem = new Problem(ChessGame.getInstance().getBoard(), new ChessSuccessorFunction(), new ChessGoalTest(), new ChessHeuristicFunction()); 			Search search = new AStarSearch(new GraphSearch());			ChessInteligentAgent agent = new ChessInteligentAgent(problem, search);						 agent.printActions();			 agent.printInstrumentation();		} 		catch (Exception e) {			e.printStackTrace();		}	}	private void printInstrumentation() {		Properties list = this.getInstrumentation();		System.out.println(list);	}		@SuppressWarnings("unchecked")	private void printActions() {		List list = this.getActions();				for (Object object : list) {			System.out.println(object);		}			}	}