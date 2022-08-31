package com.bupt.buptcar.controller;

import com.bupt.buptcar.pojo.Car;
import com.bupt.buptcar.pojo.User;
import com.bupt.buptcar.service.CarService;
import com.bupt.buptcar.service.ImgService;
import com.bupt.buptcar.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
public class CarController {
    @Autowired
    CarService carService;

    @Autowired
    SeriesService seriesService;

    @Autowired
    ImgService imgService;

    @RequestMapping(value = {"car", "cars"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String getCars(){
        return "redirect:cars/1";
    }

    /** 某页汽车  */
    @RequestMapping(value = "cars/{pageID}", method = {RequestMethod.GET, RequestMethod.POST})
    public String getCarOfPage(Model model, Car carCondition, @PathVariable("pageID") Integer pageID){
        List<Car> carList = carService.getCarList(carCondition, pageID);
        List<String> brandList = seriesService.getBrandList();
        List<String> seriesNameList = seriesService.getSeriesNameList();
        model.addAttribute("pageID", pageID);
        model.addAttribute("cars", carList);
        model.addAttribute("brands", brandList);
        model.addAttribute("seriesNames", seriesNameList);
        return "car_list";
    }

    /** 查看汽车详情 */
    @GetMapping("car/{carID}")
    public String getById(Model model,
                          @RequestParam(value = "msg", required = false) String msg,
                          @PathVariable("carID") Integer carID){
        Car car = carService.getById(carID);
        List<String> imgs = imgService.getImgByCarID(carID);
        model.addAttribute("car", car);
        model.addAttribute("imgs", imgs);
        if (msg != null)
            model.addAttribute("msg", msg);
        return "car_info";
    }

    /** 获取汽车图片 */
    @GetMapping("pic/{img}")
    public ResponseEntity<byte[]> download(@PathVariable("img")String img) throws IOException {
        final byte[] bytes = imgService.download(img);
        // 响应头 (HttpHeaders)
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Content-Type", "application/octet-stream;charset=UTF-8");
        // 状态码
        HttpStatus statusCode = HttpStatus.OK;
        // 创建ResponseEntity（响应体，相应头，状态码）
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers, statusCode);
        return responseEntity;
    }


    /** 卖家上传的所有汽车 */
    @GetMapping("mycar")
    public String getMyCarList(Model model, HttpSession session){
        User user = (User)session.getAttribute("user");
        Integer userID = user.getUserID();
        List<Car> carList = carService.getBySalerID(userID);
        model.addAttribute("cars", carList);
        return "my_list";
    }

    /** 卖家删除某汽车 */
    @DeleteMapping("mycar/{carID}")
    public String deleteByID(@PathVariable("carID")Integer carID){
        carService.deleteByID(carID);
        return "redirect:/mycar";
    }

    /** 进入上传汽车页面 */
    @GetMapping("addCar")
    public String toAddCar(Model model, @RequestParam(value = "msg", required = false)String msg){
        List<String> brandList = seriesService.getBrandList();
        List<String> seriesNameList = seriesService.getSeriesNameList();
        model.addAttribute("brands", brandList);
        model.addAttribute("seriesNames", seriesNameList);
        if (msg != null)
            model.addAttribute("msg", msg);
        return "my_add";
    }

    /** 卖家上传某汽车 */
    @PostMapping("mycar")
    public String addMyCar(HttpSession session,
                           Car car,
                           @RequestPart("main_img") MultipartFile mainImg,
                           @RequestPart("imgs") MultipartFile[] imgs,
                           RedirectAttributes attributes) throws IOException {
        // 汽车信息
        User user = (User)session.getAttribute("user");
        Integer userID = user.getUserID();
        String msg = carService.addCar(car, userID);
        attributes.addAttribute("msg", msg);
        // 汽车图片
        Integer carID = car.getCarID();
        imgService.upload(carID, mainImg, imgs);
        return "redirect:/addCar";
    }
}