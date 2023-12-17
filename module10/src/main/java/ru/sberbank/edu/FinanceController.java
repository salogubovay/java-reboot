package ru.sberbank.edu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/finance")
public class FinanceController {
    
    @Autowired
    private FinancialService financialService;
    
    @GetMapping
    public ModelAndView financeCalculator() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/calculator.jsp");
        return modelAndView;
    }
    
    @PostMapping
    public ModelAndView response(@RequestParam("sum") String sum, @RequestParam("percentage") String percentage, @RequestParam("years") String years) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/response.jsp");
        financialService.generateResult(modelAndView, sum, percentage, years);
        return modelAndView;
    }
}
