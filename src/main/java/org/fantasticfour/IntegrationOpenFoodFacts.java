package org.fantasticfour;

import org.fantasticfour.bll.AppService;

import java.io.IOException;
import java.sql.SQLException;

public class IntegrationOpenFoodFacts {



  private static final AppService appService = AppService.getSingle();


    public static void main(String[] args) throws IOException, SQLException {
        System.out.println("test");
        appService.insertFromCsvToDataBase();
        appService.updateDataBase();
    }
}
