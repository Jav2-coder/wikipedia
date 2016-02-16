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
 */
public class App {

	public static void main(String[] args) throws IOException {

		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("colors.txt"), "utf-8"));
		
		Document doc = Jsoup.connect("https://ca.wikipedia.org/wiki/Llista_de_colors").get();

		Elements tbody = doc.select("tbody");

		Elements tr = tbody.select("tr");

		for (int i = 1; i < tr.size(); i++) {

			Elements th = tr.get(i).select("th");

			Elements td = tr.get(i).select("td");

			writer.write("INSERT INTO colors (nom, colorhex, red, green, blue) VALUES (“" + th.text() + "”, “"
					+ td.get(1).text() + "”, " + td.get(2).text() + ", " + td.get(3).text() + ", " + td.get(4).text()
					+ ");\n");
		}
		
		writer.close();
		
	}
}
