package assignment.controller;

import assignment.entity.Drug;
import assignment.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/drug")
public class DrugController {//TODO change Map to List if there is more time
    @Autowired
    private DrugService drugService;
    private boolean initial = true;

    @RequestMapping(path = "/addDrug", method = RequestMethod.POST)
    public String addDrug(@RequestBody Drug drug){
        try {
            drugService.addDrug(drug);
        }catch (Exception e){
            return "failed";
        }
        drugService.getAllDrugs();
        return "success";
    }

    @RequestMapping(path = "/getRelatedDrugs", method = RequestMethod.POST)
    public Map<String, String> getRelatedDrugs(@RequestBody String id){
        List<Drug> list = drugService.getRelatedDrugs(id);
        Map <String, String> map = new HashMap<>();
        for (int i = 0 ; i< list.size(); i++){
            map.put(Integer.toString(i), list.get(i).toString());
        }
        return map;
    } //TODO change this one

    @RequestMapping(path = "/deleteDrug", method = RequestMethod.DELETE)
    public void deleteDrugById(@RequestBody String id) {
        drugService.deleteByDrugId(id);
        drugService.getAllDrugs();
    }

    @RequestMapping(path = "/updateDrug", method = RequestMethod.PUT)
    public void quickUpdateDrug(@RequestBody Map<String, String> data) {
        drugService.quickUpdateDrug(data.get("id"), Integer.parseInt(data.get("money")), Integer.parseInt(data.get("stock")));
        drugService.getAllDrugs();
    }

    @RequestMapping(value = "/getDrugsByFilter", method = RequestMethod.POST)
    public Map<String, String> getDrugsByFilter(@RequestBody Map<String, String> data){
        if (initial){
            drugService.getAllDrugs();
            initial = false;
        }
        List<Drug> list = drugService.getDrugsByFilter(data.get("group"), data.get("type"), data.get("sortType"));
        Map <String, String> map = new HashMap<>();
        for (int i = 0 ; i< list.size(); i++){
            map.put(Integer.toString(i), list.get(i).toString());
        }
        return map;
    }

    @RequestMapping(path = "/advanceUpdateDrug", method = RequestMethod.PUT)
    public void advanceUpdateDrug(@RequestBody Drug drug) {
        drugService.advanceUpdateDrug(drug);
        drugService.getAllDrugs();
    }
}
