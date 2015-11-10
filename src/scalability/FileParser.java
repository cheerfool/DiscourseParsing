package scalability;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileParser {
	String outputPath = "./";
	int docCount = 0;
	public static Pattern pattern = Pattern.compile("<s>([^<]+)</s>");

	String setOutputPath(String path) {
		this.outputPath = path;
		File outputDir = new File(outputPath);
		return outputDir.getAbsolutePath();
	}

	int parse(File file) throws IOException {
		String filename = file.getName().split("\\.")[0];
		int count = 0;
		FileInputStream fis = new FileInputStream(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		String line;

		while ((line = br.readLine()) != null) {
			Matcher matcher = pattern.matcher(line);
			if (matcher.find()) {
				count++;
				String sentence = matcher.group(1).trim();
				String outputFileName = outputPath + filename + "-" + count + ".stc";
				PrintWriter pw = new PrintWriter(outputFileName, "utf-8");
				pw.println(sentence);
				pw.close();
			}
		}

		docCount++;
		return count;
	}
}
