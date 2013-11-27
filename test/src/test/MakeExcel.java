package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;

//エクセルのオートフィルが使えないタイプの連続データを作成するのに使用
//いろいろな方法で試した
public class MakeExcel {
	
	/**
	 * @field 改行コード
	 */
	private static final String BR = System.getProperty("line.separator");
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		writeToTextFile();
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
	
	/**
	 * コンソールに表示して、コピペする方法
	 */
	public static void writeToConsole() {
		for(int i = 1; i <= 500; i++) {
			System.out.print("'\"1A1000014100001003");
			System.out.printf("%03d", i);
			System.out.println("N\"");
			
			System.out.print("'\"1A1000014100001003");
			System.out.printf("%03d", i);
			System.out.println("A\"");
		}
	}
	
	/**
	 * デスクトップにテキストファイルを作成する方法
	 * PrintWriterで書き込む
	 */
	public static void writeToTextFile() {
		File file = new File("C:/Users/r-aoki/Desktop/temp.txt");
		PrintWriter writer = null;
		try {
			FileOutputStream fos = new FileOutputStream(file);
//			OutputStreamWriter osw = new OutputStreamWriter(fos);
			writer = new PrintWriter(fos);
			StringBuilder sb = new StringBuilder();
			DecimalFormat format = new DecimalFormat("000");
			
			for(int i = 1; i <= 500; i++) {
				sb.append("\"1A1000014100001003");
				sb.append(format.format(i));
				sb.append("N\"" + BR);
			}
			
			writer.write(sb.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}	
	}
	
	/**
	 * OutputStreamWriterとかよくわからないので、簡単なバージョン
	 * FileWriterで書き込む
	 */
	public static void writeToTextFile2() {
		File file = new File("C:/Users/r-aoki/Desktop/temp.txt");
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
			StringBuilder sb = new StringBuilder();
			DecimalFormat format = new DecimalFormat("000");
			
			for(int i = 1; i <= 500; i++) {
				sb.append("\"1A1000014100001003");
				sb.append(format.format(i));
				sb.append("A\"" + BR);
			}
			
			fw.write(sb.toString());
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * PrintWriterのprintf()メソッドを利用する
	 * StringBuilderを介さない方法
	 */
	public static void writeToTextFile3() {
		File file = new File("C:/Users/r-aoki/Desktop/temp.txt");
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(file);
			
			for(int i = 1; i <= 500; i++) {
				writer.print("\"1A1000014100001003");
				writer.printf("%03d", i);
				writer.println("N\"");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}	
	}
	
}
