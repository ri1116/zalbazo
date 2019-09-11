package kr.zalbazo.controller.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.zalbazo.model.content.Content;
import kr.zalbazo.model.content.Criteria;
import kr.zalbazo.model.content.PageDTO;
import kr.zalbazo.service.content.ContentService;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping({ "/jisikdong/**" })
public class JisikDongController {
	private static final Long JISIKDONG_CATEGORY_NUM = 2L;
	
	@Autowired
	private ContentService service;
	
	@GetMapping("/register")
	public String register() {
		return "jisikdong/register"; // WEB-INF/views/register.jsp
	}

	@PostMapping("/register")
	public String register(Content content, RedirectAttributes rttr) {
		content.setCategoryId(JISIKDONG_CATEGORY_NUM);
		service.register(content);
		rttr.addFlashAttribute("result", content);
		return "redirect:/jisikdong/list";
	}

	@GetMapping("/list")
	public void list(Criteria cri, Model model, Content content) {

		cri.setCategory(JISIKDONG_CATEGORY_NUM);
		model.addAttribute("contentList", service.getList(cri));
		int total = service.getTotal(cri);
		log.info("list : " + cri);
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}

	@GetMapping({ "/get", "/modify" })
	public void detail(@RequestParam("contentId") Long contentId, Model model, @ModelAttribute("cri") Criteria cri) {
		model.addAttribute("content", service.get(contentId));
	}

	@PostMapping("/modify")
	public String modify(Content content, RedirectAttributes rttr, @ModelAttribute("cri") Criteria cri) {
		if (service.modify(content)) {
			rttr.addFlashAttribute("result", "success");
		}


		return "redirect:/jisikdong/list" + cri.getListLink();
	}

	@PostMapping("/remove")
	public String remove(@RequestParam("contentId") Long contentId, RedirectAttributes rttr, @ModelAttribute("cri") Criteria cri) {
		if (service.remove(contentId)) {
			rttr.addFlashAttribute("result", "success");
		}


		return "redirect:/jisikdong/list" + cri.getListLink();
	}

}