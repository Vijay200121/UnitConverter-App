package com.vijaygaike.unitconverter.ui.conversionFactors;

public class weightConversionFactor {

    //tonne, kilogram, gram, milligram, pound.

    Float num;
    Float kg;
    Float output;

    public Float convertWeightToKg(String input, String unitIn) {
        num = Float.parseFloat(input);

        switch (unitIn){
            case "Tonne":
                kg = (num*1000);break;
            case "Kilogram":
                kg = (num*1);break;
            case "Gram":
                kg = (float)(num*0.001);break;
            case "Milligram":
                kg = (float)(num*0.000001);break;
            case "Pound":
                kg = (float)(num*0.453592);break;
        }

        return kg;
    }

    public Float convertKgToUser(String m, String unitOut) {
        kg = Float.parseFloat(m);

        switch (unitOut){
            case "Tonne":
                output = (float)(kg*0.001);break;
            case "Kilogram":
                output = (kg*1);break;
            case "Gram":
                output = kg*1000;break;
            case "Milligram":
                output = kg*1000000;break;
            case "Pound":
                output = (float)(kg*2.20462);break;
        }

        return output;
    }
}
