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
			freq = new int[31];
			map.put(relation, freq);
		}
		int index = span < 30 ? span : 30;
		freq[index]++;
		freq[0]++;
		return freq[index];
	}

	void write(OutputStream os) {
		PrintWriter pw = new PrintWriter(os);
		pw.format("%-20s%-7s", "Relation", "Total");
		for (int i = 1; i < 30; i++)
			pw.format("%-7d", i);
		pw.println("30+");
		for (int i = 0; i < 300; i++)
			pw.print("-");
		pw.println();

		for (Entry<String, int[]> record : map.entrySet()) {
			String relation = record.getKey();
			int[] freq = record.getValue();
			pw.format("%-20s%-7d", relation, freq[0]);
			for (int i = 1; i < 30; i++)
				pw.format("%-7d", freq[i]);
			pw.println(freq[30]);
		}
		pw.flush();
	}

	void export(OutputStream os) {
		PrintWriter pw = new PrintWriter(os);
		pw.print("Relation,Total,");
		for (int i = 1; i < 30; i++)
			pw.print(i + ",");
		pw.println("30+");

		for (Entry<String, int[]> record : map.entrySet()) {
			String relation = record.getKey();
			int[] freq = record.getValue();
			pw.print(relation + "," + freq[0] + ",");
			for (int i = 1; i < 30; i++)
				pw.print(freq[i] + ",");
			pw.println(freq[30]);
		}
		pw.flush();
	}

}
