package assignment.service;

import assignment.entity.Producer;
import assignment.repository.ProducerRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = {ProducerService.class})
@RunWith(SpringRunner.class)
public class ProducerServiceTest {
    @MockBean
    ProducerRepo producerRepo;

    @Autowired
    ProducerService producerService;

    @Before
    public void init() {
        Producer producer1 = new Producer("HP", "Hoang");
        Producer producer2 = new Producer("BP", "Bao");

        Mockito.when(producerRepo.save(Mockito.any(Producer.class))).thenReturn(new Producer());

        List<Producer> producers = Arrays.asList(producer1, producer2);
        Mockito.when(producerRepo.findAll()).thenReturn(producers);

        Mockito.when(producerRepo.findById("HP")).thenReturn(Optional.of(producer1));
    }

    @Test
    public void addProducer() {
        Producer newProducer = new Producer("TP", "Thai's pharmacy");
        assertEquals("succeeded", producerService.addProducer(newProducer));

        Producer invalidProducer = new Producer(null, "The's pharmacy");
        assertEquals("failed", producerService.addProducer(invalidProducer));
    }

    @Test
    public void getAllProducers() {
        Producer producer1 = new Producer("HP", "Hoang");
        Producer producer2 = new Producer("BP", "Bao");
        List<Producer> producers = Arrays.asList(producer1, producer2);
        for (int i = 0; i < producers.size(); i++) {
            Producer found =  producerService.getAllProducers().get(i);
            assertEquals(producers.get(i).getId(), found.getId());
            assertEquals(producers.get(i).getName(), found.getName());
            assertEquals(producers.get(i).toString(), found.toString());
        }
    }

    @Test
    public void getProducerById() {
        assertNotNull(producerService.getProducerById("HP"));
        assertEquals("HP", producerService.getProducerById("HP").getId());
        assertEquals("Hoang", producerService.getProducerById("HP").getName());

        assertNull(producerService.getProducerById("BP"));
    }
}