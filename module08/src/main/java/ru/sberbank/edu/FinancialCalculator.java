package ru.sberbank.edu;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class FinancialCalculator {
    private int minSum = 50_000;
    private double sum;
    private double percentage;
    private double years;
    

    public FinancialCalculator(double sum, double percentage, double years) {
        this.sum = sum;
        this.percentage = percentage;
        this.years = years;
    }
    
    public double calculateResult() {
        return sum * Math.pow(1 + percentage / 100,  years);
    }

    public double getSum() {
        return sum;
    }

    public double getPercentage() {
        return percentage;
    }

    public double getYears() {
        return years;
    }

    public int getMinSum() {
        return minSum;
    }
    
    public String getResult() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        symbols.setGroupingSeparator(' ');
        return new DecimalFormat("###,###", symbols).format(calculateResult());
    }
    
}
