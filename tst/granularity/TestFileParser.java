package granularity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.junit.Assert;

public class TestFileParser {

	@Test
	public void test() {
		Pattern p = FileParser.pattern;
		Matcher m = p.matcher("              ( Nucleus (span 16 17) (rel2par Contrast)");

		Assert.assertTrue(m.find());
		Assert.assertEquals(2, m.groupCount());
		Assert.assertEquals("16 17", m.group(1));
		Assert.assertEquals("Contrast", m.group(2));

		Matcher m2 = p.matcher("  ( Nucleus (leaf 1) (rel2par span) (text _!Spencer J. Volk, president and chief operating officer of this consumer and industrial products company, was elected a director._!) )");
		Assert.assertTrue(m2.find());
		Assert.assertEquals(2, m2.groupCount());
		Assert.assertEquals("1", m2.group(1));
		Assert.assertEquals("span", m2.group(2));
		
//		if (m2.find()) {
//			System.out.println(m2.groupCount());
//			System.out.println(m2.group(0));
//			System.out.println(m2.group(1));
//			System.out.println(m2.group(2));
//		}
	}

}
