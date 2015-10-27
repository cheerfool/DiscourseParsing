package granularity;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class CountSpan {

	public static final String rootDir = "D:/Data/RST-DT/ALL/";

	public static final String relationClassPath = rootDir + "RelationClasses.txt";

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		GranularityMap countMap = new GranularityMap();
		RelationClassifier relClassifier = new RelationClassifier(relationClassPath);

		FilenameFilter fileFilter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".dis");
			}
		};

		File dir = new File(rootDir);
		File[] trees = dir.listFiles(fileFilter);

		// for(File file: trees){
		// System.out.println(file.getName());
		// }
		// System.out.println(trees.length);

	}

}
