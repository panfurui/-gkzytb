package com.demo.article;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Slf4j
@Controller
class ImageController {

    @Autowired
    ResourceLoader resourceLoader;
    @Value("${file.path}")
    String rootPath;

    /**
     * 显示单张图片
     *
     * @return
     */
    @RequestMapping("show")
    public void showPhotos(@RequestParam(required = false) String name,
                                     HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = rootPath+"/"+name;

        if (path != null) {
            response.setContentType("image/jpeg");
            FileInputStream is = this.query_getPhotoImageBlob(path);

            if (is != null) {
                int i = is.available(); // 得到文件大小  
                byte data[] = new byte[i];
                is.read(data); // 读数据  
                is.close();
                response.setContentType("image/jpeg");// 设置返回的文件类型  
                OutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象  
                toClient.write(data); // 输出数据  
                toClient.close();
            }
        }
    }



    public FileInputStream query_getPhotoImageBlob(String adress) {
        FileInputStream is = null;
        File filePic = new File(adress);
        try {
            is = new FileInputStream(filePic);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return is;

    }
}