package net.javierjimenez;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * 
 * @author alumne1daw
 *
 */
public class App {

	/**
	 * Metodo principal y unico del programa, encargado de leer el html del
	 * enlace que le pasamos (en este caso la Wikipedia) y generar un documento
	 * con comandos SQL que contienen la información que hemos extraido de la
	 * URL (en este caso hemos extraido valores sobre la lista de colores de la
	 * Wikipedia).
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		/*
		 * Objeto para escribir un documento que lo generara en la raiz donde se
		 * encuentre el programa
		 */
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("colors.txt"), "utf-8"));

		/*
		 * Objeto de Jsoup que nos permite acceder al archivo html de la URL que
		 * le pasamos.
		 */
		Document doc = Jsoup.connect("https://ca.wikipedia.org/wiki/Llista_de_colors").get();

		/*
		 * Objeto Elements que recoge las etiquetas tbody del html (En resumen:
		 * Un array de tbody).
		 */
		Elements tbody = doc.select("tbody");

		/*
		 * Objeto Elements que recoge las etiquetas tr que se encuentran dentro
		 * del Elements tbody.
		 */
		Elements tr = tbody.select("tr");

		for (int i = 1; i < tr.size(); i++) {

			/*
			 * Objeto Elements que recoge las etiquetas th que se encuentran en
			 * cada posicion del array de tr.
			 */
			Elements th = tr.get(i).select("th");

			/*
			 * Objeto Elements que recoge las etiquetas td que se encuentran en
			 * cada posicion del array de tr.
			 */
			Elements td = tr.get(i).select("td");

			/*
			 * Objeto write que escribe en un texto (o lo crea si no existe) los
			 * valores que le pasamos como string.
			 */
			writer.write("INSERT INTO colors (nom, colorhex, red, green, blue) VALUES (“" + th.text() + "”, “"
					+ td.get(1).text() + "”, " + td.get(2).text() + ", " + td.get(3).text() + ", " + td.get(4).text()
					+ ");\n");
		}

		/*
		 * Cerramos el objeto writer para mantener la eficiencia de nuestra
		 * maquina.
		 */
		writer.close();

	}
}
