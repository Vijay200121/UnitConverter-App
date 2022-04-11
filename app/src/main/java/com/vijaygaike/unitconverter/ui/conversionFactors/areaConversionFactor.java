package com.vijaygaike.unitconverter.ui.conversionFactors;

public class areaConversionFactor {

    /*
        <item>Square Kilometer</item>
        <item>Square Meter</item>
        <item>Square Mile</item>
        <item>Square Yard</item>
        <item>Square Foot</item>
        <item>Square Inch</item>
        <item>Hectare</item>
        <item>Acre</item>
     */
    Float num;
    Float squareMeter;
    Float output;

    public Float convertAreaToSquareMeter(String input, String unitIn) {
        num = Float.parseFloat(input);

        switch (unitIn){
            case "Square Kilometer":squareMeter = (float)(num * 1000000);break;
            case "Square Meter":squareMeter = (float)(num * 1);break;
            case "Square Mile":squareMeter = (float)(num * 2590000);break;
            case "Square Yard":squareMeter = (float)(num * 0.836127);break;
            case "Square Foot":squareMeter = (float)(num * 0.092903);break;
            case "Square Inch":squareMeter = (float)(num * 0.00064516);break;
            case "Hectare":squareMeter = (float)(num * 10000);break;
            case "Acre":squareMeter = (float)(num * 4046.86);break;
        }

        return squareMeter;
    }

    public Float convertSquareMeterToUser(String m, String unitOut) {
        squareMeter = Float.parseFloat(m);

        switch (unitOut){
            case "Square Kilometer":output = (float)(squareMeter * 0.000001);break;
            case "Square Meter":output = (float)(squareMeter * 1);break;
            case "Square Mile":output = (float)(squareMeter * 0.0000003861);break;
            case "Square Yard":output = (float)(squareMeter * 1.196);break;
            case "Square Foot":output = (float)(squareMeter * 10.7639);break;
            case "Square Inch":output = (float)(squareMeter * 1550);break;
            case "Hectare":output = (float)(squareMeter * 0.0001);break;
            case "Acre":output = (float)(squareMeter * 0.000247105);break;
        }

        return output;
    }
}
