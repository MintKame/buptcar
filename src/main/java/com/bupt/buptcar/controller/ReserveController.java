package com.bupt.buptcar.controller;

import com.bupt.buptcar.pojo.Car;
import com.bupt.buptcar.pojo.Reserve;
import com.bupt.buptcar.pojo.User;
import com.bupt.buptcar.service.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ReserveController {
    @Autowired
    ReserveService reserveService;

    /** 预约看车 */
    @PostMapping("reserve/{carID}")
    public String addReserve(
            @PathVariable("carID") Integer carID,
            @RequestParam("gmt_reserve") String gmt_reserve,
            HttpSession httpSession,
            RedirectAttributes redirectAttributes
    ){
        User user = (User)httpSession.getAttribute("user");
        Integer userID = user.getUserID();
        String reserveMsg = reserveService.addReserve(userID, carID, gmt_reserve);
        redirectAttributes.addAttribute("msg", reserveMsg);
        return "redirect:/car/" + carID; // 留在当前页面
    }

    /** 买家查预约 */
    @GetMapping("reserve")
    public String getBuyerReserve(HttpSession httpSession, Model model){
        User user = (User)httpSession.getAttribute("user");
        List<Reserve> reserves = reserveService.getByBuyerID(user.getUserID());
        model.addAttribute("reserves", reserves);
        return "reserve_list";
    }

    /** 卖家查预约 */
    @GetMapping("myreserve")
    public String getSalerReserve(HttpSession httpSession, Model model){
        User user = (User)httpSession.getAttribute("user");
        List<Reserve> reserves = reserveService.getBySalerID(user.getUserID());
        model.addAttribute("reserves", reserves);
        return "reserve_my_list";
    }

    /** 买家取消预约 */
    @DeleteMapping("reserve/cancel/{reserveID}")
    public String cancelReserve1(HttpSession httpSession, @PathVariable("reserveID")Integer reserveID){
        reserveService.cancelByReserveID(reserveID);
        return "redirect:/reserve";
    }

    /** 卖家取消预约 */
    @DeleteMapping("myreserve/cancel/{reserveID}")
    public String cancelReserve2(HttpSession httpSession, @PathVariable("reserveID")Integer reserveID){
        reserveService.cancelByReserveID(reserveID);
        return "redirect:/myreserve";
    }

    /** 卖家确认预约 */
    @PutMapping("myreserve/confirm/{reserveID}")
    public String confirmReserve(HttpSession httpSession, @PathVariable("reserveID")Integer reserveID){
        reserveService.confirmByReserveID(reserveID);
        return "redirect:/myreserve";
    }

    /** 买家完成预约 */
    @PutMapping("reserve/finish/{reserveID}")
    public String finishReserve1(HttpSession httpSession, @PathVariable("reserveID")Integer reserveID){
        reserveService.finishByReserveID(reserveID);
        return "redirect:/reserve";
    }

    /** 卖家完成预约 */
    @PutMapping("myreserve/finish/{reserveID}")
    public String finishReserve2(HttpSession httpSession, @PathVariable("reserveID")Integer reserveID){
        reserveService.finishByReserveID(reserveID);
        return "redirect:/myreserve";
    }
}
