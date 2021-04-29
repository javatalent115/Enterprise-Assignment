
import rmit.config.AppConfig;
import rmit.entity.Drug;
import rmit.entity.Producers;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import rmit.service.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by CoT on 10/13/17.
 */
public class Main {


    public static void main(String[] args){

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Service service = (Service) context.getBean("service");
        ArrayList<Drug> drugs = new ArrayList<>();
        ArrayList<Producers> producers = new ArrayList<>();
        try {
            File myObj = new File("message.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] split = data.split("&");
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
                String[] split = data.split("&");
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
            service.saveProducer(producer);
        }
        for (Drug drug: drugs) {
            service.saveDrug(drug);
        }

    }

}
