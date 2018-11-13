package ant.kitchens.utils;

import java.util.Random;

/**
 * 生成唯一的主键.
 * Created by wolf   2018/10/24
 */
public class KeyUtil {
    //格式: 时间+随机数
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
