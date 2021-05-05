package main.java;
import main.java.entity.Drug;
import main.java.entity.Producers;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:8080")
public class ServerController {

    @PostMapping(value = "/api/getDrugs")
    public Map<String, String> getDrugs(){
        List list = Handler.getAllDrugs();
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i< list.size(); i++){
            Drug drug = (Drug) list.get(i);
            map.put(Integer.toString(i), drug.toString());
        }
        return map;
    }

    @PostMapping(value = "/api/deleteDrug")
    public Map<String, String> deleteDrug(@RequestBody String id){
        Map<String, String> map = new HashMap<>();
        map.put("0", (String) Handler.deleteDrug(id).get(0));
        return map;
    }

    @PostMapping(value = "/api/deleteProducer")
    public Map<String, String> deleteProducer(@RequestBody String id){
        Map<String, String> map = new HashMap<>();
        map.put("0", (String) Handler.deleteProducer(id).get(0));
        return map;
    }

    @PostMapping(value = "/api/updateDrug")
    public Map<String, String> updateDrug(@RequestBody String id, @RequestBody int money){
        Map<String, String> map = new HashMap<>();
        map.put("0", (String) Handler.updateDrugMoney(id, money).get(0));
        return map;
    }

    @PostMapping(value = "/api/getProducers")
    public Map<String, String> getProducers(){
        List list = Handler.getAllProducers();
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i< list.size(); i++){
            Producers producer = (Producers) list.get(i);
            map.put(Integer.toString(i), producer.toString());
        }
        return map;
    }

    @PostMapping(value = "/api/addDrug")
    public Map<String, String> addDrug(@RequestBody Drug drug) {
        Map<String, String> map = new HashMap<>();
        map.put("0", (String) Handler.saveDrug(drug).get(0));
        return map;
    }

    @PostMapping(value = "/api/addProducer")
    public Map<String, String> addProducer(@RequestBody Producers producer){
        Map<String, String> map = new HashMap<>();
        map.put("0", (String) Handler.saveProducer(producer).get(0));
        return map;
    }

    @PostMapping(value = "/api/getTypes")
    public Map<String, String> getAllTypes(){
        List list = Handler.getAllTypes();
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i< list.size(); i++){
            map.put(Integer.toString(i), (String) list.get(i));
        }
        return map;
    }

    @PostMapping(value = "/api/getGroups")
    public Map<String, String> getAllGroups(){
        List list = Handler.getAllGroups();
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i< list.size(); i++){
            map.put(Integer.toString(i), (String) list.get(i));
        }
        return map;
    }

    @PostMapping(value = "/api/sortDrugAsc")
    public Map<String, String> getDrugsSortAsc(){
        HashMap<String,String> map = new HashMap<>();
        List list =  Handler.getDrugSortAsc();
        for (int i = 0; i< list.size(); i++){
            Drug drug = (Drug) list.get(i);
            map.put(Integer.toString(i), drug.toString());
        }
        return map;
    }

    @PostMapping(value = "/api/sortDrugDes")
    public Map<String, String> getDrugsSortDes(){
        HashMap<String,String> map = new HashMap<>();
        List list =  Handler.getDrugSortDes();
        for (int i = 0; i< list.size(); i++){
            Drug drug = (Drug) list.get(i);
            map.put(Integer.toString(i), drug.toString());
        }
        return map;
    }

    @PostMapping(value = "/api/searchDrugsById")
    public Map<String, String> searchDrugsById(@RequestBody String id){
        HashMap<String,String> map = new HashMap<>();
        List list =  Handler.searchDrugsById(id);
        for (int i = 0; i< list.size(); i++){
            map.put(Integer.toString(i), (String) list.get(i));
        }
        return map;
    }

    @PostMapping(value = "/api/searchDrugsByName")
    public Map<String, String> searchDrugsByName(@RequestBody String name){
        HashMap<String,String> map = new HashMap<>();
        List list =  Handler.searchDrugsByName(name);
        for (int i = 0; i< list.size(); i++){
            map.put(Integer.toString(i), (String) list.get(i));
        }
        return map;
    }

    @PostMapping(value = "/api/getNOP")
    public Map<String, String> getNOP (@RequestBody String producerID){
        Map<String, String> map = new HashMap<>();
        map.put("0", (String) Handler.getNOP(producerID).get(0));
        return map;
    }

    @PostMapping(value = "/api/authUser")
    public Map<String, String> isValid(@RequestBody String username,@RequestBody String password){
        Map<String, String> map = new HashMap<>();
        if (username.equals("user123") && password.equals("user123")){
            map.put("0", "valid");
        }
        else if (username.equals("admin321") && password.equals("admin321")){
            map.put("0", "valid");
        }
        else map.put("0", "invalid");
        return map;
    }
}
