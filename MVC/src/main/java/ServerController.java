package main.java;
import main.java.entity.Drug;
import main.java.entity.Producers;
import main.java.service.Handler;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:5500")
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


    @PostMapping(value = "/api/updateDrug")
    public Map<String, String> updateDrug(@RequestBody Map<String, String> data){
        Map<String, String> map = new HashMap<>();
        map.put("0", (String) Handler.updateDrugMoney(data.get("id"), Integer.parseInt(data.get("money"))).get(0));
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
    public Map<String, String> addDrug(@RequestBody Map<String, String> data) {
        Drug drug = new Drug(data.get("id"), data.get("name"), data.get("preparation"), data.get("packaging"), data.get("drugGroup"), data.get("dosage"), data.get("type")
                , data.get("ingredients"), Integer.parseInt(data.get("money")), Integer.parseInt(data.get("stock")), new Producers(data.get("producers_id"),"name"), data.get("country"));
        Map<String, String> map = new HashMap<>();
        map.put("0", (String) Handler.saveDrug(drug).get(0));
        return map;
    }

    @PostMapping(value = "/api/addProducer")
    public Map<String, String> addProducer(@RequestBody Map<String, String> data){
        Map<String, String> map = new HashMap<>();
        map.put("0", (String) Handler.saveProducer(new Producers(data.get("id"),data.get("name"))).get(0));
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

    @GetMapping(value = "/api/sortDrugDes")
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
    public Map<String, String> isValid(@RequestBody Map<String, String> user){
        Map<String, String> map = new HashMap<>();
        if (user.get("username").equals("user123") && user.get("password").equals("user123")){
            map.put("0", "user");
        }
        else if (user.get("username").equals("admin123") && user.get("password").equals("admin123")){
            map.put("0", "admin");
        }
        else map.put("0", "invalid");
        return map;
    }
}
