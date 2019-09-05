package kr.zalbazo.controller.content;

import kr.zalbazo.model.content.Content;
import kr.zalbazo.service.ContentService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Log4j
@RequestMapping({"/jisikdong/*"})
public class JisikDongController {

    private static final Long JISIKDONG_CATEGORY_NUM = 2L;

    @Autowired
    private ContentService service;

    @GetMapping("/register")
    public String register(){
        return "/jisikdong/register"; //WEB-INF/views/register.jsp
    }

    @PostMapping("/register")
    public String register(Content content, RedirectAttributes rttr){
        content.setCategoryId(JISIKDONG_CATEGORY_NUM);

        service.register(content);
        rttr.addFlashAttribute("result", content.getId());

        return "redirect:/jisikdong/list";
    }

    @GetMapping("/list")
    public void list(Model model){

        model.addAttribute("contentList", service.getList(JISIKDONG_CATEGORY_NUM));
        service.getList(JISIKDONG_CATEGORY_NUM ).stream().forEach(System.out::println);
    }

    @GetMapping("/get")
    public void detail(@RequestParam("id") Long id, Model model){
        model.addAttribute("content", service.get(id));
    }

    @PostMapping("/modify")
    public String modify(Content content, RedirectAttributes rttr){
        if(service.modify(content)){
            rttr.addFlashAttribute("result", content.getId());
        }
        return "redirect:/jisikdong/list";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("id") Long id, RedirectAttributes rttr){
        if(service.remove(id)){
            rttr.addFlashAttribute("result", "success");
        }
        return "redirect:/jisikdong/list";
    }
}