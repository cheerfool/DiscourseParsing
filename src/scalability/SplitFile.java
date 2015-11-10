package scalability;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;

public class SplitFile {

	public static final String rootDir = "D:/Dropbox/Research/Dataset/RST-DT/";
	public static final String tokenFilePath = rootDir + "token/";
	public static final String outputPath = rootDir + "sentence/";

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		FilenameFilter fileFilter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".tok");
			}
		};

		File dir = new File(tokenFilePath);
		File[] docs = dir.listFiles(fileFilter);
		System.out.println(docs.length);

		FileParser parser = new FileParser();
		parser.setOutputPath(outputPath);

		for (File file : docs) {
			System.out.println(file.getName() + ": " + parser.parse(file) + " sentence(s).");
		}

		System.out.println("Total number of parsed documents: " + parser.docCount);
	}

}
