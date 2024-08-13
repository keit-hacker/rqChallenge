package com.example.rqchallenge.mock;

import com.example.rqchallenge.dto.EmployeeDTO;

import java.util.Arrays;
import java.util.List;

public class MockEmployeeData {
    public static List<EmployeeDTO> getMockedEmployees() {
        return Arrays.asList(
                new EmployeeDTO("1", "Tiger Nixon", "320800", "61", ""),
                new EmployeeDTO("2", "Garrett Winters", "170750", "63", ""),
                new EmployeeDTO("3", "Ashton Cox", "86000", "66", ""),
                new EmployeeDTO("4", "Cedric Kelly", "433060", "22", ""),
                new EmployeeDTO("5", "Airi Satou", "162700", "33", ""),
                new EmployeeDTO("6", "Brielle Williamson", "372000", "61", ""),
                new EmployeeDTO("7", "Herrod Chandler", "137500", "59", ""),
                new EmployeeDTO("8", "Rhona Davidson", "327900", "55", ""),
                new EmployeeDTO("9", "Colleen Hurst", "205500", "39", ""),
                new EmployeeDTO("10", "Sonya Frost", "103600", "23", ""),
                new EmployeeDTO("11", "Jena Gaines", "90560", "30", ""),
                new EmployeeDTO("12", "Quinn Flynn", "342000", "22", ""),
                new EmployeeDTO("13", "Charde Marshall", "470600", "36", ""),
                new EmployeeDTO("14", "Haley Kennedy", "313500", "43", ""),
                new EmployeeDTO("15", "Tatyana Fitzpatrick", "385750", "19", ""),
                new EmployeeDTO("16", "Michael Silva", "198500", "66", ""),
                new EmployeeDTO("17", "Paul Byrd", "725000", "64", ""),
                new EmployeeDTO("18", "Gloria Little", "237500", "59", ""),
                new EmployeeDTO("19", "Bradley Greer", "132000", "41", ""),
                new EmployeeDTO("20", "Dai Rios", "217500", "35", ""),
                new EmployeeDTO("21", "Jenette Caldwell", "345000", "30", ""),
                new EmployeeDTO("22", "Yuri Berry", "675000", "40", ""),
                new EmployeeDTO("23", "Caesar Vance", "106450", "21", ""),
                new EmployeeDTO("24", "Doris Wilder", "85600", "23", "")
        );
    }
}