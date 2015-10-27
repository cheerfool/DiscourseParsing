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
		pw.format("%-20s\t", "Relation");
		for (int i = 0; i < 29; i++)
			pw.print((i + 1) + "\t");
		pw.println("30+\t");
		for (int i = 0; i < 260; i++)
			pw.print("-");
		pw.println();

		for (Entry<String, int[]> record : map.entrySet()) {
			String relation = record.getKey();
			int[] freq = record.getValue();
			pw.format("%-20s\t", relation);
			for (int n : freq)
				pw.print(n + "\t");
			pw.println();
		}
		pw.flush();
	}

	void export(OutputStream os) {
		PrintWriter pw = new PrintWriter(os);
		pw.print("Relation,");
		for (int i = 0; i < 29; i++)
			pw.print((i + 1) + ",");
		pw.println("30+");

		for (Entry<String, int[]> record : map.entrySet()) {
			String relation = record.getKey();
			int[] freq = record.getValue();
			pw.print(relation + ",");
			for (int i = 0; i < 29; i++)
				pw.print(freq[i] + ",");
			pw.println(freq[29]);
		}
		pw.flush();
	}

}
