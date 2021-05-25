import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import assignment.*;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        AllControllerTest.class,
        ServiceTest.class
})

public class UnitTest {
}
