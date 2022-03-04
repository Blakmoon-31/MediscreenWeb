package com.openclassrooms.mediscreenWeb.bean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class TriggerWordBean {

	@Size(max = 20)
	@NotBlank
	private String triggerWord;
}
