package test;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 適当に何か貼り付けて実行するクラス
 * 内容はその時々で変わる
 * @author r-aoki
 */
public class Hello {
  public static void main(String[] args) throws ParseException {
	Pattern pattern = Pattern.compile("[\\-\\-s]");
	Matcher matcher = pattern.matcher("\n");
	
	System.out.println(matcher.matches());
  }
}