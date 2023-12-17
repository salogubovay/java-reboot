package ru.sberbank.edu;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.ModelAndView;

public class FinancialService {
    
    @Value("${minSum}")
    private Double minSum;
    
    public void generateResult(ModelAndView modelAndView, String strSum, String strPercentage, String strYears) {
        String respHeader, respStrBody;
        if (!isNumber(strSum) || !isNumber(strPercentage) || !isNumber(strYears)) {
            respHeader = "Результат";
            respStrBody = "Неверный формат данных. Скорректируйте значения";
            setModel(modelAndView, respHeader, respStrBody);
        } else {
            FinancialCalculator calculator = new FinancialCalculator(Double.valueOf(strSum), 
                                                                     Double.valueOf(strPercentage), 
                                                                     Double.valueOf(strYears));
            if (calculator.getSum() < 0 || calculator.getPercentage() < 0 || calculator.getYears() < 0) {
                respHeader = "Результат";
                respStrBody = "Неверный формат данных. Скорректируйте значения";
            } else if (calculator.getSum() < minSum) {
                respHeader = "Ошибка";
                respStrBody = "Минимальная сумма на момент открытия вклада " + formatResult(minSum) + " рублей.";
            } else {
                respHeader = "Результат";
                respStrBody = "Итоговая сумма " + formatResult(calculator.calculateResult()) + " рублей.";       
            }
        }
        setModel(modelAndView, respHeader, respStrBody);
    }
    
    
    private boolean isNumber(String number) {
        try {
            Double.parseDouble(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    private void setModel(ModelAndView modelAndView, String headerText, String bodyText) {
        modelAndView.addObject("headerText", headerText);
        modelAndView.addObject("bodyText", bodyText);
    }
    
    public String formatResult(double result) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        symbols.setGroupingSeparator(' ');
        return new DecimalFormat("###,###", symbols).format(result);
    }
}
