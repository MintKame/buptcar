package com.bupt.buptcar.controller;

import com.bupt.buptcar.pojo.Payment;
import com.bupt.buptcar.pojo.User;
import com.bupt.buptcar.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    /** 发起订单 */
    @PostMapping("payment/{carID}")
    public String addPayment(
            @PathVariable("carID") Integer carID,
            HttpSession httpSession,
            RedirectAttributes redirectAttributes
    ){
        User user = (User)httpSession.getAttribute("user");
        String paymentMsg = paymentService.addPayment(user.getUserID(), carID);
        redirectAttributes.addAttribute("msg", paymentMsg);
        return "redirect:/car/" + carID; // 留在当前页面
    }

    /** 买家查订单 */
    @GetMapping("payment")
    public String getBuyerpayment(HttpSession httpSession, Model model){
        User user = (User)httpSession.getAttribute("user");
        List<Payment> payments = paymentService.getByBuyerID(user.getUserID());
        model.addAttribute("payments", payments);
        return "payment_list";
    }

    /** 卖家查订单 */
    @GetMapping("mypayment")
    public String getSalerpayment(HttpSession httpSession, Model model){
        User user = (User)httpSession.getAttribute("user");
        List<Payment> payments = paymentService.getBySalerID(user.getUserID());
        model.addAttribute("payments", payments);
        return "payment_my_list";
    }

    /** 买家取消订单 */
    @DeleteMapping("payment/cancel/{paymentID}")
    public String cancelpayment1(HttpSession httpSession, @PathVariable("paymentID")Integer paymentID){
        paymentService.cancelByPaymentID(paymentID);
        return "redirect:/payment";
    }

    /** 卖家取消订单 */
    @DeleteMapping("mypayment/cancel/{paymentID}")
    public String cancelpayment2(HttpSession httpSession, @PathVariable("paymentID")Integer paymentID){
        paymentService.cancelByPaymentID(paymentID);
        return "redirect:/mypayment";
    }

    /** 卖家确认订单 */
    @PutMapping("mypayment/confirm/{paymentID}")
    public String confirmPayment(HttpSession httpSession, @PathVariable("paymentID")Integer paymentID){
        paymentService.confirmByPaymentID(paymentID);
        return "redirect:/mypayment";
    }

    /** 买家支付 */
    @PutMapping("payment/pay/{paymentID}")
    public String pay(HttpSession httpSession, @PathVariable("paymentID")Integer paymentID){
        paymentService.payByPaymentID(paymentID);
        return "redirect:/payment";
    }

    /** 买家确认提车，完成订单 */
    @PutMapping("payment/finish/{paymentID}")
    public String finishPayment(HttpSession httpSession, @PathVariable("paymentID")Integer paymentID){
        paymentService.finishByPaymentID(paymentID);
        return "redirect:/payment";
    }
}
