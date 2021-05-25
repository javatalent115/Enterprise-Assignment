package assignment.service;

import assignment.entity.Drug;
import assignment.entity.Producer;
import assignment.repository.DrugRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.*;

@SpringBootTest(classes = {DrugService.class})
@RunWith(SpringRunner.class)
public class DrugServiceTest {
    @MockBean
    DrugRepo drugRepo;

    @Autowired
    DrugService drugService;

    @Before
    public void init() {
        Drug drug1 = new Drug("d1", "d1", "d1", "d1", "d1", "d1", "d1", "d1", 10, 10, new Producer("p1", "p1"), "d1");
        Drug drug2 = new Drug("d2", "d2", "d2", "d2", "d2", "d2", "d2", "d2", 10, 10, new Producer("p1", "p1"), "d2");
        Drug drug3 = new Drug("d3", "d3", "d3", "d3", "d1", "d3", "d1", "d3", 10, 10, new Producer("p2", "p2"), "d3");
        List<Drug> drugs = new ArrayList<>();
        drugs.add(drug1);
        drugs.add(drug2);
        drugs.add(drug3);

        Mockito.when(drugRepo.save(Mockito.any(Drug.class))).thenReturn(new Drug());

        Mockito.when(drugRepo.findAll()).thenReturn(drugs);

        Mockito.when(drugRepo.findById("d1")).thenReturn(Optional.of(drug1));
    }

    @Test
    public void addDrug() {
        assertNotNull(drugRepo.save(new Drug()));
    }

    @Test
    public void getAllDrugs() {
    }

    @Test
    public void deleteByDrugId() {
    }

    @Test
    public void getDrugById() {
        Drug drug1 = new Drug("d1", "d1", "d1", "d1", "d1", "d1", "d1", "d1", 10, 10, new Producer("p1", "p1"), "d1");
        Drug found =  drugService.getDrugById("d1");
        Drug notfound =  drugService.getDrugById("d3");

        assertNotNull(found);
        assertNull(notfound);

        assertEquals(drug1.getId(), found.getId());
        assertEquals(drug1.getName(), found.getName());
        assertEquals(drug1.getPreparation(), found.getPreparation());
        assertEquals(drug1.getPackaging(), found.getPackaging());
        assertEquals(drug1.getDrugGroup(), found.getDrugGroup());
        assertEquals(drug1.getDosage(), found.getDosage());
        assertEquals(drug1.getMoney(), found.getMoney());
        assertEquals(drug1.getStock(), found.getStock());
        assertEquals(drug1.getProducer().getId(), found.getProducer().getId());
        assertEquals(drug1.getCountry(), found.getCountry());
    }

    @Test
    public void quickUpdateDrug() {
        Drug found = drugService.getDrugById("d1");
        drugService.quickUpdateDrug("d1", 10, 20);
        Drug refound =  drugService.getDrugById("d1");

        assertEquals(refound.getMoney(), found.getMoney());
        assertEquals(refound.getStock(), found.getStock());
    }

    @Test
    public void reduceStock() {
        Drug found = drugService.getDrugById("d1");
        int oldStock = found.getStock();
        drugService.reduceStock("d1", 1000);
        Drug refound = drugService.getDrugById("d1");
        int newStock = refound.getStock();
        assertEquals(1000, oldStock - newStock);
    }

    @Test
    public void getDrugListByProducer() {
        Drug drug1 = new Drug("d1", "d1", "d1", "d1", "d1", "d1", "d1", "d1", 10, 10, new Producer("p1", "p1"), "d1");
        Drug drug2 = new Drug("d2", "d2", "d2", "d2", "d2", "d2", "d2", "d2", 10, 10, new Producer("p1", "p1"), "d2");
        drugService.getAllDrugs();
        List<Drug> mock = Arrays.asList(drug1, drug2);
        List<String> drugString = drugService.getDrugListByProducer("p1");

        assertEquals(mock.size(), drugString.size());
        assertNull(drugService.getDrugById("p3"));
        for (int i = 0; i < drugString.size(); i++) {
            assertEquals(mock.get(i).getId()+"---"+mock.get(i).getName(), drugString.get(i));
        }
    }

    @Test
    public void advanceUpdateDrug() {
        Drug newDrug = new Drug("d1", "d1", "d2", "d2", "d2", "d2", "d2", "d2", 10, 10, new Producer("p1", "p1"), "d2");
        drugService.advanceUpdateDrug(newDrug);
        Drug refound =  drugService.getDrugById("d1");

        assertEquals(newDrug.getId(), refound.getId());
        assertEquals(newDrug.getName(), refound.getName());

        assertEquals(newDrug.getPreparation(), refound.getPreparation());
        assertEquals(newDrug.getPackaging(), refound.getPackaging());
        assertEquals(newDrug.getDrugGroup(), refound.getDrugGroup());
        assertEquals(newDrug.getDosage(), refound.getDosage());
        assertEquals(newDrug.getMoney(), refound.getMoney());
        assertEquals(newDrug.getStock(), refound.getStock());
        assertEquals(newDrug.getProducer().getId(), refound.getProducer().getId());
        assertEquals(newDrug.getCountry(), refound.getCountry());
    }

    @Test
    public void getDrugsByGroup() {
        Drug drug1 = new Drug("d1", "d1", "d1", "d1", "d1", "d1", "d1", "d1", 10, 10, new Producer("p1", "p1"), "d1");
        Drug drug3 = new Drug("d3", "d3", "d3", "d3", "d1", "d3", "d1", "d3", 10, 10, new Producer("p2", "p2"), "d3");
        drugService.getAllDrugs();
        List<Drug> mock = Arrays.asList(drug1, drug3);
        List<Drug> drugs = drugService.getDrugsByGroup("d1");

        assertEquals(mock.size(), drugs.size());
        assertEquals(0, drugService.getDrugsByGroup("d4").size());
        for (int i = 0; i < drugs.size(); i++) {
            assertEquals(mock.get(i).getId(), drugs.get(i).getId());
            assertEquals(mock.get(i).getName(), drugs.get(i).getName());
            assertEquals(mock.get(i).getPreparation(), drugs.get(i).getPreparation());
            assertEquals(mock.get(i).getPackaging(), drugs.get(i).getPackaging());
            assertEquals(mock.get(i).getDrugGroup(), drugs.get(i).getDrugGroup());
            assertEquals(mock.get(i).getDosage(), drugs.get(i).getDosage());
            assertEquals(mock.get(i).getMoney(), drugs.get(i).getMoney());
            assertEquals(mock.get(i).getStock(), drugs.get(i).getStock());
            assertEquals(mock.get(i).getProducer().getId(), drugs.get(i).getProducer().getId());
            assertEquals(mock.get(i).getCountry(), drugs.get(i).getCountry());
        }
    }

    @Test
    public void getDrugsByType() {
        Drug drug1 = new Drug("d1", "d1", "d1", "d1", "d1", "d1", "d1", "d1", 10, 10, new Producer("p1", "p1"), "d1");
        Drug drug3 = new Drug("d3", "d3", "d3", "d3", "d1", "d3", "d1", "d3", 10, 10, new Producer("p2", "p2"), "d3");
        drugService.getAllDrugs();
        List<Drug> mock = Arrays.asList(drug1, drug3);
        List<Drug> drugs = drugService.getDrugsByType("d1");

        assertEquals(mock.size(), drugs.size());
        assertEquals(0, drugService.getDrugsByType("d4").size());
        for (int i = 0; i < drugs.size(); i++) {
            assertEquals(mock.get(i).getId(), drugs.get(i).getId());
            assertEquals(mock.get(i).getName(), drugs.get(i).getName());
            assertEquals(mock.get(i).getPreparation(), drugs.get(i).getPreparation());
            assertEquals(mock.get(i).getPackaging(), drugs.get(i).getPackaging());
            assertEquals(mock.get(i).getDrugGroup(), drugs.get(i).getDrugGroup());
            assertEquals(mock.get(i).getDosage(), drugs.get(i).getDosage());
            assertEquals(mock.get(i).getMoney(), drugs.get(i).getMoney());
            assertEquals(mock.get(i).getStock(), drugs.get(i).getStock());
            assertEquals(mock.get(i).getProducer().getId(), drugs.get(i).getProducer().getId());
            assertEquals(mock.get(i).getCountry(), drugs.get(i).getCountry());
        }
    }

    @Test
    public void getRelatedDrugs() {
        Drug drug1 = new Drug("d1", "d1", "d1", "d1", "d1", "d1", "d1", "d1", 10, 10, new Producer("p1", "p1"), "d1");
        Drug drug2 = new Drug("d2", "d2", "d2", "d2", "d2", "d2", "d2", "d2", 10, 10, new Producer("p1", "p1"), "d2");
        drugService.getAllDrugs();
        List<Drug> mock = Collections.singletonList(drug2);
        List<Drug> drugs = drugService.getRelatedDrugs("d1");
        for (Drug drug : drugs) {
            assertEquals(drug1.getProducer().getId(), drug.getProducer().getId());
            assertNotEquals(drug1.getId(), drug.getId());
        }
    }

    @Test
    public void getDrugsByFilter() {

    }
}