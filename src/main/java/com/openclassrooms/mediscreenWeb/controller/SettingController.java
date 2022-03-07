package com.openclassrooms.mediscreenWeb.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.openclassrooms.mediscreenWeb.bean.TriggerWordBean;
import com.openclassrooms.mediscreenWeb.service.SettingService;

@Controller
public class SettingController {

	@Autowired
	private SettingService settingService;

	@GetMapping("/settings")
	public String getTriggerWords(Model model) {
		model.addAttribute("triggerWords", settingService.getTriggerWords());
		TriggerWordBean newTriggerWord = new TriggerWordBean();
		model.addAttribute("newTriggerWord", newTriggerWord);

		return "settings";
	}

	@PostMapping("/settings/triggerWord/add")
	public String addTriggerWord(@Valid TriggerWordBean triggerWord, BindingResult result, Model model) {

		if (!result.hasErrors()) {
			settingService.addTriggerWord(triggerWord);
			model.addAttribute("triggerWords", settingService.getTriggerWords());
			return "redirect:/settings";
		}
		return "settings";
	}

	@GetMapping("/settings/triggerWord/delete/{triggerWord}")
	public String deleteTriggerWord(@PathVariable("triggerWord") String triggerWord) {
		settingService.deleteTriggerWord(triggerWord);
		return "redirect:/settings";
	}

}
