package com.vijaygaike.unitconverter.ui.conversionFactors;

public class pressureConversionFactor {

    /*
        <item>Bar</item>
        <item>Pascal</item>
        <item>Pound per square inch</item>
        <item>Standard atmosphere</item>
        <item>Torr</item>
     */

    Float num;
    Float pa;
    Float output;

    public Float convertPressureToPa(String input, String unitIn) {
        num = Float.parseFloat(input);

        switch (unitIn){
            case "Bar":pa = (float)(num *100000);break;
            case "Pascal":pa = (float)(num *1);break;
            case "Pound per square inch":pa = (float)(num *6894.76);break;
            case "Standard atmosphere":pa = (float)(num *101325);break;
            case "Torr":pa = (float)(num *133.322);break;
        }

        return pa;
    }

    public Float convertPaToUser(String m, String unitOut) {
        pa = Float.parseFloat(m);

        switch (unitOut){
            case "Bar":output = (float)(pa *0.00005);break;
            case "Pascal":output = (float)(pa *1);break;
            case "Pound per square inch":output = (float)(pa *0.000145038);break;
            case "Standard atmosphere":output = (float)(pa *0.00000987);break;
            case "Torr":output = (float)(pa *0.00750062);break;
        }

        return output;
    }
}
