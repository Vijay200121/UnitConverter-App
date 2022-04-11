package com.vijaygaike.unitconverter.ui.conversionFactors;

public class dataSizeConversionFactor {

    /*
        <item>Bit</item>
        <item>Byte</item>
        <item>KiloByte</item>
        <item>MegaByte</item>
        <item>GigaByte</item>
        <item>TeraByte</item>
        <item>PetaByte</item>
     */

    Float num;
    Float mb;
    Float output;

    public Float convertDataSizeToMb(String input, String unitIn) {
        num = Float.parseFloat(input);

        switch (unitIn){
            case "Bit":mb = (float)(num * 0.000000125);break;
            case "Byte":mb = (float)(num * 0.000001);break;
            case "KiloByte":mb = (float)(num * 0.001);break;
            case "MegaByte":mb = (float)(num * 1);break;
            case "GigaByte":mb = (float)(num * 1000);break;
            case "TeraByte":mb = (float)(num * 1000000);break;
            case "PetaByte":mb = (float)(num * 1000000000);break;
        }

        return mb;
    }

    public Float convertMbToUser(String m, String unitOut) {
        mb = Float.parseFloat(m);

        switch (unitOut){
            case "Bit":output = (float)(mb * 8000000);break;
            case "Byte":output = (float)(mb * 1000000);break;
            case "KiloByte":output = (float)(mb * 1000);break;
            case "MegaByte":output = (float)(mb * 1);break;
            case "GigaByte":output = (float)(mb * 0.001);break;
            case "TeraByte":output = (float)(mb *0.000001);break;
            case "PetaByte":output = (float)(mb * 0.000000001);break;
        }


        return output;
    }
}
