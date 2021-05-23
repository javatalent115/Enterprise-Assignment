package assignment.service;

import assignment.controller.DrugController;
import assignment.entity.Drug;
import assignment.repository.DrugRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Transactional
@Service
public class DrugService {
    @Autowired
    DrugRepo drugRepo;
    private List<Drug> drugList;
    public void addDrug(Drug drug){
        this.drugRepo.save(drug);
    }

    public List<Drug> getAllDrugs(){
        drugList = this.drugRepo.findAll();
        drugList.sort(new Comparator<Drug>() {
            @Override
            public int compare(Drug drug1, Drug drug2)  {
                return drug1.getId().toLowerCase().compareTo(drug2.getId().toLowerCase());
            }
        });
        return drugList;
    }

    public void deleteByDrugId(String id) {
        this.drugRepo.deleteById(id);
    }

    public Drug getDrugById(String id) {
        Optional<Drug> result = this.drugRepo.findById(id);
        return result.orElse(null);
    }

//    public void updateDrugById(String id, Drug newDrug) {
//        getDrugById(id).replace(newDrug);
//    }
    public void quickUpdateDrug(String id, int money, int stock){
        Drug drug = getDrugById(id);
        drug.setMoney(money);
        drug.setStock(stock);
        this.drugRepo.save(drug);
    }

    public void reduceStock(String id, int amount){
        Drug drug = getDrugById(id);
        drug.setStock((drug.getStock()-amount));
        this.drugRepo.save(drug);
    }

    public void advanceUpdateDrug(Drug newData){
        Drug drug = getDrugById(newData.getId());
        drug.setStock(newData.getStock());
        drug.setMoney(newData.getMoney());
        drug.setCountry(newData.getCountry());
        drug.setDosage(newData.getDosage());
        drug.setDrugGroup(newData.getDrugGroup());
        drug.setIngredients(newData.getIngredients());
        drug.setPackaging(newData.getPackaging());
        drug.setPreparation(newData.getPreparation());
        drug.setType(newData.getType());
        this.drugRepo.save(drug);
    }

    public List<Drug> getDrugsByGroup(String group){
        List<Drug> result = new ArrayList<>();
        for (Drug drug : drugList) {
            if (drug.getDrugGroup().equals(group)) {
                result.add(drug);
            }
        }
        return result;
    }

    public List<Drug> getDrugsByType(String type){
        List<Drug> result = new ArrayList<>();
        for (Drug drug : drugList) {
            if (drug.getType().equals(type)) {
                result.add(drug);
            }
        }
        return result;
    }

    public List<Drug> getRelatedDrugs(String id) {
        Drug drug = getDrugById(id);
        List<Drug> list = new ArrayList<>();
        for (Drug d : drugList) {
            if (d.getProducer() == drug.getProducer() && !d.getId().equals(id)) {
                list.add(d);
            }
        }
        return list;
    }

//    public List<Drug> getRelatedDrugs(String id){
//        Drug drug = getDrugById(id);
//        return drug.getDrugsOfSameProducer();
//    }

    public List<Drug> getDrugsByFilter(String group, String type, String sortType){
        List<Drug> result;
        if (!group.equals("Both")){
            if (type.equals("Both")){
                result = getDrugsByGroup(group);
            }
            else {
                result = new ArrayList<>();
                for (Drug drug : drugList) {
                    if (drug.getType().equals(type) && drug.getDrugGroup().equals(group)) {
                        result.add(drug);
                    }
                }
            }
        }
        else {
            if (!type.equals("Both")) {
                result = getDrugsByType(type);
            }
            else{
                result = new ArrayList<>(drugList);
            }
        }
        switch (sortType) {
            case "Money (low - high)":
                Collections.sort(result);
                break;
            case "Money (high - low)":
                Collections.sort(result);
                Collections.reverse(result);
                break;
            case "Name (A-Z)":
                result.sort(new Comparator<Drug>() {
                    public int compare(Drug drug1, Drug drug2) {
                        return drug1.getName().toLowerCase().compareTo(drug2.getName().toLowerCase());
                    }
                });
                break;
            case "Name (Z-A)":
                result.sort(new Comparator<Drug>() {
                    @Override
                    public int compare(Drug drug1, Drug drug2) {
                        return drug1.getName().toLowerCase().compareTo(drug2.getName().toLowerCase());
                    }
                });
                Collections.reverse(result);
                break;
            default: break;
        }
        return result;
    }
}
