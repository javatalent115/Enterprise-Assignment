package assignment;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import assignment.controller.*;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        OrderDetailControllerTest.class,
        ProducerControllerTest.class,
        DrugControllerTest.class,
        OrderControllerTest.class,
        CustomerControllerTest.class,
        ControllerTest.class
})

public class AllControllerTest {
}
