package com.vijaygaike.unitconverter.ui.conversionFactors;

public class speedConversionFactor {

    /*
        <item>Miles per hour</item>
        <item>Foot per second</item>
        <item>Meter per second</item>
        <item>Kilometer per hour</item>
        <item>Knot</item>
     */

    Float num;
    Float mps;
    Float output;

    public Float convertSpeedToMps(String input, String unitIn) {
        num = Float.parseFloat(input);

        switch (unitIn){
            case "Miles per hour":mps = (float)(num * 0.44704);break;
            case "Foot per second":mps = (float)(num * 0.3048);break;
            case "Meter per second":mps = (float)(num * 1);break;
            case "Kilometer per hour":mps = (float)(num * 0.277778);break;
            case "Knot":mps = (float)(num * 0.514444);break;
        }

        return mps;
    }

    public Float convertMpsToUser(String m, String unitOut) {
        mps = Float.parseFloat(m);

        switch (unitOut){
            case "Miles per hour":output = (float)(mps * 2.23694);break;
            case "Foot per second":output = (float)(mps * 3.28084);break;
            case "Meter per second":output = (float)(mps * 1);break;
            case "Kilometer per hour":output = (float)(mps * 3.6);break;
            case "Knot":output = (float)(mps * 1.94384);break;
        }

        return output;
    }
}
