package granularity;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map.Entry;

public class GranularityMap {
	public HashMap<String, int[]> map = new HashMap<String, int[]>();

	int addOne(String relation, int span) {
		int[] freq = map.get(relation);
		if (freq == null) {
			freq = new int[30];
			map.put(relation, freq);
		}
		int index = span < 30 ? span - 1 : 29;
		freq[index]++;
		return freq[index];
	}

	void write(OutputStream os) {
		PrintWriter pw = new PrintWriter(os);
		pw.print("Relation\t");
		for (int i = 0; i < 29; i++)
			pw.print((i + 1) + "\t");
		pw.println("30+\t");

		for (Entry<String, int[]> record : map.entrySet()) {
			String relation = record.getKey();
			int[] freq = record.getValue();
			pw.print(relation + "\t");
			for (int n : freq)
				pw.print(n + "\t");
			pw.println();
		}

		pw.flush();
	}

}
