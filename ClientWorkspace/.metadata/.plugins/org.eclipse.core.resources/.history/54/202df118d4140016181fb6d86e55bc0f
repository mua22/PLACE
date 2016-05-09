package epclaim.gui;

import javax.swing.JPanel;

import com.mxgraph.layout.mxFastOrganicLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.layout.mxParallelEdgeLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.util.mxMorphing;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;

public class GraphPanel extends JPanel{

	/**
	 * Create the panel.
	 */
	mxIGraphLayout layout;
	private final mxGraph graph = new mxGraph();
	public GraphPanel() {
		setLayout(new BorderLayout(0, 0));
		this.applyEdgeDefaults();
		//this.addTestData();
		//this.addTestData();
		this.addVertex("V1");
		this.addVertex("V21");
		this.addEdge(this.addVertex("V1"), this.addVertex("V21"));
		final mxGraphComponent graphComponent = new mxGraphComponent(graph);
		layout = new mxParallelEdgeLayout(graph);
		mxFastOrganicLayout layout = new mxFastOrganicLayout(graph);
		layout.setForceConstant(100);
//	    // layout using morphing
//	    graph.getModel().beginUpdate();
//	    try {
//	        layout.execute(graph.getDefaultParent());
//	    } finally {
//	        mxMorphing morph = new mxMorphing(graphComponent,40, 1.2, 40);
//
//	        morph.addListener(mxEvent.DONE, new mxIEventListener() {
//
//	            @Override
//	            public void invoke(Object arg0, mxEventObject arg1) {
//	                graph.getModel().endUpdate();
//	                // fitViewport();
//	            }
//
//	        });
//
//	        morph.startAnimation(); 
//	    }
		layout.execute(graph.getDefaultParent());
		this.add(graphComponent, BorderLayout.CENTER);
	    
	    
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
		   return graph.insertVertex(parent, null, str, 20, 20, 80,
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
