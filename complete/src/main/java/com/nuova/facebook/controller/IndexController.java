package com.nuova.facebook.controller;

import javax.inject.Inject;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class IndexController {

	private Facebook facebook;
	private ConnectionRepository connectionRepository;

	@Inject
	public IndexController(Facebook facebook, ConnectionRepository connectionRepository) {
		this.facebook = facebook;
		this.connectionRepository = connectionRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String carregaPosts(Model model) {
		if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
			return "redirect:/connect/facebook";
		}

		model.addAttribute("facebookProfile", facebook.userOperations().getUserProfile());
		PagedList<Post> feeds = facebook.feedOperations().getFeed();

		
		model.addAttribute("feed", feeds);
		return "posts";
	}

}
