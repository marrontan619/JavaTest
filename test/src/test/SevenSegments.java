package test;


public class SevenSegments {
    
    private static final byte zero = 0x7E; // 1111110
    private static final byte one = 0x30; // 0110000
    private static final byte two = 0x6d; // 1101101
    private static final byte three = 0x79; // 1111001
    private static final byte four = 0x33; // 0110011
    private static final byte five = 0x5b; // 1011011
    private static final byte six = 0x5f; // 1011111
    private static final byte seven = 0x70; // 1110000
    private static final byte eight = 0x7f; // 1111111
    private static final byte nine = 0x7b; // 1111011
    
    
    public static void main(String[] args) {
        System.out.println(Integer.toString(zero ^ one, 2));
    }
    
    /**
     * @param current
     * @param next
     * @return 切り替える数
     */
    public static int diffCheck(byte current, byte next) {
        String diffHex = Integer.toString(current ^ next, 2);
        int count = 0;
        for (int i = 0; i < 7; i++) {
            if ('1' == diffHex.charAt(i)) {
                count++;
            }
        }
        return count;
    }
    

}
