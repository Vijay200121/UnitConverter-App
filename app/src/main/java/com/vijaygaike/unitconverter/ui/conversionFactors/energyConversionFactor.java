package com.vijaygaike.unitconverter.ui.conversionFactors;

public class energyConversionFactor {

    /*
        <item>Joule</item>
        <item>KiloJoule</item>
        <item>Gram Calorie</item>
        <item>KiloCalorie</item>
        <item>Watt Hour</item>
        <item>Kilowatt Hour</item>
     */

    Float num;
    Float j;
    Float output;

    public Float convertEnergyToJ(String input, String unitIn) {
        num = Float.parseFloat(input);

        switch (unitIn){
            case "Joule":j = (float)(num * 1);break;
            case "KiloJoule":j = (float)(num * 1000);break;
            case "Gram Calorie":j = (float)(num * 4.184);break;
            case "KiloCalorie":j = (float)(num * 4184);break;
            case "Watt Hour":j = (float)(num * 3600);break;
            case "Kilowatt Hour":j = (float)(num * 360000);break;
        }

        return j;
    }

    public Float convertJToUser(String m, String unitOut) {
        j = Float.parseFloat(m);

        switch (unitOut){
            case "Joule":output = (float)(j * 1);break;
            case "KiloJoule":output = (float)(j * 0.001);break;
            case "Gram Calorie":output = (float)(j * 0.239006);break;
            case "KiloCalorie":output = (float)(j * 0.000239006);break;
            case "Watt Hour":output = (float)(j * 0.000277778);break;
            case "Kilowatt Hour":output = (float)(j * 0.0000002778);break;
        }

        return output;
    }
}
