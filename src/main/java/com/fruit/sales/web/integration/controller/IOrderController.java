package com.fruit.sales.web.integration.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fruit.sales.common.BusinessConstant;
import com.fruit.sales.dto.UserOrder;
import com.fruit.sales.entity.Assign;
import com.fruit.sales.entity.OrderAddress;
import com.fruit.sales.entity.PubConfig;
import com.fruit.sales.service.AssignService;
import com.fruit.sales.service.OrderAddressService;
import com.fruit.sales.serviceImpl.PubConfigServiceImpl;
import com.fruit.sales.web.integration.common.UserOrderConstant;
import com.fruit.sales.web.integration.service.IUserOrderService;
import com.fruit.sales.wechat.RestultCode;
import com.fruit.sales.wechat.ReturnResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

//@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/rest/order")
public class IOrderController {
	
	private static final Logger logger = LoggerFactory.getLogger(IOrderController.class);
	
	@Autowired
	private IUserOrderService iUserOrderService;

	@Autowired
	private AssignService assinService;


	@Autowired
	private OrderAddressService orderAddressService;

	@Autowired
	private PubConfigServiceImpl pubConfigServiceImpl;

	@RequestMapping(value="waitForNums/{wechatId}", method = RequestMethod.GET)
	public @ResponseBody ReturnResult getCurrentMonthWaitForOrderNums(@PathVariable String wechatId) throws JsonProcessingException{
		logger.info("get user wait for order nums current month with wechatId:{}", wechatId);
		ReturnResult rr = new ReturnResult();
		int currentNums = iUserOrderService.getWaitOrderNumsCurrMonth(wechatId);

		PubConfig pubcfg = pubConfigServiceImpl.findByName(BusinessConstant.MAX_WAITFOR_ORDER_NUM);

		String maxNumConfig = "";

		if(pubcfg != null){
			maxNumConfig = pubcfg.getValue();
			rr.setCode(RestultCode.SUCCESS.toString());
		}else{
			maxNumConfig = BusinessConstant.MAX_WAITFOR_ORDER_NUM_UNDEFINED;
			rr.setCode(RestultCode.EXCEPTION.toString());
		}

		HashMap<String, Object> map = new HashMap<>();
		map.put("maxNumConfig", maxNumConfig);
		map.put("currentNums", currentNums);

		ObjectMapper mapper = new ObjectMapper();
		String json =  mapper.writeValueAsString(map);
		rr.setMsg(json);

		rr.setValue(BusinessConstant.PROCESS_SUCCESS);

		return rr;

	}
	
	@RequestMapping(value="cancleOrder/{orderId}", method = RequestMethod.GET)
	public @ResponseBody ReturnResult cancleOrder(@PathVariable String orderId) throws JsonProcessingException{
		
		logger.info("cancleOrder, wechatId:{} and orderId:{}", orderId);
		ReturnResult rr = new ReturnResult();
		
		if(StringUtils.isNotEmpty(orderId)){
			rr = iUserOrderService.cancleUserOrder(orderId);
			
		}else{
			rr.setCode(RestultCode.FAIL.toString());
			rr.setValue(BusinessConstant.PARAM_NOT_CORRECT);
		}
		
		return rr;
	}
	
	
	@RequestMapping(value="query/{wechatId}/{status}", method = RequestMethod.GET)
	public @ResponseBody ReturnResult queryOrder(@PathVariable String wechatId, @PathVariable String status) throws JsonProcessingException{
		logger.info("queryOrder, wechatId:{} and status:{}", wechatId, status);
		ReturnResult rr = new ReturnResult();
		
		if(StringUtils.isNotEmpty(wechatId) && StringUtils.isNotEmpty(status)){
			
			rr = iUserOrderService.queryUserOrder(wechatId, status);
			
		}else{
			rr.setCode(RestultCode.FAIL.toString());
			rr.setValue(BusinessConstant.PARAM_NOT_CORRECT);
		}
		
		return rr;
		
	}
	
	@RequestMapping(value="queryAddress/{wechatId}", method = RequestMethod.GET)
	public @ResponseBody ReturnResult queryOrderAddress(@PathVariable String wechatId) throws JsonProcessingException{
		logger.info("queryOrderAddress, wechatId:{}", wechatId);
		ReturnResult rr = new ReturnResult();
		
		if(StringUtils.isNotEmpty(wechatId)){
			rr = iUserOrderService.queryUserAddress(wechatId);
		}else{
			rr.setCode(RestultCode.FAIL.toString());
			rr.setValue(BusinessConstant.PARAM_NOT_CORRECT);
		}
		
		return rr;
	}
	
	@RequestMapping(value="delAddress/{addressId}", method = RequestMethod.GET)
	public @ResponseBody ReturnResult deleteOrderAddress(@PathVariable String addressId) throws JsonProcessingException{
		logger.info("deleteOrderAddress, addressId:{}", addressId);
		ReturnResult rr = new ReturnResult();
		
		if(StringUtils.isNotEmpty(addressId)){
			rr = iUserOrderService.deleteUserAddress(addressId);
		}else{
			rr.setCode(RestultCode.FAIL.toString());
			rr.setValue(BusinessConstant.PARAM_NOT_CORRECT);
		}
		
		return rr;
	}

	@RequestMapping(value="defaultAddr/{wechatId}/{addrId}", method = RequestMethod.GET)
	public @ResponseBody ReturnResult ConfigDefaultAddr(@PathVariable String wechatId, @PathVariable String addrId) {
		ReturnResult rr = new ReturnResult();

		OrderAddress orderAddr = orderAddressService.findById(addrId);
		Assert.notNull(orderAddr, "cannot find this address by id:" + addrId);

		orderAddressService.setDefaultAddr(wechatId, addrId);

		rr.setCode(RestultCode.SUCCESS.toString());
		rr.setValue(BusinessConstant.PROCESS_SUCCESS);
		rr.setMsg(null);
		return rr;
	}

	@RequestMapping(value="userOrder/{wechatId}", method = RequestMethod.POST)
	public @ResponseBody ReturnResult userOrder(@RequestBody UserOrder userOrder, @PathVariable String wechatId)
			throws JsonProcessingException {

		logger.info("user order, get wechatId:{} and order:\n{}", wechatId, userOrder);

		return handleUserOrder(userOrder, wechatId, BusinessConstant.NOT_DEFAULT_ADDRESS);
	}

	@RequestMapping(value="userOrder/{wechatId}/{isDefaultAddr}", method = RequestMethod.POST)
	public @ResponseBody ReturnResult userOrder(
			@RequestBody UserOrder userOrder, @PathVariable String wechatId, @PathVariable Integer isDefaultAddr)
				throws JsonProcessingException{
		logger.info("user order, get wechatId:{}, isDefaultAddr:{}, and order:\n{}", wechatId, isDefaultAddr, userOrder);

		return handleUserOrder(userOrder, wechatId, isDefaultAddr);

	}

	private ReturnResult handleUserOrder(UserOrder userOrder, String wechatId, Integer isDefaultAddr)
			throws JsonProcessingException{
		ReturnResult rr = new ReturnResult();

		if(StringUtils.isNotEmpty(wechatId)){
			Assign assign = assinService.findByWechatId(wechatId);

			if(null != assign){

				//用户是否激活
				if(assign.getIsActive().equals(BusinessConstant.NOT_ACTIVE)){
					logger.info("exception: wechat/user not active.");
					rr.setCode(RestultCode.EXCEPTION.toString());
					rr.setValue(UserOrderConstant.USER_NOT_ACTIVE);
				}else{
					logger.info("prepare to save order.");
					rr = iUserOrderService.order(assign, userOrder, isDefaultAddr);
				}

			}else{
				logger.info("exception: user not exists.");
				//用户验证未通过
				rr.setCode(RestultCode.FAIL.toString());
				rr.setValue(UserOrderConstant.USER_NOT_EXIST);
			}

		}else{
			logger.info("exception: param not correct!");
			rr.setCode(RestultCode.FAIL.toString());
			rr.setValue(BusinessConstant.PARAM_NOT_CORRECT);
		}

		return rr;
	}
}



