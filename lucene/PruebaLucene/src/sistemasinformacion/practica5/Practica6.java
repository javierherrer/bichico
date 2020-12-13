package sistemasinformacion.practica5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;

public class Practica6 {

	/**
	 * Relación de ficheros a indexar / buscar
	 */
	private Collection <String> ficherosAIndexar = new ArrayList<String>();
	/**
	 * Relación de palabras clave a buscar
	 */
	private Collection <String> queries = new ArrayList <String>();
	/**
	 * Analizar utilizado por el indexador / buscador 
	 */
	private Analyzer analizador;
	
	private final static String INDEXDIR = "./ficheros/indice";
	
	
	public static void main(String[] args) {
	Scanner reader = new Scanner(System.in);
	int numero = 0;	
	IndexadorYBuscador indexador = null;
	Directory directorio = null;
	Collection<String> ficheros = null;
	do {
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!Ficheros y directorios¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡");
		mostrarFicheros();
		System.out.println("********************* Menu de opciones *********************");
		System.out.println("1.- Indexar un directorio");
		System.out.println("2.- Añadir un documento al índice");
		System.out.println("3.- Buscar término");
		System.out.println("4.- Salir");
		numero = reader.nextInt();
		
		
		if(numero == 1) {
			ficheros = indexarDocumento();
			indexador = new IndexadorYBuscador(ficheros);
			try {
				directorio = indexador.crearIndiceEnUnDirectorio();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(numero == 2) {
				reader.nextLine();
				System.out.println("Introduce ruta del documento");
				String ruta = reader.nextLine();
				File newFIle = new File(ruta);
				if(newFIle.exists()) {
					try {
						directorio = indexador.crearIndiceDeUnFichero(newFIle);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					System.out.println("fichero no existente");
				}
		}else if(numero == 3) {
			reader.nextLine();
			System.out.println("Introduce termino a buscar");
			String termino = reader.nextLine();
			try {
				System.out.println(directorio);
				System.out.println(ficheros);
				System.out.println(termino);
				indexador.buscarQueryEnIndice(directorio, ficheros.size(), 1, termino);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(numero == 4) {
			System.out.println("Cerrando programa");
		}else {
			System.out.println("Introducido numero incorrecto");
		}
		
	}while(numero != 4);

	}

	private static void mostrarFicheros() {
		
		File carpeta = new File("./Documentos");
		System.out.println("Carpeta raiz: " + carpeta.getPath());
		String[] listado = carpeta.list();
		ArrayList<File> ficheros = new ArrayList();
		ArrayList<File> directorios = new ArrayList();
		if (listado == null || listado.length == 0) {
		    System.out.println("No hay elementos dentro de la carpeta actual");
		    return;
		}
		else {
		    for (int i=0; i< listado.length; i++) {
		    	 File fichero = new File(carpeta.getPath()+"/"+listado[i]);
		    	 if(fichero.isDirectory()) {
		    		  directorios.add(fichero);
		    	 }else {
		    		  ficheros.add(fichero);
		    	 }
		      
		    }
		}
		System.out.println("****Directorios****");
		for (File file : directorios) {
			System.out.println(file.getPath());
		}
		System.out.println("****Ficheros*****");
		for (File file : ficheros) {
			System.out.println(file.getPath());
		}
		
	}

	private static Collection<String> indexarDocumento() {
		Scanner reader = new Scanner(System.in);
		File carpeta = new File("./Documentos");
		String[] listado = carpeta.list();
		ArrayList<File> directorios = new ArrayList();
		if (listado == null || listado.length == 0) {
		    System.out.println("No hay elementos dentro de la carpeta actual");
		    return null;
		}
		else {
		    for (int i=0; i< listado.length; i++) {
		    	 File fichero = new File(carpeta.getPath()+"/"+listado[i]);
		    	 if(fichero.isDirectory()) {
		    		  directorios.add(fichero);
		    	 }else {
		    		  //System.out.println("Documento : "+(i+1)+".- "+listado[i]);
		    	 }
		      
		    }
		}
		int x = 1;
		for (File file : directorios) {
			System.out.println(x+".- "+ file.getPath());
		}
		System.out.println("Seleccione numero de directorio");
		int numero = reader.nextInt();
		//System.out.println(carpeta.getPath()+"/"+directorios.get(numero-1));
		carpeta = new File(directorios.get(numero-1).getPath());
		listado = carpeta.list();
		Collection <String> ficheros = new ArrayList <String>();
		 directorios = new ArrayList<File>();
		if (listado == null || listado.length == 0) {
		    System.out.println("No hay elementos dentro de la carpeta actual");
		    return null;
		}
		else {
			 for (int i=0; i< listado.length; i++) {
				 File fichero = new File(carpeta.getPath()+"/"+listado[i]);
				 if(fichero.isDirectory()) {
					 directorios.add(fichero);
				 }else {
						System.out.println("Fichero añadido "+ fichero.getPath());
					ficheros.add(fichero.getPath());
				 }
				 	
			    }
		}
		
		
		while(directorios.size() != 0) {
			carpeta = new File(directorios.get(0).getPath());
			listado = carpeta.list();
			for (int i=0; i< listado.length; i++) {
				 File fichero = new File(carpeta.getPath()+"/"+listado[i]);
				 if(fichero.isDirectory()) {
					 directorios.add(fichero);
				 }else {
					System.out.println("Fichero añadido "+ fichero.getPath());
					ficheros.add(fichero.getPath());
				 }
				 	
			    }
			directorios.remove(0);
			
		}
		
		return ficheros;
	}
}
