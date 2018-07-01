package ua.mk.par.elibrary;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ua.mk.par.elibrary.entity.*;

@SpringBootApplication
@EnableAutoConfiguration
public class ELibrary {

    public static void main(String[] args) throws Exception {
        try {
            //testSaveUser();
            //testSaveBook();
            SpringApplication.run(ELibrary.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //System.exit(0);
        }
    }

}
