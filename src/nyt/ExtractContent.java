package nyt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import com.nytlabs.corpus.NYTCorpusDocument;
import com.nytlabs.corpus.NYTCorpusDocumentParser;

public class ExtractContent {
	public static final String inputPath = "D:/Data/nyt_corpus/xml/";
	public static final String outputPath = "D:/Data/nyt_corpus/text/";
	public static NYTCorpusDocumentParser parser = new NYTCorpusDocumentParser();

	public static void main(String[] args) throws UnsupportedEncodingException, IOException {
		// TODO Auto-generated method stub
		extractText("2007");
	}

	public static void extractText(String year) throws IOException, UnsupportedEncodingException {
		File yearDir = new File(inputPath + year + "/");
		int count = 0;
		FilenameFilter fileFilter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".xml");
			}
		};

		for (File monthDir : yearDir.listFiles()) {
			if (!monthDir.isDirectory())
				continue;
			String month = monthDir.getName();
			for (File dayDir : monthDir.listFiles()) {
				if (!dayDir.isDirectory())
					continue;
				String day = dayDir.getName();
				File outputDayDir = new File(outputPath + year + "/" + month + "/" + day + "/");
				if (!outputDayDir.exists())
					outputDayDir.mkdirs();
				for (File article: dayDir.listFiles(fileFilter)){
					NYTCorpusDocument doc = parser.parseNYTCorpusDocumentFromFile(article, false);
					String outputDocPath = outputDayDir.getAbsolutePath() + "/" + doc.getGuid() + ".txt";
					PrintWriter pw = new PrintWriter (outputDocPath, "utf-8");
					pw.print(doc.getBody());
					pw.close();
					count ++;
				}
			}
		}
		
		System.out.println("Year " + year + ": " + count + " articles parsed.");
	}

}
