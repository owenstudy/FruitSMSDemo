package com.fruit.sales.web.integration.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fruit.sales.entity.Assign;
import com.fruit.sales.service.AssignService;
import com.fruit.sales.wechat.RestultCode;
import com.fruit.sales.wechat.ReturnResult;

//@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/rest/assign")
public class IAssignContoller {

	private static final Logger logger = LoggerFactory.getLogger(IOrderController.class);
	
	@Autowired
	private AssignService assinService;
	
	@RequestMapping(value="checkBalance/{wechatId}", method = RequestMethod.GET)
	public @ResponseBody ReturnResult checkAssignBalance(@PathVariable String wechatId) throws JsonProcessingException{
		
		logger.info("checkAssignBalance, wechatId:{}", wechatId);
		
		Assign assign = assinService.findByWechatId(wechatId);
		
		ReturnResult rr = new ReturnResult();
		
		rr.setCode(RestultCode.SUCCESS.name());
		rr.setValue(String.valueOf(assign.getBalanceUnit()));
		
		return rr;
		
	}
}
