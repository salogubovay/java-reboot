package ru.sberbank.edu;

import java.io.IOException;
import java.io.Writer;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FinancialServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/calculator.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String strSum = req.getParameter("sum");
        String strPercentage = req.getParameter("percentage");
        String strYears = req.getParameter("years");
        String respHeader, respStrBody;
        Writer writer = resp.getWriter();
        
        if (!isNumber(strSum) || !isNumber(strPercentage) || !isNumber(strYears)) {
            respHeader = "Результат";
            respStrBody = "Неверный формат данных. Скорректируйте значения";
            writer.write(createHtml(respHeader, respStrBody));
        }
        
        FinancialCalculator calculator = new FinancialCalculator(Double.valueOf(strSum), 
                                                                    Double.valueOf(strPercentage), 
                                                                    Double.valueOf(strYears));
        if (calculator.getSum() < calculator.getMinSum()) {
            respHeader = "Ошибка";
            respStrBody = "Минимальная сумма на момент открытия вклада " + calculator.getMinSum() + " рублей.";
        } else {
            respHeader = "Результат";
            respStrBody = "Итоговая сумма " + calculator.getResult() + " рублей.";       
        }
        writer.write(createHtml(respHeader, respStrBody));     
    }
    
    private boolean isNumber(String number) {
        try {
            Double.parseDouble(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    private String createHtml(String header, String bodyText) {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("<html>\n");
        strBuilder.append("<head><meta charset=\"utf-8\"></head>\n");
        strBuilder.append("<body style=\"margin-left: 20%; margin-right: 20%;\">\n");
        strBuilder.append("<h1>" + header + "</h1>\n");
        strBuilder.append("<p width=300px>" + bodyText + "</p>\n");
        strBuilder.append("</body>");
        strBuilder.append("</html>");    
        return strBuilder.toString();
    }
    
}
