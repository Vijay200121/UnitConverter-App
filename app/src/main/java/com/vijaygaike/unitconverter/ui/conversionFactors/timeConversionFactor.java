package com.vijaygaike.unitconverter.ui.conversionFactors;

public class timeConversionFactor {
    Float num;
    Float min;
    Float output;

    public Float convertTimeToMin(String input, String unitIn) {
        num = Float.parseFloat(input);

        switch (unitIn){
            case "Millisecond":
                min = (float)(num * 0.00001667);break;
            case "Second":
                min = (float)(num * 0.0166667);break;
            case "Minute":
                min = (float)(num * 1);break;
            case "Hour":
                min = (float)(num * 60);break;
            case "Day":
                min = (float)(num * 1440);break;
            case "Week":
                min = (float)(num * 10080);break;
            case "Month":
                min = (float)(num * 43800);break;
            case "Calender Year":
                min = (float)(num * 525600);break;
        }

        return min;
    }

    public Float convertMinToUser(String m, String unitOut) {
        min = Float.parseFloat(m);

        switch (unitOut){
            case "Millisecond":
                output = (float)(min * 60000);break;
            case "Second":
                output = (float)(min * 60);break;
            case "Minute":
                output = (float)(min * 1);break;
            case "Hour":
                output = (float)(min * 0.0166667);break;
            case "Day":
                output = (float)(min * 0.000694444);break;
            case "Week":
                output = (float)(min * 0.0000992);break;
            case "Month":
                output = (float)(min * 0.0000228);break;
            case "Calender Year":
                output = (float)(min * 0.00000190);break;
        }


        return output;
    }

}
