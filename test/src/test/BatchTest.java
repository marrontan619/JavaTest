package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

//ファイルの扱いの練習
//大体はサンプル
public class BatchTest {
	private int rowCount;

	public static void main(String[] args) {
		BatchTest batchTest = new BatchTest();
		String parentFolderPath = "../test/request";
		ArrayList<String> csvFileList = new ArrayList<String>();
		csvFileList = batchTest.getCsvFileList(parentFolderPath);
		for (String csvFile : csvFileList) {
			System.out.println(csvFile);
		}
		for (String srcPath: csvFileList) {
			batchTest.writeCsvFile(srcPath);
		}
		
		
	}

	/**
	 * @param folderPath
	 * @return csvFileList
	 * folderPathの配下にある"XXXX.csv"のパスのリストを返す
	 */
	private ArrayList<String> getCsvFileList(String folderPath) {
		File parentFolder = new File(folderPath);
		File[] childrenFiles = parentFolder.listFiles();
		ArrayList<String> fileList = new ArrayList<String>();
		if (childrenFiles != null && childrenFiles.length > 0) {
			int fileCount = childrenFiles.length;
			for (int i = 0; i < fileCount; i++) {
				if(childrenFiles[i].getName().endsWith(".csv") &&
				   !childrenFiles[i].getName().endsWith("Result.csv")
				){
					fileList.add(childrenFiles[i].getPath());
				}
			}
		}
		return fileList;
	}
	
	/**
	 * @param srcFile
	 * @return void
	 * 1ファイル分の処理を行うメソッド
	 */
	private void writeCsvFile(String srcFile){
		rowCount = 0;
		String newFileName = srcFile.substring(0, srcFile.length()-4) + "Result.csv";
		File resultFile = new File(newFileName);
		if(!resultFile.exists()){
			try {
				resultFile.createNewFile();
				System.out.println("created new file. " + newFileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		FileWriter fw = null;
		try {
			fw = new FileWriter(resultFile);
			br = new BufferedReader(new FileReader(srcFile));
			String line = "";
			//1行分書き出しのループ
			while((line = br.readLine()) != null){
				sb.delete(0, sb.length());
				line = line.replace("\"", "");
				String[] lineData = line.split(",");
				if(!lineData[0].equals("E")){
					for(int i = lineData.length-1; i > 0; i--){
						sb.append(lineData[i] + ","); 
					}
					sb.append(lineData[0] + "\n");
					fw.write(sb.toString());
					rowCount++;
				}else{
					break;
				}
			}
			fw.write("\"E\"," + rowCount);
			fw.close();
			br.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			br = null;
			fw = null;
		}
		resultMove(newFileName, "../test/response");
	}
	
	/**
	 * @param 元のファイルパス
	 * @param 移動先
	 */
	public void resultMove(String srcFilePath, String destFilePath){
		File file = new File(srcFilePath);
		String fileName = file.getName();
		try{
			file.renameTo(new File(destFilePath, fileName));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
