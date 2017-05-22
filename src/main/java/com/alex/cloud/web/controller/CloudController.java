package com.alex.cloud.web.controller;

import com.alex.cloud.model.File;
import com.alex.cloud.model.User;
import com.alex.cloud.service.FileService;
import com.alex.cloud.web.helper.DBInitializer;
import com.alex.cloud.web.helper.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;

@Controller
public class CloudController {

    @Autowired
    private FileService fileService;
    @Autowired
    private DBInitializer dbInitializer;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, HttpServletRequest httpServletRequest){
        dbInitializer.fillTables();
        model.addAttribute("user", new User());
        try {
            Object flash = httpServletRequest.getSession().getAttribute("flash");
            model.addAttribute("flash", flash);

            httpServletRequest.getSession().removeAttribute("flash");
        }
        catch (Exception e){

        }
        return "login";
    }


    @RequestMapping(value = "/home")
    public String home(){
        return "home";
    }


    @RequestMapping(value = "/upload")
    public String upload(Model model){
        List<File> files = fileService.findAll();
        model.addAttribute("file", new File());
        model.addAttribute("files", files);
        return "upload";
    }


    @RequestMapping(value = "/performUpload", method = RequestMethod.POST)
    public String performUpload(File file, @RequestParam MultipartFile multipartFile, Principal principal, RedirectAttributes redirectAttributes) throws IOException {
        User user = (User) ((UsernamePasswordAuthenticationToken)principal).getPrincipal();
        fileService.save(file, multipartFile, user);

        redirectAttributes.addFlashAttribute("flash",new FlashMessage("File successfully uploaded", FlashMessage.Status.SUCCESS));

        return "redirect:/upload";
    }


    @RequestMapping(value = "/file/{fileId}")
    public void download(@PathVariable Long fileId, Model model,  HttpServletResponse httpServletResponse) throws IOException {
        File file = fileService.findOne(fileId);

        Files.write(Paths.get("/home/alexandru_bobernac/Documents/javaTest/alex.txt"), file.getBytes());
        java.io.File fileToDownload = new java.io.File("/home/alexandru_bobernac/Documents/javaTest/alex.txt");

        String mimeType = URLConnection.guessContentTypeFromName(fileToDownload.getName());
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }

        httpServletResponse.setContentType(mimeType);
        httpServletResponse.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
        httpServletResponse.setContentLength((int) fileToDownload.length());

        InputStream inputStream = new BufferedInputStream(new FileInputStream(fileToDownload));
        FileCopyUtils.copy(inputStream, httpServletResponse.getOutputStream());
    }


    @RequestMapping(value = "/deleteFiles", method = RequestMethod.POST)
    @ResponseBody
    public String deleteFiles(@RequestParam List<String> radio){

        if(radio == null){
            System.out.println("nuuulll");
        }

        else {
            for(String string : radio){
                System.out.println(string);
            }
        }
        return "safe";
    }
}
