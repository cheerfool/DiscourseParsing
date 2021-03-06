package granularity;

import java.io.IOException;
import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Test;

public class TestRelationClassifier {

	String relationPath = CountSpan.relationClassPath;

	@Test
	public void test() throws IOException {
		RelationClassifier classifier = new RelationClassifier(relationPath);
//		for (Entry<String, String> pair : classifier.map.entrySet()) {
//			System.out.println(pair.getKey() + " -> " + pair.getValue());
//		}
//		System.out.println(classifier.map.size());
//		System.out.println(classifier.classify("Problem-Solution"));
//		System.out.println(classifier.classify("span"));
		
		Assert.assertTrue(classifier.map.size() == 114);
		Assert.assertEquals("Elaboration", classifier.classify("elaboration-additional"));
		Assert.assertEquals("Topic-Comment", classifier.classify("Problem-Solution"));
	}

}
