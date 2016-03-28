package xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileParser {
	String outputPath = "./";
	int docCount = 0;
	RelationClassifier classifier;
	public static Pattern pattern = Pattern
			.compile("\\( (root|nucleus|satellite) \\((?:span|leaf) ([^\\)]+)\\) *\\(rel\\dpar ([^\\)]+)\\)( \\(text _!([^\n]+)_!\\))?");
	public static Pattern scPattern = Pattern.compile("span ([^\\)]+)");
	FileParser(String outputPath, RelationClassifier classifier) {
		this.outputPath = outputPath;
		this.classifier = classifier;
	}

	int parse(File file) throws IOException {
		String filename = file.getName().split("\\.")[0];
		FileInputStream fis = new FileInputStream(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));

		String outputFileName = outputPath + filename + ".xml";
		PrintWriter pw = new PrintWriter(outputFileName, "utf-8");

		int level = 0;
		Stack<String> tags = new Stack<String>();

		String line = br.readLine();
		Matcher headMatcher = scPattern.matcher(line);
		if(headMatcher.find()){
			//System.out.println(line+"\n"+headMatcher.group(0)+"\n"+headMatcher.group(1));
			String[] spans = headMatcher.group(1).split(" ");
			int span = 1;
			if (spans.length > 1) {
				span = Integer.parseInt(spans[1])
						- Integer.parseInt(spans[0]) + 1;
				pw.println("<Doc spans=\"" + span + "\">");
			}
		}else{
			pw.println("<Doc>");
		}

		while ((line = br.readLine()) != null) {
			line = line.trim().toLowerCase();
			if (line.equals(")") && !tags.isEmpty()) {
				level--;
				if (level < 0)
					continue;
				printxml(pw, level, "</" + tags.pop() + ">");
			} else {
				Matcher matcher = pattern.matcher(line);
				if (matcher.find()) {
					String relation = classifier.classify(matcher.group(3));

					String[] spans = matcher.group(2).split(" ");
					int span = 1;
					if (spans.length > 1) {
						span = Integer.parseInt(spans[1])
								- Integer.parseInt(spans[0]) + 1;
					}

					printxml(pw, level,
							"<" + relation + " class=\"" + matcher.group(1)
									+ "\" spans=\"" + span + "\">");

					String content = matcher.group(5);
					if (content != null) {
						content = content.replaceAll("<p>", "");
						content = content.replaceAll("&", "&amp;");
						printxml(pw, level + 1, content);
						printxml(pw, level, "</" + relation + ">");
					} else {
						level++;
						tags.push(relation);
					}
				}
			}
		}

		while (!tags.isEmpty() && level > 0) {
			level--;
			printxml(pw, level, "</" + tags.pop() + ">");
		}

		pw.println("</Doc>");
		pw.close();
		br.close();
		fis.close();

		docCount++;
		return docCount;
	}

	// public void check(String line) {
	// Matcher matcher = pattern.matcher(line);
	// if (matcher.find()) {
	// String relation = classifier.classify(matcher.group(2));
	//
	// int spanCount;
	// String[] spans = matcher.group(1).split(" ");
	// if (spans.length > 1) {
	// int headSpan = Integer.parseInt(spans[0]);
	// int tailSpan = Integer.parseInt(spans[1]);
	// spanCount = tailSpan - headSpan + 1;
	// } else {
	// spanCount = 1;
	// }
	//
	// map.addOne(relation, spanCount);
	// }
	// }

	public void printxml(PrintWriter pw, int n, String content) {
		if (n > 0) {
			for (int i = 0; i < n * 2; i++)
				pw.print(" ");
		}
		pw.println(content);
	}
}
