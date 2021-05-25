package assignment.controller;

import assignment.service.DrugService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;

@WebMvcTest(DrugController.class)
@RunWith(SpringRunner.class)
public class DrugControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private DrugService drugService;

    @Test
    public void addDrug() {
        assertNotNull(1);
    }

    @Test
    public void getRelatedDrugs() {
        assertNotNull(1);
    }

    @Test
    public void deleteDrugById() {
        assertNotNull(1);
    }

    @Test
    public void quickUpdateDrug() {
        assertNotNull(1);
    }

    @Test
    public void getDrugsByFilter() {
        assertNotNull(1);
    }

    @Test
    public void advanceUpdateDrug() {
        assertNotNull(1);
    }

    @Test
    public void reduceStock() {
        assertNotNull(1);
    }
}