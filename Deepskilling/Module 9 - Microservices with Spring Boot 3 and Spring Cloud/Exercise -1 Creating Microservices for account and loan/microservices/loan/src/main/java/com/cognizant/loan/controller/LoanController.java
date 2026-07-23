package com.cognizant.loan.controller;

import com.cognizant.loan.model.Loan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanController {

    @GetMapping("/loans/{number}")
    public Loan getLoanDetails(@PathVariable String number) {
        // Return dummy response without backend connectivity
        // Sample Response: { number: "H00987987972342", type: "car", loan: 400000, emi: 3258, tenure: 18 }
        return new Loan(number, "car", 400000.0, 3258.0, 18);
    }
}
