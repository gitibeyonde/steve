package de.rwth.idsg.steve.web.controller;

import de.rwth.idsg.steve.repository.ReservationRepository;
import de.rwth.idsg.steve.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * One controller for transactions and reservations pages
 *
 * @author Sevket Goekay <goekay@dbis.rwth-aachen.de>
 * @since 15.08.2014
 */
@Controller
public class TransactionsReservationsController {

    @Autowired private TransactionRepository transactionRepository;
    @Autowired private ReservationRepository reservationRepository;

    @RequestMapping(value = "/transactions", method = RequestMethod.GET)
    public String getTransactions(Model model) {
        model.addAttribute("transList", transactionRepository.getTransactions());
        return "data-man/transactions";
    }

    @RequestMapping(value = "/transactions/csv", method = RequestMethod.GET)
    public void getTransactionsCSV(HttpServletResponse response) throws IOException {
        String fileName = "transactions.csv";
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", fileName);
        response.setContentType("text/csv");
        response.setHeader(headerKey, headerValue);
        response.getOutputStream().print(transactionRepository.getTransactionsCSV());
    }

    @RequestMapping(value = "/reservations", method = RequestMethod.GET)
    public String getReservations(Model model) {
        model.addAttribute("reservList", reservationRepository.getReservations());
        return "data-man/reservations";
    }

}