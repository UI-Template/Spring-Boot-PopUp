package com.sb.dummy.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DummyController {
	@Autowired
	private HomeRepo repository;

	@RequestMapping("/addUser")
	public String saveUser(@ModelAttribute("user") User user, Model model) {
		repository.save(user);
		model.addAttribute("msg", "Record Saved successfully..");
		List<User> users = repository.findAll();
		model.addAttribute("users", users);
		return "home";
	}

	@RequestMapping("/getUserbyId")
	public String getUser(@RequestParam("name") String name, Model model) {
		model.addAttribute("users",
				repository.findAll().stream().filter(u -> u.getName().equalsIgnoreCase(name)).findAny().get());
		return "home";

	}

}
