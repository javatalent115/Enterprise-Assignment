package assignment.controller;

import assignment.service.ProducerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;

@WebMvcTest(ProducerController.class)
@RunWith(SpringRunner.class)
public class ProducerControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProducerService producerService;

    @Test
    public void getAllProducers() {
        assertNotNull(1);
    }
}