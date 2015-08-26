package com.crack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;

public class Cracker {
	

	
	
	 public static void main(String[] args) throws Exception {  
	     
		  	String classname = "com.bluestripe.license.License";   // class filename 
	        String methodname = "parseFile";  						// crack method
	        ClassPool pool = ClassPool.getDefault();
	        CtClass cc = pool.get(classname);  
	        String methodContent = getParseFileString() ; 
	        try {  

	        	CtMethod mMethodName = cc.getDeclaredMethod(methodname) ;
	        	cc.removeMethod(mMethodName);							// delete parseFile method
				CtMethod cm = CtNewMethod.make(methodContent, cc);		//add parseFile method
				cc.addMethod(cm);	            
	            cc.writeFile();											//write back to file 
	        } catch (NotFoundException e) {  
	  
	            System.out.println(methodname + " not found!");  
	        }
	    }  
	
//	    public static void printClass(String classname) {  
//	        try {  
//	            System.out.println("Methods of Class " + classname + ":");  
//	            Class c = Class.forName(classname);  
//	            Method[] method = c.getDeclaredMethods();  
//	            for (int i = 0; i < method.length; i++) {  
//	                System.out.println(method[i]);  
//	            }  
//	            System.out.println();  
//	        } catch (ClassNotFoundException e) {  
//	            // TODO Auto-generated catch block  
//	            e.printStackTrace();  
//	        }  
//	    }  
	    
	    public static String getParseFileString(){
	    	String filename =  "parseFile.txt" ;
	    	StringBuilder sb = new StringBuilder() ;
	    	File file = new File(filename);
	        BufferedReader reader = null;
	         try {
	        
	             reader = new BufferedReader(new FileReader(file));
	             String tempString = null;
	             while ((tempString = reader.readLine()) != null) {
	        
	            	 sb.append(tempString) ;
	             }
	             reader.close();
	         } catch (IOException e) {
	             e.printStackTrace();
	         } finally {
	             if (reader != null) {
	                 try {
	                     reader.close();
	                 } catch (IOException e1) {
	                 }
	             }
	         }
	    	return sb.toString() ;
	    }
}

