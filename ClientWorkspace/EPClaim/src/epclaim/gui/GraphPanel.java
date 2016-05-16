package epclaim.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.mxgraph.layout.mxFastOrganicLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.layout.mxOrganicLayout;
import com.mxgraph.layout.mxParallelEdgeLayout;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.util.mxMorphing;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;

import epclaim.centralsystem.CentralSystem;
import epclaim.compiler.Agent;
import epclaim.compiler.Artifact;
import epclaim.compiler.PlaceObjectCollection;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.Timer;
import javax.swing.border.Border;
public class GraphPanel extends JPanel{

	/**
	 * Create the panel.
	 */
	CentralSystem centralSystem;
	mxIGraphLayout layout;
	private mxGraph graph;
	int delay = 2000; //milliseconds
	/**
	 * Jpanel to hold the graph
	 */
	private JPanel graphComponentPanel;
	public GraphPanel(CentralSystem cs) {
		this.centralSystem = cs;
		setLayout(new BorderLayout(0, 0));
		this.setLayout(new BorderLayout());
		graphComponentPanel = new JPanel();
		this.add(graphComponentPanel, BorderLayout.CENTER);
		

		ActionListener taskPerformer = new ActionListener() {
		  public void actionPerformed(ActionEvent evt) {
			  setMxGraphForAgents();
		    //myComponent.repaint();
		  }
		};

		new Timer(delay, taskPerformer).start();
	    
	}
	
	
	public void setMxGraphForAgents(){
		graph = new mxGraph();
this.applyEdgeDefaults();
		this.removeAll();
		this.repaint();
		final mxGraphComponent graphComponent = new mxGraphComponent(graph);
		
		//layout = new mxParallelEdgeLayout(graph);
		
		//layout.setForceConstant(100);
//	    		layout.execute(graph.getDefaultParent());
		graphComponentPanel = new JPanel();
		graphComponentPanel.setLayout(new BorderLayout());
		graphComponentPanel.add(graphComponent, BorderLayout.CENTER);
		this.add(graphComponentPanel,BorderLayout.CENTER);
		JLabel descriptionMessage = new JLabel("PLACE Environment");
		this.add(descriptionMessage, BorderLayout.SOUTH);
		//mxFastOrganicLayout layout = new mxFastOrganicLayout(graph);
		
		mxIGraphModel model = graph.getModel();
		Random rand = new Random();
		int i = rand.nextInt(10);
		/*for(int index = 0;index<i;index++)
			this.addVertex("C"+Integer.toString(index));*/
		updateGraphWithPlaceObjects();
		Object cell = graph.getDefaultParent();
		layout = new mxOrganicLayout(graph);
		graph.getModel().beginUpdate();
		try {
			layout.execute(cell);
		} finally {
			graph.getModel().endUpdate();
		}
		//this.repaint();
		this.getParent().repaint();
		
		//this.repaint();
	}


	/**
	 * 
	 */
	private void updateGraphWithPlaceObjects() {
		try {
			PlaceObjectCollection placeObjects = this.centralSystem.getPlaceObjects();
			for(Agent agent:placeObjects.getAgentsCollection().getAgentsList()){
				this.addVertex(agent.getAgentName());
			}
			for(Artifact artifact:placeObjects.getEnvironment().getArtifacts().values()){
				this.addVertex(artifact.getArtifactName());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void addTestData(){
		Object parent = graph.getDefaultParent();
		graph.getModel().beginUpdate();
		try
		{
		   Object v1 = graph.insertVertex(parent, null, "Hello", 20, 20, 80,
		         30);
		   Object v2 = graph.insertVertex(parent, null, "World!",
		         240, 150, 80, 30);
		   Object v3 = graph.insertVertex(parent, null, "World11!",
			         240, 150, 80, 30);
		   graph.insertEdge(parent, null, "", v1, v2);
		   graph.insertEdge(parent, null, "", v3, v2);
		}
		finally
		{
		   graph.getModel().endUpdate();
		}
		
	}
	public void addEdge(Object v1,Object v2){
		Object parent = graph.getDefaultParent();
		graph.getModel().beginUpdate();
		try
		{
		   
		   graph.insertEdge(parent, null, "", v1, v2);
		   
		}
		finally
		{
		   graph.getModel().endUpdate();
		}
		
	}
	public Object addVertex(String str){
		Object parent = graph.getDefaultParent();
		graph.getModel().beginUpdate();
		try
		{
		   return graph.insertVertex(parent, str, str, 20, 20, 80,
		         30);
		   
		}
		finally
		{
		   graph.getModel().endUpdate();
		}
		
	}
	private void applyEdgeDefaults() {
	    // Settings for edges
	    Map<String, Object> edge = new HashMap<String, Object>();
	    edge.put(mxConstants.STYLE_ROUNDED, true);
	    edge.put(mxConstants.STYLE_ORTHOGONAL, false);
	    edge.put(mxConstants.STYLE_EDGE, "elbowEdgeStyle");
	    edge.put(mxConstants.STYLE_ENDARROW, mxConstants.NONE);
	    //edge.put(mxConstants.STYLE_EDGE, mxConstants.no);
	    edge.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_CONNECTOR);
	    //edge.put(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_CLASSIC);
	    edge.put(mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_MIDDLE);
	    edge.put(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_CENTER);
	    edge.put(mxConstants.STYLE_STROKECOLOR, "#000000"); // default is #6482B9
	    edge.put(mxConstants.STYLE_FONTCOLOR, "#446299");

	    mxStylesheet edgeStyle = new mxStylesheet();
	    edgeStyle.setDefaultEdgeStyle(edge);
	    graph.setStylesheet(edgeStyle);
	}
}
