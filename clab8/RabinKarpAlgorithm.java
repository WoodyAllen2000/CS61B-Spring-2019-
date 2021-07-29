public class RabinKarpAlgorithm {
    /**
     * This algorithm returns the starting index of the matching substring.
     * This method will return -1 if no matching substring is found, or if the input is invalid.
     */
    public static int rabinKarp(String input, String pattern) {
        if (input.length() < pattern.length()) {
            return -1;
        }
        RollingString inputString = new RollingString(input.substring(0, pattern.length()), pattern.length());
        RollingString patternString = new RollingString(pattern, pattern.length());
        int perfectCode = patternString.hashCode();
        for (int i = pattern.length(); i < input.length(); i++) {
            if (inputString.hashCode() == perfectCode) {
                return i - pattern.length();
            }
            inputString.addChar(input.charAt(i));
        }
        return -1;
    }

}
