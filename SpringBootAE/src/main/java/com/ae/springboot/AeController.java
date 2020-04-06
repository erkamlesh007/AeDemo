package com.ae.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ae.springboot.model.AeEntity;
import com.ae.springboot.service.AirthmaticExpression;

@RequestMapping("/")
@Controller
public class AeController {

	@Autowired
	private AirthmaticExpression ae;

	@GetMapping({ "", "hello" })
	public String hello(Model model, @ModelAttribute("inputForm") InputForm inputForm) {
		model.addAttribute("name", inputForm.getName());
		return "airthmaticExp";
	}

	@PostMapping({ "/calc" })
	public String calc(Model model, @ModelAttribute("inputForm") InputForm inputForm) {
		int result = ae.eval(inputForm.getName().trim());
		model.addAttribute("input", inputForm.getName());
		model.addAttribute("name", result);
		
		/*try {
			
			AeEntity entry= new AeEntity();
			entry.setInput(inputForm.getName());
			entry.setOutput(""+result);
			entry.setId(1L);
			
			ae.createOrUpdateAirthmeticExp(entry);
		} catch (RecordNotFoundException e) {
			
			e.printStackTrace();
		}*/
		 
		return "airthmaticExp";
	}
}
