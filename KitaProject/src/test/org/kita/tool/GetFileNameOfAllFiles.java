package org.kita.tool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/*
 * Print path/filename of all files in a folder and sub-folder
 * */

public class GetFileNameOfAllFiles {
	public static void main(String[] args) throws FileNotFoundException {

		// Export console to a file
		PrintStream out = new PrintStream(
				new FileOutputStream("D:\\output.txt"));
		System.setOut(out);

		// Folder path
		listFile("D:\\Production\\ProductImage");
	}

	public static void listFile(String pathname) {
		File f = new File(pathname);
		File[] listfiles = f.listFiles();
		for (int i = 0; i < listfiles.length; i++) {
			if (listfiles[i].isDirectory()) {
				File[] internalFile = listfiles[i].listFiles();
				for (int j = 0; j < internalFile.length; j++) {
					System.out.println(internalFile[j]);
					if (internalFile[j].isDirectory()) {
						String name = internalFile[j].getAbsolutePath();
						listFile(name);
					}
				}
			} else {
				System.out.println(listfiles[i]);
			}
		}
	}
}