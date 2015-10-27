package granularity;

import org.junit.Test;

public class TestGranularityMap {

	@Test
	public void test() {
		GranularityMap map = new GranularityMap();
		map.addOne("Joint", 3);
		map.addOne("Explanation", 45);
		map.addOne("Comparison", 1);
		
		map.write(System.out);
		map.export(System.out);
	}

}
