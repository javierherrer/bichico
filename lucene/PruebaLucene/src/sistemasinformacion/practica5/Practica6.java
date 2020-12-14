package sistemasinformacion.practica5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.es.SpanishAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.MMapDirectory;

public class Practica6 {

	/**
	 * Relación de ficheros a indexar / buscar
	 */
	private Collection<String> ficherosAIndexar = new ArrayList<String>();
	/**
	 * Relación de palabras clave a buscar
	 */
	private Collection<String> queries = new ArrayList<String>();
	/**
	 * Analizar utilizado por el indexador / buscador
	 */
	private static Analyzer analizador;
	
	static Directory directorio = null;
	static Collection<String> ficheros = null;

	private final static String INDEXDIR = "./ficheros/indice";

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		analizador = new SpanishAnalyzer();
		int numero = 0;
		
		do {
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!Ficheros y directorios¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡");
			mostrarFicheros();
			System.out.println("********************* Menu de opciones *********************");
			System.out.println("1.- Indexar un directorio");
			System.out.println("2.- Añadir un documento al índice");
			System.out.println("3.- Buscar término");
			System.out.println("4.- Salir");
			numero = reader.nextInt();

			if (numero == 1) {
				ficheros = indexarDocumento();
				try {
					directorio = crearIndiceEnUnDirectorio();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (numero == 2) {
				reader.nextLine();
				System.out.println("Introduce ruta del documento");
				String ruta = reader.nextLine();
				File newFIle = new File(ruta);
				if (newFIle.exists()) {
					try {
						directorio = crearIndiceDeUnFichero(newFIle);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					System.out.println("fichero no existente");
				}
			} else if (numero == 3) {
				reader.nextLine();
				try {
					directorio = MMapDirectory.open(Paths.get(INDEXDIR));
					for (String string : directorio.listAll()) {
						System.out.println(string);
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("Introduce termino a buscar");
				String termino = reader.nextLine();
				try {
					System.out.println(directorio);
					System.out.println(ficheros);
					buscarQueryEnIndice(directorio, ficheros.size(), 1, termino);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (numero == 4) {
				System.out.println("Cerrando programa");
			} else {
				System.out.println("Introducido numero incorrecto");
			}

		} while (numero != 4);

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
		} else {
			for (int i = 0; i < listado.length; i++) {
				File fichero = new File(carpeta.getPath() + "/" + listado[i]);
				if (fichero.isDirectory()) {
					directorios.add(fichero);
				} else {
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
		} else {
			for (int i = 0; i < listado.length; i++) {
				File fichero = new File(carpeta.getPath() + "/" + listado[i]);
				if (fichero.isDirectory()) {
					directorios.add(fichero);
				} else {
					// System.out.println("Documento : "+(i+1)+".- "+listado[i]);
				}

			}
		}
		int x = 1;
		for (File file : directorios) {
			System.out.println(x + ".- " + file.getPath());
		}
		System.out.println("Seleccione numero de directorio");
		int numero = reader.nextInt();
		// System.out.println(carpeta.getPath()+"/"+directorios.get(numero-1));
		carpeta = new File(directorios.get(numero - 1).getPath());
		listado = carpeta.list();
		Collection<String> ficheros = new ArrayList<String>();
		directorios = new ArrayList<File>();
		if (listado == null || listado.length == 0) {
			System.out.println("No hay elementos dentro de la carpeta actual");
			return null;
		} else {
			for (int i = 0; i < listado.length; i++) {
				File fichero = new File(carpeta.getPath() + "/" + listado[i]);
				if (fichero.isDirectory()) {
					directorios.add(fichero);
				} else {
					System.out.println("Fichero añadido " + fichero.getPath());
					ficheros.add(fichero.getPath());
				}

			}
		}

		while (directorios.size() != 0) {
			carpeta = new File(directorios.get(0).getPath());
			listado = carpeta.list();
			for (int i = 0; i < listado.length; i++) {
				File fichero = new File(carpeta.getPath() + "/" + listado[i]);
				if (fichero.isDirectory()) {
					directorios.add(fichero);
				} else {
					System.out.println("Fichero añadido " + fichero.getPath());
					ficheros.add(fichero.getPath());
				}

			}
			directorios.remove(0);

		}

		return ficheros;
	}

	public static void buscarQueryEnIndice(Directory directorioDelIndice, int paginas, int hitsPorPagina,
			String queryAsString) throws IOException {
		System.out.println(directorioDelIndice);
		DirectoryReader directoryReader = DirectoryReader.open(directorioDelIndice);
		IndexSearcher buscador = new IndexSearcher(directoryReader);

		QueryParser queryParser = new QueryParser("contenido", analizador);
		Query query = null;
		try {
			query = queryParser.parse(queryAsString);
			TopDocs resultado = buscador.search(query, paginas * hitsPorPagina);
			ScoreDoc[] hits = resultado.scoreDocs;

			System.out.println("\nBuscando " + queryAsString + ": Encontrados " + hits.length + " hits.");
			int i = 0;
			for (ScoreDoc hit : hits) {
				int docId = hit.doc;

				Document doc = buscador.doc(docId);
				System.out.println((++i) + ". " + doc.get("path") + "\t" + hit.score);
			}

		} catch (ParseException e) {
			throw new IOException(e);
		}
	}
	
	public static Directory crearIndiceEnUnDirectorio() throws IOException{
		IndexWriter indice = null;
		Directory directorioAlmacenarIndice = new MMapDirectory(Paths.get(INDEXDIR));

		IndexWriterConfig configuracionIndice = new IndexWriterConfig(analizador);

		indice = new IndexWriter(directorioAlmacenarIndice, configuracionIndice);
		
		for (String fichero : ficheros) {
			System.out.println(fichero);
			anhadirFichero(indice, fichero);
		}
		
		indice.close();
		return directorioAlmacenarIndice;
	}
	
	/**
	 * Añade un fichero al índice
	 * @param indice Indice que estamos construyendo
	 * @param path ruta del fichero a indexar
	 * @throws IOException
	 */
	private static void anhadirFichero(IndexWriter indice, String path) 
	throws IOException {
		InputStream inputStream = new FileInputStream(path);
		BufferedReader inputStreamReader = new BufferedReader(
				new InputStreamReader(inputStream,"UTF-8"));
		
		Document doc = new Document();   
		doc.add(new TextField("contenido", inputStreamReader));
		doc.add(new StringField("path", path, Field.Store.YES));
		indice.addDocument(doc);
	}
	
	public static Directory crearIndiceDeUnFichero(File fichero) throws IOException{
		IndexWriter indice = null;
		Directory directorioAlmacenarIndice = new MMapDirectory(Paths.get(INDEXDIR));
		IndexWriterConfig configuracionIndice = new IndexWriterConfig(analizador);

		indice = new IndexWriter(directorioAlmacenarIndice, configuracionIndice);
		anhadirFichero(indice, fichero.getPath());
		indice.close();
		return directorioAlmacenarIndice;
	}
}
