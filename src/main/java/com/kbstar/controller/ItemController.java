package com.kbstar.controller;

import com.kbstar.dto.Item;
import com.kbstar.dto.ItemSearch;
import com.kbstar.dto.Marker;
import com.kbstar.dto.MarkerSearch;
import com.kbstar.service.ItemService;
import com.kbstar.util.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemService itemService;

    String dir = "item/";

    @Value("${uploadimgdir}")  // application properties 안에서 설정해준 imgdir 경로를 가져옴
    String imgdir;

    @RequestMapping("/add")
    public String add(Model model){
        model.addAttribute("center", dir+"add");
        return "index";
    }

    @RequestMapping("/addimpl")
    public String addimpl(Model model, Item item) throws Exception {
        MultipartFile mf = item.getImg();
        String imgname = mf.getOriginalFilename();
        log.info("----------------------------------------");
        log.info(imgname);
        log.info("----------------------------------------");
        item.setImgname(imgname);
        itemService.register(item);
        FileUploadUtil.saveFile(mf,imgdir);
        return "redirect:/item/all";
    }

    @RequestMapping("/all")
    public String all(Model model) throws Exception {
        List<Item> list = null;
        list = itemService.get();
        model.addAttribute("clist", list);
        model.addAttribute("center", dir+"all");
        return "index";
    }
    @RequestMapping("/detail")
    public String detail(Model model, int id) throws Exception {
        Item item = null;
        item= itemService.get(id);
        model.addAttribute("gitem", item);
        model.addAttribute("center", dir+"detail");
        return "index";
    }

    @RequestMapping("/updateimpl")
    public String updateimpl(Model model, Item item) throws Exception {
        MultipartFile mf = item.getImg();
        String new_imgname = mf.getOriginalFilename();
        if(new_imgname.equals("") || new_imgname == null){
            itemService.modify(item);
        }else{
            item.setImgname(new_imgname);
            itemService.modify(item);
            FileUploadUtil.saveFile(mf,imgdir);
        }
        return "redirect:/item/detail?id="+item.getId();
    }

    @RequestMapping("/deleteimpl")
    public String deleteimpl(Model model, int id) throws Exception {
        itemService.remove(id);
        return "redirect:/item/all";
    }

    @RequestMapping("/search")
    public String search(Model model, ItemSearch is) throws Exception {
        List<Item> list = itemService.search(is);
        model.addAttribute("is", is);
        model.addAttribute("clist", list);
        model.addAttribute("center", dir+"all");

        return "index";
    }

}