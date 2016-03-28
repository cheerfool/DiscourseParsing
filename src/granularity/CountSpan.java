package granularity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;

public class CountSpan {

	//public static final String rootDir = "D:/Data/RST-DT/";
	public static final String rootDir = "/Users/jkl/Projects/dataset/PennTB/";
	public static final String rstTreesDir = rootDir + "parsed/";
	public static final String relationClassPath = rootDir + "RelationClasses.txt";
	public static final String outputDir = rootDir+"stat/";
//	public static final String resultPath = rootDir + "results/relationLevel.csv";
//	public static final String formatResultPath = rootDir + "results/relationLevel.txt";
//	public static final String reviewDir = "/Users/jkl/Projects/dataset/review-dataset/";
//	public static final String reviewOutputDir = "/Users/jkl/Dropbox/research/Dataset/Shima/";
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		countSpan(rstTreesDir , "_doc.dis", outputDir, "PennTB");
		//countSpan(reviewDir, "_doc.dis", reviewOutputDir, "AAPDVDP");
//		File inputData = new File(rstTreesDir);
//		for(File subData: inputData.listFiles()){
//			if(subData.isDirectory()){
//				System.out.println(subData.getAbsolutePath());
//				countSpan(subData.getAbsolutePath(), "_doc.dis", outputDir, subData.getName());
//			}
//		}
	}
	
	static void countSpan(String rstDir, String suffix, String outputDir, String name) throws IOException{
		GranularityMap countMap = new GranularityMap();
		RelationClassifier relClassifier = new RelationClassifier(relationClassPath);

		FileParser parser = new FileParser(countMap, relClassifier);

		FilenameFilter fileFilter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(suffix);
			}
		};

		File dir = new File(rstDir);
		File[] trees = dir.listFiles(fileFilter);

		System.out.println(trees.length);
		for (File file : trees) {
			int order = parser.parse(file);
			//System.out.println(order + ":" + file.getName());
		}

		System.out.println(dir.getName() + " : " + parser.count + " trees Parsed.");
		countMap.write(new FileOutputStream(outputDir + name + ".txt"));
		countMap.write(System.out);
		countMap.export(new FileOutputStream(outputDir + name + ".csv"));
	}

}
