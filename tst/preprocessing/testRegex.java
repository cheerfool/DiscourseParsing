package preprocessing;

import static org.junit.Assert.*;

import org.junit.Test;

public class testRegex {

	@Test
	public void test() {
		String text = "<en=4>A form of asbestos once used to make Kent cigarette filters has caused a high percentage of cancer deaths among a group of workers exposed to it more than 30 years ago , researchers reported .";
		System.out.println(text.replaceAll("<en=\\d+>", ""));
		text = "``A form of asbestos once used to make'' Kent cigarette filters ";
		System.out.println(text.replaceAll("``|''", ""));
		text = "A form of asbestos once used to make Kent's cigarette 's filters ";
		System.out.println(text.replaceAll("'", " '"));
		text = "cheer@gmail.com .";
		System.out.println(text.replaceAll("@", " at "));
		text = "A form of asbestos once used to make Kent's cigarette 's filters Corp. .";
		System.out.println(text.replaceAll("\\. +\\.$", "\\.\\."));
		text = "cheer@gmail.com ..... .";
		System.out.println(text.replaceAll("\\.{3,}", ","));
		text = "cheer@gmail.com :";
		System.out.println(text.replaceAll(":$", "."));
		text = "{cheer@gmail.com q}:";
		System.out.println(text.replaceAll("[{}]", ""));
		System.out.println("Done");
	}

}
