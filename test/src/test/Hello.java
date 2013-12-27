package test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 適当に何か貼り付けて実行するクラス
 * 内容はその時々で変わる
 * 現状:eqlipseとの連携を確認するために修正
 * @author r-aoki
 */
public class Hello {
  public static void main(String[] args) throws ParseException {
      cal(2013, 2, 2);
  }
  
  /**
   * 日曜日=1 ～ 土曜日=7 だった
   * @param year
   * @param month
   * @param date
   */
  public static void cal(int year, int month, int date) {
      Calendar calendar = Calendar.getInstance();
      calendar.set(year, month, date);
      System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
  }
}