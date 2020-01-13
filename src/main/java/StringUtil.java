import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

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
                // 转换为大写，并下一个字母依然要转换为小写
                lowerFlag = true;
                result.append(upperChar(c));
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

    private static char upperChar(char c){
        if ((c >= 'a') && (c <= 'z')){
            return c -= 32;
        }else {
            return c;
        }
    }

    public static String replaceString(String target, int indexStart, int indexEnd, String replaceStr){
        StringBuilder result = new StringBuilder(target);
        result.replace(indexStart, indexEnd, replaceStr);
        return result.toString();
    }


    public static void main(String[] args) {
        String testStr = "STRUCT{ROOM_ID_TesT: 1, ROOM_NamE: \"test_DA\"}";
        //1. 参数名转换为驼峰命名法
        String resultStr = transStringToHump(testStr);

        //2. 替换部分参数
//        resultStr  = resultStr.replace("struct" ,"msgContent:");
        resultStr = replaceString(resultStr, 0, 6, "msgContent:");
        System.out.println("原字符串；" + testStr + "转换为：" + resultStr);

        //3. 处理字符串格式
        StringBuilder tempStr = new StringBuilder(resultStr);
        tempStr.append(",msgType: 1");
        tempStr.insert(0, '{');
        tempStr.append('}');
        System.out.println( "转换的对象为：" + tempStr);

        JSONObject messageObj = JSON.parseObject(tempStr.toString());
        String result = messageObj.getString("msgContent");
        System.out.println( "提取的对象为：" + result);
    }
}
