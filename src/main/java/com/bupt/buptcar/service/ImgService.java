package com.bupt.buptcar.service;

import com.bupt.buptcar.dao.CarMapper;
import com.bupt.buptcar.dao.ImgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.*;
import java.util.List;
import java.util.UUID;

@Service
public class ImgService {

    static private String prefix = "E://pic/car/";

    @Autowired
    ImgMapper imgMapper;

    /** 文件上传 */
    public String upload(Integer carID, MultipartFile mainImg,  MultipartFile[] imgs) throws IOException {
        // 上传文件，文件名存到数据库
        if(!mainImg.isEmpty()){
            String mainImgName = upload(mainImg);
            imgMapper.setImgByCarID(carID, mainImgName);
        }

        for (MultipartFile img : imgs) {
            if(!img.isEmpty()){
                String imgName = upload(img);
                imgMapper.addImg(carID, imgName);
            }
        }
        return "success";
    }

    // 上传文件，返回文件名
    private String upload(MultipartFile img) throws IOException{
        // 文件名
        String fileName = img.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf(".")); // 文件名后缀
        fileName = UUID.randomUUID().toString() + suffixName; // 处理重名
        // 服务器中，photo目录 的路径
        File file = new File(prefix);
        if(!file.exists()){
            file.mkdir();
        }
        // 最终 文件上传到 target文件夹下
        String finalPath = prefix + File.separator + fileName;
        img.transferTo(new File(finalPath));
        return fileName;
    }


    /** 文件下载 */
    public byte[] download(String img) throws IOException {
        // webapp下文件的路径 -> 服务器中文件的真实路径
        String filePath = prefix + img;
        // 将文件 读到 字节数组
        InputStream is = new FileInputStream(filePath);
        byte[] bytes = new byte[is.available()];  // size为文件的字节数
        is.read(bytes);
        is.close();
        return bytes;
    }

    public List<String> getImgByCarID(Integer carID){
        return imgMapper.getImgByCarID(carID);
    }
}
