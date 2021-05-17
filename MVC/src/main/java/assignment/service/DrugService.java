package assignment.service;

import assignment.entity.Drug;
import assignment.repository.DrugRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class DrugService {
    @Autowired
    DrugRepo drugRepo;

    public void addDrug(Drug drug){
        this.drugRepo.save(drug);
    }

    public List<Drug> getAllDrugs(){
        return this.drugRepo.findAll();
    }

    public void deleteByDrugId(String id) {
        this.drugRepo.deleteById(id);
    }

    public Drug getDrugById(String id) {
        Optional<Drug> result = this.drugRepo.findById(id);
        return result.orElse(null);
    }

    public void updateDrugById(String id, Drug newDrug) {
        getDrugById(id).replace(newDrug);
    }

    public long countDrug() {
        return this.drugRepo.count();
    }

    public void deleteAllDrug() {
        this.drugRepo.deleteAll();
    }
}
