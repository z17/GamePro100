package user;


import java.util.Random;

public class Utils {
    public static String randomString(int length) {
        final char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
        final StringBuilder sb = new StringBuilder();
        final Random random = new Random();
        for (int i = 0; i < length; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }}
