public class StringUtil {

    /**
     * 将字符串设置为驼峰命名
     */
    public static String transStringToHump(String target){
        StringBuilder result = new StringBuilder();
        boolean lowerFlag = true;

        for (char c: target.toCharArray()){
            if ("_".equals(String.valueOf(c))){
                // '_'忽略，且下一个字母保持大小
                lowerFlag = false;
                continue;
            }
            if (lowerFlag){
                // 转换为小写
                result.append(lowerChar(c));
            }else {
                // 保持大写，并下一个字母依然要转换为小写
                lowerFlag = true;
                result.append(c);
            }
        }

        return result.toString();
    }

    private static char lowerChar(char c){
        if ((c >= 'A') && (c <= 'Z')){
            return c += 32;
        }else {
            return c;
        }
    }

    public static void main(String[] args) {
        String testStr = "ROOM_ID_TesT";
        String resultStr = transStringToHump(testStr);

        System.out.println("原字符串；" + testStr + "转换为：" + resultStr);
    }
}
