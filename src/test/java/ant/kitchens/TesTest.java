package ant.kitchens;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by wolf   2018/10/22
 */
@Slf4j
public class TesTest {

    private static HashMap<Integer,List<Integer>> su = new HashMap<>();

    public static void init(){
        su.put(1, Arrays.asList(6,8));su.put(2,Arrays.asList(7,9));
        su.put(3,Arrays.asList(4,8));su.put(4,Arrays.asList(3,9,0));
        su.put(6,Arrays.asList(1,7,0));su.put(7,Arrays.asList(2,6));
        su.put(8,Arrays.asList(1,3));su.put(9,Arrays.asList(2,4));
        su.put(0,Arrays.asList(4,6));
    }
        // 0   3   9
    public static void main(String[] args) {
        init();
        log.info("result:{}",deg(4).toString());
    }

    public static Integer deg(Integer n){

        int flag = 1,location = 0,count = 1;

        while ( n>=1 ){
            if(n==1){
                return count=10*count;
            }

            if(location==0){
                count= count*2*2;
                location= su.get(0).get(1);
            }

            else if(location==4){
                count= count*2*2;
                location= su.get(0).get(1);
            }

            else if(location==6){
                count=7*2*count;
            }

            else {
                count=7*2*count;
            }
            n--;
       }
        return  count;
    }


}
