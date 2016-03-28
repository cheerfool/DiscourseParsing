package xml;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class RelationClassifier {

	public HashMap<String, String> map = new HashMap<String, String>();

	RelationClassifier(String filename) throws IOException {
		FileInputStream fis = new FileInputStream(filename);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));

		String line = null;
		while ((line = br.readLine()) != null) {
			String[] wordlist = line.split(":");
			if (wordlist.length > 1) {
				String formalRelation = wordlist[0];
				String[] relations = wordlist[1].trim().split(",");
				for (String relation : relations)
					map.put(relation.trim().toLowerCase(), formalRelation);
			}
		}

		br.close();
		fis.close();
	}

	public String classify(String OriginalRelation) {
		String result = map.get(OriginalRelation.toLowerCase());
		if (result == null)
			return "Unknown";
		else
			return result;
	}
}
