package test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrBuilder;

public class LoadTargetMaker {
    
    private final static String dQuart = "\"";
    private final static String separator = ",";
    
    private static String myFolderPath = "./";
    
    private final static String[] dayCode = {dQuart + "SUN" + dQuart,
                                             dQuart + "MON" + dQuart,
                                             dQuart + "TUE" + dQuart,
                                             dQuart + "WED" + dQuart,
                                             dQuart + "THU" + dQuart,
                                             dQuart + "FRI" + dQuart,
                                             dQuart + "SAT" + dQuart};
    private final static String extention = ".xml" + dQuart;
    private final static String[] price = {"100", "200", "300", "400", "500", "600", "700", "800"};
    private final static String nullCol = "";
    private final static String threeLetterCode = "AAA";
    private final static String flagY = "1";
    private final static String flagN = "0";
    private final static String createrName = dQuart + "General User" + dQuart;
    private final static String timeStamp = "2013/12/26";
    private final static String createrId = "-1";
    
    private static Calendar day = Calendar.getInstance();
    private final static DecimalFormat df = new DecimalFormat("00");   
    
    static String[] line = new String[19];
    
    static {
        line[11] = threeLetterCode;
        line[12] = flagY;
        line[13] = flagN;
        line[14] = createrName;
        line[15] = timeStamp;
        line[16] = createrId;
        line[17] = nullCol;
        line[18] = nullCol;
    }
    
    /**
     * ラッシュテスト・負荷テストなどに使う多量のDBデータ作成用メソッドのアーキタイプ
     * データ内容はある程度決め打ち
     * @param fileName
     * @param start 
     * @param end 
     * 現状は (end - start) * 29200 行のレコードが、1回の実行で作成される
     */
    public static void makeLoadTarget (String fileName, int start, int end) {       
        StrBuilder sb = new StrBuilder();
        for (int sequenceA = start; sequenceA <= end; sequenceA += 3) {
            line[0] = Integer.toString(sequenceA);
            
            for (int sequenceB = 1; sequenceB <= 10; sequenceB++) {
                day.set(2013, 3, 1);
                line[1] = Integer.toString(sequenceB);
                line[5] = dQuart + RandomStringUtils.randomAlphabetic(5) + extention;

                for (int sequenceC = 1; sequenceC <= 2920;) {
                    line[3] = dQuart +
                              day.get(Calendar.YEAR) +
                              df.format(day.get(Calendar.MONTH) + 1) +
                              df.format(day.get(Calendar.DATE)) +
                              dQuart;
                    line[4] = dayCode[(day.get(Calendar.DAY_OF_WEEK) - 1)];
                    
                    for (int priceRate = 1; priceRate <= 8; priceRate++) {
                        line[2] = Integer.toString(sequenceC++);
                        line[6] = Integer.toString(priceRate);
                        line[7] = price[priceRate - 1];
                        line[8] = price[priceRate - 1];
                        line[9] = price[priceRate - 1];
                        line[10] = price[priceRate - 1];
                        
                        sb.appendWithSeparators(line, separator);
                        sb.appendNewLine();
                    }
                    day.add(Calendar.DATE, 1);
                }
            }
            File file = new File(myFolderPath + fileName);
            FileWriter fw = null;
            try {
                fw = new FileWriter(file, true);
                fw.append(sb.toString());
                fw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fw != null) {
                    try {
                        fw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            // メモリ対策。sequenceAの単位（29200行）で書き出し→クリア
            sb.clear();
        }
        
        // ファイルを分割で作成するとき、次のstartをひと目でわかるようにプリント
        System.out.println(StringUtils.join(line, separator));
        
    }    
    
    public static void main(String[] args) {
        LoadTargetMaker.makeLoadTarget("temp.csv", 1, 1);
    }
}
