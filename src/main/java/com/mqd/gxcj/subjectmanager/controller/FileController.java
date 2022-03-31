package com.mqd.gxcj.subjectmanager.controller;

import com.mqd.gxcj.subjectmanager.exception.AppException;
import com.mqd.gxcj.subjectmanager.utils.R;
import com.mqd.gxcj.subjectmanager.utils.RStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.UUID;


@Api(tags = "文件上传与管理")
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {

    @Value("${file.file-location}")
    private String fileLocation;

    @Value("${file.img-location}")
    private String imgLocation;


    @ApiOperation(value = "上传文件的接口")
    @PostMapping("/upload/file")
    public R uploadFile(@RequestPart("file") MultipartFile file) throws AppException, IOException {
        String originalFilename = file.getOriginalFilename();
        assert originalFilename != null;
        //文件上传
        if (originalFilename.endsWith(".zip") || originalFilename.endsWith(".7z")){
            File parent = new File(fileLocation);
            if (!parent.exists()) {
                boolean mkdirs = parent.mkdirs();
            }
            //创建文件夹
            String uuid = null;
            File dest = null;
            do {
                uuid = UUID.randomUUID().toString();
                dest = new File(fileLocation + "/" + uuid);
            }while (dest.exists());
            boolean mkdirs = dest.mkdirs();
            //保存文件到本地
            File local = new File(fileLocation + "/" + uuid + "/" + originalFilename);
            if (mkdirs){
                file.transferTo(local);
                return R.ok().put("url",uuid);
            }
            throw new AppException(RStatus.ERROR);
        }
        //图片上传
        if (originalFilename.endsWith(".jpg") || originalFilename.endsWith(".png")){
            long size = file.getSize();
            if (size > 1024*1024*2){
                throw new AppException(RStatus.VERIFY_ERROR);
            }
            File imgPath = new File(imgLocation);
            if (!imgPath.exists()){
                boolean mkdirs = imgPath.mkdirs();
            }
            String uuid = null;
            File dest = null;
            String filename = null;
            do {
                uuid = UUID.randomUUID().toString();
                filename = uuid +
                        originalFilename.substring(originalFilename.lastIndexOf("."));
                dest = new File(imgLocation + "/" + filename);
            }while (dest.exists());
            file.transferTo(dest);
            return R.ok().put("url",filename);
        }
        throw new AppException(RStatus.VERIFY_ERROR);
    }

    @ApiOperation(value = "下载文件的接口")
    @GetMapping("/download/file/{filename}")
    public String downloadFile(HttpServletResponse response, @PathVariable String filename) throws AppException, FileNotFoundException, UnsupportedEncodingException {
        File file = new File(fileLocation + "/" + filename);
        if (!file.exists()){
            throw new AppException(RStatus.FILE_NOT_EXIST);
        }
        File[] files = file.listFiles();
        response.reset();
        response.setCharacterEncoding("utf-8");
        assert files != null;
        response.setContentType("multipart/form-data");
        //response.setContentType("multipart/form-data;charset=UTF-8");也可以明确的设置一下UTF-8，测试中不设置也可以。
        response.setHeader("Content-Disposition", "attachment; fileName="+  files[0].getName() +";filename*=utf-8''"+ URLEncoder.encode(files[0].getName(),"UTF-8"));
        response.setContentLength((int) files[0].length());
        System.out.println(files[0].getName());
        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(files[0]));){
            byte[] buff = new byte[4096];
            ServletOutputStream os = response.getOutputStream();
            int index = 0;
            while ((index = bis.read(buff)) != -1){
                os.write(buff,0,index);
                os.flush();
            }
        } catch (IOException e) {
            log.error("下载文件出错");
            e.printStackTrace();
            return "下载文件出错";
        }
        return "ok";
    }

    @ApiOperation(value = "获取图片的接口")
    @GetMapping("/download/img/{filename}")
    public String downloadImg(HttpServletResponse response, @PathVariable String filename) throws AppException, IOException {
        File file = new File(imgLocation + "/" + filename);
        if (!file.exists()){
            throw new AppException(RStatus.FILE_NOT_EXIST);
        }
        response.reset();
        response.setCharacterEncoding("utf-8");
        response.setContentType("image/png");
        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));){
            byte[] buff = new byte[4096];
            ServletOutputStream os = response.getOutputStream();
            int index = 0;
            while ((index = bis.read(buff)) != -1){
                os.write(buff,0,index);
                os.flush();
            }
        } catch (IOException e) {
            log.error("下载图片出错");
            e.printStackTrace();
            return "下载图片出错";
        }
        return "ok";
    }
}
