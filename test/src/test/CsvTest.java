package test;

//eとgの間のあれが、要素数としてカウントされるのか
//結果：Yes
public class CsvTest {
	
	public static void main(String[] args) {
		String str = "a, b, c, d, e,, g, h, i, j";
		String[] s = str.split(",");
		System.out.println(s.length);
	}

}
