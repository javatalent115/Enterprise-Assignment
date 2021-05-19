package assignment.controller;

import assignment.entity.Drug;
import assignment.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/drug")
public class DrugController {
    @Autowired
    private DrugService drugService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Drug> getAllDrugs(){
        return drugService.getAllDrugs();
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public void addDrug(@RequestBody Drug drug){
        drugService.addDrug(drug);
    }

//    @RequestMapping(path = "", method = RequestMethod.DELETE)
//    public void deleteAllDrugs(){
//        drugService.deleteAllDrug();
//    }

    @RequestMapping(path = "", method = RequestMethod.DELETE)
    public void deleteDrugById(@RequestBody String id) {
        drugService.deleteByDrugId(id);
    }

//    @RequestMapping(path = "", method = RequestMethod.PUT)
//    public void updateDrugById(@RequestBody Drug drug) {
//        drugService.updateDrugById(drug.getId(), drug);
//    }
//TODO update this put request

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Drug getDrugById(@PathVariable String id) {
        return drugService.getDrugById(id);
    }
}
