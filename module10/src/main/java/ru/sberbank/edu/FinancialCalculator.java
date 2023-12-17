package ru.sberbank.edu;

public class FinancialCalculator {
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
}
