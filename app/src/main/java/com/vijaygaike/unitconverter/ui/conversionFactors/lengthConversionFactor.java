package com.vijaygaike.unitconverter.ui.conversionFactors;

@SuppressWarnings("SwitchStatementWithoutDefaultBranch")
public class lengthConversionFactor {

    Float num;
    Float meter;
    Float output;

    public Float convertLengthToMeter(String input, String unitIn){
        /*base :- meter

         TO METER
        1 kilometer     x 0.001
        1 meter         x 1
        1 decimeter     x 10
        1 centimeter    x 100
        1 millimeter    x 1000
        1 micrometer    x 1,000,000
        1 nanometer     x 1000000000
        1 picometer     x 1000000000000
        1 nautical mile x 0.00054
        1 mile          x 0.0006214
        1 yard          x 1.0936133
        1 foot          x 3.2808399
        1 inch          x 39.3700787
         */
        num = Float.parseFloat(input);
        //converting input to meter
        switch (unitIn){
            case "Kilometer":
                meter = (float) (num*1000);break;
            case "Meter":
                meter = (float) (num*1);break;
            case "Decimeter":
                meter = (float) (num*10);break;
            case "Centimeter":
                meter = (float) (num*0.01);break;
            case "Millimeter" :
                meter = (float) (num*0.001);break;
            case "Micrometer":
                meter = (float) (num*0.000001);break;
            case "Nanometer":
                meter = (float) (num*0.000000001);break;
            case "Nautical mile":
                meter = (float) (num*1852);break;
            case "Mile":
                meter = (float) (num*1609.34);break;
            case "Yard":
                meter = (float) (num*0.9144);break;
            case "Foot":
                meter = (float) (num*0.3048);break;
            case "Inch":
                meter = (float) (num*0.0254);break;
        }

        return meter;
    }

    public Float convertMeterToUser(String m, String unitOut){
        //converting meter to desired output.
        meter = Float.parseFloat(m);
        switch (unitOut){
            case "Kilometer":
                output = (float) (meter*0.001);break;
            case "Meter":
                output = (float) ( meter*1);break;
            case "Decimeter":
                output = (float) (meter*10);break;
            case "Centimeter":
                output = (float) (meter*100);break;
            case "Millimeter" :
                output = (float) (meter*1000);break;
            case "Micrometer":
                output = (float) (meter*1000000);break;
            case "Nanometer":
                output = (float) (meter*1000000000);break;
            case "Nautical mile":
                output = (float) (meter*1852);break;
            case "Mile":
                output = (float) (meter*0.0006214);break;
            case "Yard":
                output = (float) (meter*1.0936133);break;
            case "Foot":
                output = (float) (meter*3.2808399);break;
            case "Inch":
                output = (float) (meter*39.3700787);break;
        }

        return output;
    }
}
