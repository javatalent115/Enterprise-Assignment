package assignment.controller;

import assignment.entity.Drug;
import assignment.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/drug")
public class DrugController {
    @Autowired
    private DrugService drugService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public Map<String, String> getAllDrugs(){
        List<Drug> list =drugService.getAllDrugs();
        Map <String, String> map = new HashMap<>();
        for (int i = 0 ; i< list.size(); i++){
            Drug drug = list.get(i);
            map.put(Integer.toString(i), drug.toString());
        }
        return map;
    }

    @RequestMapping(path = "/addDrug", method = RequestMethod.POST)
    public String addDrug(@RequestBody Drug drug){
        try {
            drugService.addDrug(drug);
        }catch (Exception e){
            return "failed";
        }
        return "success";
    }

    @RequestMapping(path = "/getRelation", method = RequestMethod.POST)
    public Map<String, String> getRelatedDrugs(@RequestBody String id){
        List<Drug> list = drugService.getRelatedDrugs(id);
        Map <String, String> map = new HashMap<>();
        for (int i = 0 ; i< list.size(); i++){
            map.put(Integer.toString(i), list.get(i).toString());
        }
        return map;
    }

    @RequestMapping(path = "", method = RequestMethod.DELETE)
    public void deleteDrugById(@RequestBody String id) {
        drugService.deleteByDrugId(id);
    }

    @RequestMapping(path = "", method = RequestMethod.PUT)
    public void updateDrugById(@RequestBody Map<String, String> data) {
        drugService.updateDrugById(data.get("id"), Integer.parseInt(data.get("money")), Integer.parseInt(data.get("stock")));
    }

    @PostMapping(value = "/getDrugsByFilter")
    public Map<String, String> getDrugsByFilter(@RequestBody Map<String, String> data){
        List<Drug> list = drugService.getDrugsByFilter(data.get("group"), data.get("type"), data.get("sortType"));
        Map <String, String> map = new HashMap<>();
        for (int i = 0 ; i< list.size(); i++){
            map.put(Integer.toString(i), list.get(i).toString());
        }
        return map;
    }
}
