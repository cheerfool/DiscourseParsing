package granularity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;

public class CountSpan {

	public static final String rootDir = "D:/Data/RST-DT/";
	//public static final String rootDir = "~/Projects/dataset/RST-DT/";
	public static final String rstTreesDir = rootDir + "ALL/";
	public static final String relationClassPath = rootDir + "RelationClasses.txt";
	public static final String resultPath = rootDir + "results/relationLevel.csv";
	public static final String formatResultPath = rootDir + "results/relationLevel.txt";

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		GranularityMap countMap = new GranularityMap();
		RelationClassifier relClassifier = new RelationClassifier(relationClassPath);

		FileParser parser = new FileParser(countMap, relClassifier);

		FilenameFilter fileFilter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".dis");
			}
		};

		File dir = new File(rstTreesDir);
		File[] trees = dir.listFiles(fileFilter);

		System.out.println(trees.length);
		for (File file : trees) {
			System.out.println(parser.parse(file) + ":" + file.getName());
		}

		System.out.println("Parsed: " + parser.count);
		countMap.write(new FileOutputStream(formatResultPath));
		countMap.write(System.out);
		countMap.export(new FileOutputStream(resultPath));
	}

}
