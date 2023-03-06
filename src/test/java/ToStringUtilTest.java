import common.ToStringUtil;
import org.junit.Test;

import java.util.ArrayList;

public class ToStringUtilTest {

    @Test
    public void toStringTest(){
        ToStringUtil toStringUtil = new ToStringUtil();
        int[] ints = {1, 2, 3, 4, 5, 6};
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            integers.add(i);
        }
        try {
            System.out.println(toStringUtil.toString(ints));
            System.out.println("-----------------------------------------");
            System.out.println(toStringUtil.toString(integers));
            System.out.println("-----------------------------------------");
            System.out.println(toStringUtil.toString(toStringUtil));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
