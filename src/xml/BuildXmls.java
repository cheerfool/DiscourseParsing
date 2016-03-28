package xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;

public class BuildXmls {

	//public static final String rootDir = "D:/Dropbox/Research/Dataset/RST-DT/";
	public static final String rootDir = "/Users/jkl/Projects/dataset/PennTB/";
	public static final String rstFilePath = rootDir + "parsed/";
	public static final String outputPath = rootDir + "xmls/";
	public static final String relationClassPath = rootDir + "RelationClasses.txt";

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		FilenameFilter fileFilter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith("_doc.dis");
			}
		};

		File dir = new File(rstFilePath);
		File[] docs = dir.listFiles(fileFilter);
		System.out.println(docs.length);

		RelationClassifier relClassifier = new RelationClassifier(relationClassPath);
		
		FileParser parser = new FileParser(outputPath, relClassifier);

		for (File file : docs) {
			System.out.println(parser.parse(file) + ": " + file.getName() + " parsed.");
		}

		System.out.println("Total number of parsed documents: " + parser.docCount);
	}

}
