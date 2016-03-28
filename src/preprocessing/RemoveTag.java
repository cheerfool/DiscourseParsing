package preprocessing;

import granularity.FileParser;
import granularity.GranularityMap;
import granularity.RelationClassifier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class RemoveTag {

	public static final String rootDir = "/Users/jkl/Downloads/eng_news_txt_tbnk-ptb_revised/data/tokenized_source/";
	public static final String inputPath = rootDir;
	public static final String outputPath = "/Users/jkl/Projects/dataset/PennTB/";
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		File inputDir = new File(inputPath);
		for(File subDir: inputDir.listFiles()){
			if(subDir.isDirectory()){
				System.out.print(subDir.getAbsolutePath() + " : ");
				String outputSubDir = outputPath + subDir.getName();
				(new File(outputSubDir)).mkdirs();
				removeHead(subDir.getAbsolutePath(), ".txt", outputSubDir);
			}
		}
	
	}
	
	static void removeHead(String inputDir, String suffix, String outputDir) throws IOException{
		FilenameFilter fileFilter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(suffix);
			}
		};

		File dir = new File(inputDir);
		File[] docs = dir.listFiles(fileFilter);
		System.out.println(docs.length);

		int count = 0;
		for (File file : docs) {
			FileInputStream fis = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			PrintWriter pw = new PrintWriter(outputDir + "/" + file.getName(), "utf-8");
			
			String line;
			while ((line = br.readLine()) != null) {
				if(!line.equals(""))
					pw.println(line.replaceAll("<en=\\d+>", ""));
			}
			
			pw.close();
			br.close();
			fis.close();
			count++;
			//System.out.println(file.getName() + " parsed.");
		}

		System.out.println("Total number of parsed documents: " + count);
	}
}
