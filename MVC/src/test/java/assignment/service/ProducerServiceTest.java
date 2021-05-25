package assignment.service;

import assignment.entity.Producer;
import assignment.repository.ProducerRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

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

        List<Producer> producers = new ArrayList<>();
        producers.add(producer1);
        producers.add(producer2);
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
        List<Producer> mock = Arrays.asList(producer1, producer2);
        List<Producer> producers = producerService.getAllProducers();

        assertEquals(mock.size(), producers.size());

        for (int i = 0; i < mock.size(); i++) {
            assertEquals(mock.get(i).getId(), producers.get(i).getId());
            assertEquals(mock.get(i).getName(), producers.get(i).getName());
        }
    }

    @Test
    public void getProducerById() {
        Producer producer1 = new Producer("HP", "Hoang");
        Producer found = producerService.getProducerById("HP");
        Producer notfound = producerService.getProducerById("BP");

        assertNotNull(found);
        assertNull(notfound);

        assertEquals(producer1.getId(), found.getId());
        assertEquals(producer1.getName(), found.getName());
        assertNull(producerService.getProducerById("BP"));
    }
}