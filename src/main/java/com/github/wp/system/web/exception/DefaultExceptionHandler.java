package com.github.wp.system.web.exception;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.github.wp.system.Constants;

/**
 * <p>异常处理类
 * User: wangping
 * <p>
 * Date: 14-2-12
 * <p>
 * Version: 1.0
 */
@ControllerAdvice
public class DefaultExceptionHandler {
	/**
	 * 没有权限 异常
	 * <p/>
	 * 后续根据不同的需求定制即可
	 */
	@ExceptionHandler({ UnauthorizedException.class })
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ModelAndView processUnauthenticatedException(NativeWebRequest request, UnauthorizedException e) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("exception", e);
		mv.setViewName("unauthorized");
		return mv;
	}

	/**
	 * 默认的异常处理方法
	 * @param e
	 * @return
	 * @author wangping
	 */
	@ExceptionHandler({ Exception.class })
	@ResponseBody
	public Map<String, Object> processException(Exception e) {
		e.printStackTrace();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constants.Others.RESPONSE_MSG.value(), Constants.Others.SYSTEM_EXCEPTION.value());
		return map;
	}

	@InitBinder
	public void initListBinder(WebDataBinder binder) {
		// 设置需要包裹的元素个数，默认为256
		binder.setAutoGrowCollectionLimit(500);
	}
}
