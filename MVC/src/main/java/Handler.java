package main.java;
import main.java.config.AppConfig;
import main.java.entity.Drug;
import main.java.entity.Producers;
import main.java.service.Service;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by CoT on 10/13/17.
 */
public class Handler {
    private static Service service = (Service) new AnnotationConfigApplicationContext(AppConfig.class).getBean("service");
    public static void saveDatabase(){
        ArrayList<Drug> drugs = new ArrayList<>();
        ArrayList<Producers> producers = new ArrayList<>();
        try {
            File myObj = new File("message.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] split = data.split("&&");
                boolean isExist = false;
                for (Producers value : producers) {
                    if (value.getId().equals(split[9])) {
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    Producers producer = new Producers();
                    producer.setId(split[9]);
                    producer.setName(split[8]);
                    producers.add(producer);
                }
            }
            myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] split = data.split("&&");
                for (Producers producer : producers) {
                    if (producer.getId().equals(split[9])) {
                        Drug drug = new Drug(split[0], split[1], split[2], split[3], split[4], split[5], split[6], split[7], producer, split[10]);
                        drugs.add(drug);
                        break;
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException ignored) {}

        for (Producers producer: producers) {
            try {
                service.saveProducer(producer);
            }
            catch (Exception ignored){}
        }
        for (Drug drug: drugs) {
            try {
                service.saveDrug(drug);
            }catch (Exception ignored){}
        }
    }

    static List getAllDrugs(){
        ArrayList<Producers> producers = new ArrayList<>();
        try {
            File myObj = new File("message.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] split = data.split("&&");
                boolean isExist = false;
                for (Producers value : producers) {
                    if (value.getId().equals(split[9])) {
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    Producers producer = new Producers();
                    producer.setId(split[9]);
                    producer.setName(split[8]);
                    producers.add(producer);
                }
            }
            myReader.close();
        } catch (FileNotFoundException ignored) {}
        return producers;
    }

}
