import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class Megatest {
    @Test
    public void test1(){
        assertTrue(Main.luckyTicket("111111"));
    }
    @Test
    public void test2(){
        assertTrue(Main.luckyTicket("-100000"));
    }


}
