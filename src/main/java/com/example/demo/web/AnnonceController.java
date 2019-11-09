package com.example.demo.web;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Annonce;
import com.example.demo.model.User;
import com.example.demo.service.AnnonceService;

@Controller
public class AnnonceController {

	@Autowired
	AnnonceService annonceService;
	
	@GetMapping("/annonce")
	public ModelAndView allAnnonce(@RequestParam(defaultValue = "0") int page) {
		ModelAndView view = new ModelAndView("annonce");
		Page<Annonce> result = annonceService.allAnnonce(page);
		view.addObject("nbrResult", result.getTotalElements());
		view.addObject("nbrPage", result.getTotalPages()-1);
		view.addObject("result", result.getContent());
		return view;
	}
	
	@GetMapping("/admin")
	public ModelAndView getadmin() {
		ModelAndView view = new ModelAndView("admin");
		List<Annonce> result = annonceService.findAllByModerationFalse();
		view.addObject("nbrResult", result.size()-1);
		view.addObject("result", result);
		return view;
	}
	@GetMapping("/admin/moderate")
	public ModelAndView postadmin(@RequestParam("moderated")List<Integer>annonceModerated) {
		annonceService.moderated(annonceModerated);
		return new ModelAndView("redirect:/admin");
	}
	
	@GetMapping("/annonce/{id}")
	public ModelAndView oneAnnonce(@PathVariable int id) {
		ModelAndView view = new ModelAndView("viewAnnonce");
		ModelAndView error = new ModelAndView("error");
		Optional<Annonce> find = annonceService.oneAnnonce(id);
		if(find.isPresent()) {
			view.addObject("id", find.get().getAnnonceId());
			view.addObject("title", find.get().getTitle());
			view.addObject("date", find.get().getPostDate());
			view.addObject("price", find.get().getPrice());
			view.addObject("category", find.get().getCategory());
			view.addObject("content", find.get().getContent());
			view.addObject("username", find.get().getUtilisateur().getUsername());
			
			return view;
		} else {
			return error;
		}
	}
	
	@GetMapping(value = "/annonce/delete/{id}")
	public ModelAndView remove(@PathVariable int id) {
		annonceService.remove(id);
		return new ModelAndView("redirect:/annonce");
	}
	
	@GetMapping("/create")
	public ModelAndView createAnnonce(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("create", "annonce", new Annonce());
		String username = request.getUserPrincipal().getName();
		view.addObject("username", username);
		return view;
	}
	
	@RequestMapping(value = "/search")
	public ModelAndView search(@RequestParam(defaultValue = "0") int page) {
		ModelAndView view = new ModelAndView("search", "annonce", new Annonce());
		return view;
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(@Valid @RequestParam(defaultValue = "0") int page, @ModelAttribute("annonce")final Annonce annonce, final BindingResult result, final ModelMap model) {
		if (result.hasErrors()) {
				return "error";
		}
		else {
			Pageable pageable = PageRequest.of(0, 5);
			Page<Annonce> allResult = annonceService.searchFilter(annonce.getTitle(), annonce.getContent(), pageable);
			if(allResult.isEmpty()) {
				return "error";
			} else {
				model.addAttribute("foundNbr", allResult.getTotalPages()-1);
				model.addAttribute("found", allResult.getContent());
				return "search";
			}
		}
	}
	@PostMapping(value="/addAnnonce")
	public String addOne(@Valid @ModelAttribute("annonce")final Annonce annonce, final BindingResult result, final ModelMap model) {
		if (result.hasErrors()) {
			return "create";
		}
		User utilisateur = annonceService.findUser(annonce.getUsername());
		Annonce returned = new Annonce();
		returned.setUtilisateur(utilisateur);
		returned.setCategory(annonce.getCategory());
		returned.setContent(annonce.getContent());
		returned.setPrice(annonce.getPrice());
		returned.setTitle(annonce.getTitle());
		model.addAttribute("title", returned.getTitle());
		model.addAttribute("date", returned.getPostDate());
		model.addAttribute("price", returned.getPrice());
		model.addAttribute("category", returned.getCategory());
		model.addAttribute("content", returned.getContent());
		model.addAttribute("username", returned.getUtilisateur().getUsername());
		
		annonceService.save(returned);
		
		return "viewAnnonce";
	}
}
