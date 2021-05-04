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
    public Map deleteDrug(@RequestBody String id){
        return Handler.deleteDrug(id);
    }

    @PostMapping(value = "/api/deleteProducer")
    public Map deleteProducer(@RequestBody String id){
        return Handler.deleteProducer(id);
    }

    @PostMapping(value = "/api/updateDrug")
    public Map updateDrug(@RequestBody String id, @RequestBody int money){
        return Handler.updateDrugMoney(id, money);
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
    public Map addDrug(@RequestBody Drug drug){
        return Handler.saveDrug(drug);
    }

    @PostMapping(value = "/api/addProducer")
    public Map addProducer(@RequestBody Producers producer){
        return Handler.saveDrug(producer);
    }

    @PostMapping(value = "/api/getTypes")
    public Map getAllTypes(){
        return Handler.getAllTypes();
    }

    @PostMapping(value = "/api/getGroups")
    public Map getAllGroups(){
        return Handler.getAllGroups();
    }

    @PostMapping(value = "/api/sortDrugAsc")
    public Map getDrugsSortAsc(){
        HashMap<String,String> map = new HashMap<>();
        List list =  Handler.getDrugSortAsc();
        for (int i = 0; i< list.size(); i++){
            Drug drug = (Drug) list.get(i);
            map.put(Integer.toString(i), drug.toString());
        }
        return map;
    }

    @PostMapping(value = "/api/sortDrugDes")
    public Map getDrugsSortDes(){
        HashMap<String,String> map = new HashMap<>();
        List list =  Handler.getDrugSortDes();
        for (int i = 0; i< list.size(); i++){
            Drug drug = (Drug) list.get(i);
            map.put(Integer.toString(i), drug.toString());
        }
        return map;
    }

    @PostMapping(value = "/api/searchDrugsById")
    public Map searchDrugsById(@RequestBody String id){
        HashMap<String,String> map = new HashMap<>();
        List list =  Handler.searchDrugsById(id);
        for (int i = 0; i< list.size(); i++){
            map.put(Integer.toString(i), (String) list.get(i));
        }
        return map;
    }

    @PostMapping(value = "/api/searchDrugsByName")
    public Map searchDrugsByName(@RequestBody String name){
        HashMap<String,String> map = new HashMap<>();
        List list =  Handler.searchDrugsByName(name);
        for (int i = 0; i< list.size(); i++){
            map.put(Integer.toString(i), (String) list.get(i));
        }
        return map;
    }

    @PostMapping(value = "/api/getNOP")
    public Map getNOP (@RequestBody String producerID){
        return Handler.getNOP(producerID);
    }

    @PostMapping(value = "/api/authUser")
    public Map isValid(@RequestBody String username,@RequestBody String password){
        if (username.equals("user123") && password.equals("user123")){
            return (Map) new HashMap<>().put("response", "valid");
        }
        else if (username.equals("admin321") && password.equals("admin321")){
            return (Map) new HashMap<>().put("response", "valid");
        }
        else return (Map) new HashMap<>().put("response", "invalid");
    }
}
