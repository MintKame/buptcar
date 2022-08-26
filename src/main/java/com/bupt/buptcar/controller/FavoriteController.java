package com.bupt.buptcar.controller;

import com.bupt.buptcar.pojo.Car;
import com.bupt.buptcar.pojo.User;
import com.bupt.buptcar.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class FavoriteController {
    @Autowired
    FavoriteService favoriteService;

    /** 收藏汽车 */
    @PostMapping("favorite/{carID}")
    public String addToFavorite(
            @PathVariable("carID") Integer carID,
            HttpSession httpSession,
            RedirectAttributes redirectAttributes
    ){
        User user = (User)httpSession.getAttribute("user");
        String favoriteMsg = favoriteService.addToFavorite(user.getUserID(), carID);
        redirectAttributes.addAttribute("msg", favoriteMsg);
        return "redirect:/car/" + carID;
    }

    /** 获取所有收藏的汽车 */
    @GetMapping("favorite")
    public String getMyFavorite(HttpSession httpSession, Model model){
        User user = (User)httpSession.getAttribute("user");
        Integer userID = user.getUserID();
        List<Car> cars = favoriteService.getByBuyerID(userID);
        model.addAttribute("cars", cars);
        return "favorite_list";
    }

    /** 取消收藏汽车 */
    @DeleteMapping("favorite/{carID}")
    public String deleteByBuyerIDCarID(HttpSession httpSession, @PathVariable("carID")Integer carID){
        User user = (User)httpSession.getAttribute("user");
        Integer userID = user.getUserID();
        favoriteService.deleteByBuyerIDCarID(userID, carID);
        return "redirect:/favorite";
    }
}
