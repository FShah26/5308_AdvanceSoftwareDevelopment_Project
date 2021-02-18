import com.group9.client.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MainTests {
    @Test
    @DisplayName("HelloTest")
    public void HelloTest(){
        Assertions.assertEquals(Main.hello("hi"), "hi");
    }
}
