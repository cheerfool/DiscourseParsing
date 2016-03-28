package xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class RemoveTag {

	public static final String rootDir = "/Users/jkl/Projects/dataset/RST-DT/";
	public static final String inputFilePath = rootDir + "xmls/";
	public static final String outputPath = rootDir + "xml/";
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FilenameFilter fileFilter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".xml");
			}
		};

		File dir = new File(inputFilePath);
		File[] docs = dir.listFiles(fileFilter);
		System.out.println(docs.length);

		int count = 0;
		for (File file : docs) {
			FileInputStream fis = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			PrintWriter pw = new PrintWriter(outputPath + file.getName(), "utf-8");
			pw.println("<Doc>");
			
			String line;
			while ((line = br.readLine()) != null) {
				if(!line.equals(""))
					pw.println("  " + line.replaceAll("<p>", ""));
			}
			
			pw.println("</Doc>");
			pw.close();
			br.close();
			fis.close();
			count++;
			System.out.println(file.getName() + " parsed.");
		}

		System.out.println("Total number of parsed documents: " + count);
	}
}
