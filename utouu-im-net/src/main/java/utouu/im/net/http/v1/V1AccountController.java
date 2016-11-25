package utouu.im.net.http.v1;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import utouu.im.annotation.ILoginNoCheck;
import utouu.im.net.GlobalServerSender;
import utouu.im.net.http.BaseController;
@RestController
@RequestMapping(value="/v1")
public class V1AccountController extends BaseController{
	@RequestMapping(value="/registAccount")
	@ILoginNoCheck
	public String registAccount(HttpServletRequest request){
		System.out.println("##########注册账户,生成utouuImAccount与utouuImPwd");
		return null;
	}
	
	@RequestMapping(value="/sendMsg")
	@ILoginNoCheck
	public String sendMsg(HttpServletRequest request){
		System.out.println("##########向服务器sdk用户主动推数据");
		String toAccountString = request.getParameter("toAccount");
		String[] toAccounts = toAccountString.split(",");
		String chatText = request.getParameter("chatText");
		try {
			GlobalServerSender.sendMsg(toAccounts,chatText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
