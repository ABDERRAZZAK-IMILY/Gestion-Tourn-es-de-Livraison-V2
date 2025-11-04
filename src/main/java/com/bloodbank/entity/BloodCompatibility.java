package com.bloodbank.entity;

public class BloodCompatibility {



    public static  boolean isCompatible(String donorGroup , String receveurGroup){

        switch (donorGroup){

            case "O-" :
                return true;
            case "O+":
                return  receveurGroup.endsWith("+") || receveurGroup.equals("O-");
            case "A-" :
                return  receveurGroup.startsWith("A") || receveurGroup.startsWith("AB");
            case "A+" :
                return  receveurGroup.equals("A+") || receveurGroup.equals("AB+");
            case "B-":
                return receveurGroup.startsWith("B") || receveurGroup.startsWith("AB");
            case "B+":
                return  receveurGroup.equals("B+") || receveurGroup.equals("AB+");
            case "AB-":
                return receveurGroup.startsWith("AB");
            case "AB+":
                return receveurGroup.equals("AB+");

            default: return false;
        }



    }

}
