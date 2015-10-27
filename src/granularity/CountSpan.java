package granularity;

import java.io.File;
import java.io.FilenameFilter;

public class CountSpan {
	
	public static final String path = "D:/Data/RST-DT/ALL";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		FilenameFilter fileFilter = new FilenameFilter() {
			public boolean accept(File dir, String name){
				return name.toLowerCase().endsWith(".dis");
			}
		};
		
		File dir = new File(path);
		File[] trees = dir.listFiles(fileFilter);
		
//		for(File file: trees){
//			System.out.println(file.getName());
//		}
//		System.out.println(trees.length);
		
	}

}
