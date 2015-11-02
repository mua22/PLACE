package JSHOP2Wrapper;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import antlr.RecognitionException;
import antlr.TokenStreamException;
import JSHOP2.InternalDomain;
import JSHOP2.Plan;

public class JSHOP2WrapperTest {

	public static void main(String[] args) {
		String example = "blocks";
		String folder = "F:\\epclaimcodes\\shop2\\"+example+"\\";
		String domainFileName = folder+example;
		String problemFileName = folder+"problem";
		File file = new File(domainFileName);
		System.out.println(file.getPath());
		try {
			InternalDomain internalDomain = new InternalDomain(new File(domainFileName), -1);			
			internalDomain.getParser().domain();
			InternalDomain internalDomainProblem = new InternalDomain(new File(problemFileName), 1);			
			internalDomainProblem.getParser().command();
			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
			File problemJavaFile = new File(folder+"problem.java");
			System.out.println(problemJavaFile.getPath());
			System.out.println("Compiler Status: "+compiler);
			String classPath = folder+";F:\\EPClaim\\ClientWorkspace\\EPClaim\\bin";
			System.out.println("Class Path: "+ classPath);
			compiler.run(null, null, null, (new File(folder+example+".java")).getPath());
			compiler.run(null, null, null, "-classpath",classPath,"-sourcepath",folder,problemJavaFile.getPath());
			
			System.out.println("Done");
			
		} catch (IOException | RecognitionException | TokenStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<String> classFiles = new ArrayList<String>();
		
		ClassLoader classLoader = ProblemClassLoader.class.getClassLoader();
		ProblemClassLoader problemClassLoader = new ProblemClassLoader(classLoader);
		try {
			File folderToLoad = new File(folder);
			for(File f: folderToLoad.listFiles()){
				String classFileName = f.getName();
				if(classFileName.endsWith(".class"))
				{
					String classFileNameWithoutDotClass = classFileName.substring(0,classFileName.length()-6);
					classFiles.add(classFileNameWithoutDotClass);
					System.out.println(classFileNameWithoutDotClass);
				}
			}
			Class<?> problem = null;
			ArrayList<Class<?>> allClasses = new ArrayList<Class<?>>();
			for(String className:classFiles){
				Class<?> dummy = problemClassLoader.loadClass(className,folder);
				if(className.equals("problem"))
					problem = dummy;
				allClasses.add(dummy);
				
			}
			
			//Class<?> problem = problemClassLoader.loadClass("problem",folder);
			Method method = problem.getMethod("getPlans", null);
			LinkedList<Plan> plans =(LinkedList<Plan>) method.invoke(null, null);
			System.out.println("aClass.getName() = " + problem.getName());
			System.out.println("Plans: "+ plans);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
