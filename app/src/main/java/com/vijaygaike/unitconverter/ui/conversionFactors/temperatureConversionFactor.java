package com.vijaygaike.unitconverter.ui.conversionFactors;

public class temperatureConversionFactor {

    /*
        <item>Celsius</item>
        <item>Fahrenheit</item>
        <item>Kelvin</item>
     */

    Float num;
    Float c;
    Float output;

    public Float convertTemperatureToC(String input, String unitIn) {
        num = Float.parseFloat(input);

        switch (unitIn){
            case "Celsius":c = (float)(num * 1);break;
            case "Fahrenheit":c = (float)((num - 32) * 5/9);break;
            case "Kelvin":c = (float)(num - 273.15);break;
        }

        return c;
    }

    public Float convertCToUser(String m, String unitOut) {
        c = Float.parseFloat(m);

        switch (unitOut){
            case "Celsius":output = (float)(c * 1);break;
            case "Fahrenheit":output = (float)((c * 9/5) + 32);break;
            case "Kelvin":output = (float)(c + 273.15);break;
        }

        return output;
    }
}
