package granularity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileParser {
	GranularityMap map;
	RelationClassifier classifier;
	public int count = 0;

	public static Pattern pattern = Pattern.compile(".+\\((?:span|leaf) ([^\\)]+)\\) *\\(rel\\dpar ([^\\)]+)\\)");

	FileParser(GranularityMap map, RelationClassifier classifier) {
		this.map = map;
		this.classifier = classifier;
	}

	int parse(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		String line;
		while ((line = br.readLine()) != null)
			check(line);
		count++;
		return count;
	}

	public void check(String line) {
		Matcher matcher = pattern.matcher(line);
		if (matcher.find()) {
			String relation = classifier.classify(matcher.group(2));

			int spanCount;
			String[] spans = matcher.group(1).split(" ");
			if (spans.length > 1) {
				int headSpan = Integer.parseInt(spans[0]);
				int tailSpan = Integer.parseInt(spans[1]);
				spanCount = tailSpan - headSpan + 1;
			} else {
				spanCount = 1;
			}

			map.addOne(relation, spanCount);
		}
	}

}
