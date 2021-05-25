package assignment;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import assignment.service.*;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        OrderDetailServiceTest.class,
        ProducerServiceTest.class,
        DrugServiceTest.class,
        OrderServiceTest.class,
        CustomerServiceTest.class
})

public class ServiceTest {
}