package utouu.im.net.http.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.utouu.commons.open.client.utils.UtouuOpenSignUtil;

import utouu.im.annotation.ILoginNoCheck;
import utouu.im.utils.ConstantUtils;
import utouu.im.utils.StringHelper;


public class V1AccountLoginIntercepter extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod method = (HandlerMethod) handler;
			ILoginNoCheck iLoginNoCheck = method.getMethod().getAnnotation(ILoginNoCheck.class);
			if(iLoginNoCheck!=null){
				return true;
			}else{
				//校验登录
				String time = request.getHeader(ConstantUtils.CLIENT_HEADER_TIME);
				String sign = request.getHeader(ConstantUtils.CLIENT_HEADER_SIGN);
				if(StringHelper.isNullOrEmpty(time)){
					//时间没有传
					return false;
				}
				if(StringHelper.isNullOrEmpty(sign)){
					//sign没有传
					return false;
				}
				if (!UtouuOpenSignUtil.checkCommonSign(sign, Long.parseLong(time), request)) {
					//sign校验不通过
					return false;
				}
				return true;
			}
		}
		return false;
	}
}
