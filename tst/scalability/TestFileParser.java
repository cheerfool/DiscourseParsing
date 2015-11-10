package scalability;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.junit.Assert;

public class TestFileParser {

	@Test
	public void test() throws IOException {
		String rootPath = "data/";

		FileParser parser = new FileParser();
		String absOutputPath = parser.setOutputPath(rootPath);
		System.out.println("Output path: " + absOutputPath);
		// Assert.assertEquals("", absOutputPath);

		int count = parser.parse(new File(rootPath + "wsj_0600.tok"));
		Assert.assertEquals(2, count);
		count = parser.parse(new File(rootPath + "file2.tok"));
		Assert.assertEquals(24, count);
		Assert.assertEquals(2, parser.docCount);
	}

}
