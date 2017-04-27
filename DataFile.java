package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class DataFile{
	
	private File file;
	private FileWriter fileWriter;
	private FileReader fileReader;
	private BufferedWriter bufferedWriter = null;
	private BufferedReader bufferedReader = null;
	private OpenForm openForm;
	
	public DataFile(String nameFile, OpenForm openForm){
		file = new File(nameFile);
		this.openForm = openForm;	
	}
	
	public File getFile() {
		return file;
	}
	
	public void setFile(File file) {
		this.file = file;
	}
	
	public OpenForm getOpenForm() {
		return openForm;
	}

	public void setOpenForm(OpenForm openForm) {
		this.openForm = openForm;
	}

	/**
	 * Abrir el archivo
	 * @param mode  el modo en que se trabja en archivo
	 */
    public void open(){
	   try {
		//modo escritura "w" crea el archivo
		  switch (this.openForm) {
		  case WRITE:
			  fileWriter = new FileWriter(file);
			  bufferedWriter = new BufferedWriter(fileWriter);
			  break;
		  case READ:
			  fileReader = new FileReader(file);
			  bufferedReader = new BufferedReader(fileReader);
			  break;
		  case APPEND:
			  fileWriter = new FileWriter(file, true);
			  bufferedWriter = new BufferedWriter(fileWriter);
			  break;
		  }
	   } catch (IOException e) {
		  System.out.println("Error al abrir archivo");
	   }	
     }

    /**
     * Guarda la cadena ingresada por parametro
     * @param cad
     */
	public void write(String cad){
		if (this.openForm == OpenForm.WRITE || this.openForm == OpenForm.APPEND){
			try {
				bufferedWriter.write(cad);
				bufferedWriter.newLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
     }
	
     /**
      * Lee una lnea del archivo
      * @return
      */
	 public String read(){		  
		String cad="";	
			try {
				if (this.openForm == OpenForm.READ) {
					cad = bufferedReader.readLine();
				}
				
			} catch (IOException e) {
				System.out.println("Error al leer el archivo");
			}
		return cad;		   
	 }
	 
    /**
     * cierra archivo 
     */
	 public void close(){
			try {
			  switch (this.openForm) {	
			    case WRITE: case APPEND:
			    	bufferedWriter.close();
			    	fileWriter.close();
			    	break;
			    case READ:
			    	fileReader.close();
			    	bufferedReader.close();
			    	break;
			  }
			} catch (IOException e) {
				System.out.println("Error al cerrar archivo");
			}
	 }
	 
	 public String order(String newFile){
	        String orderedChain = "";
	        LinkedList<String> lista = new LinkedList<String>();
	        open();
	        try {
	            while ((orderedChain = bufferedReader.readLine()) != null) {            
	                lista.add(orderedChain);
	            }
	            Collections.sort(lista);
	            Iterator<String> iterator = lista.iterator();
	            String cadena;
	            while (iterator.hasNext()){
	                cadena = (String) iterator.next();
	                bufferedWriter.write(cadena);
	                System.out.println(cadena);
	            }
	        } catch (Exception e) {
	            System.out.println("No se encuetra el Archivo");
	        }
	        return orderedChain;
	 }
	 
	 public void copy(String nameTarget){
	        DataFile resultFile = new DataFile(nameTarget, OpenForm.WRITE);
	        this.setOpenForm(OpenForm.READ);
	        resultFile.open();
	        this.open();
	        String line = this.read();
	        while (line != null) {            
	            resultFile.write(line);
	            line = this.read();
	        }
	        this.close();
	        resultFile.close();
	 }
	 	 
}
