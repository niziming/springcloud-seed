package cn.zm.common.config;

import cn.zm.common.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

/**
 * @author Mr_W
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(value = Exception.class)
	public ResponseResult internalErrorHandler(Exception e) {
		log.error("happened serviceException, Caused by " +
				"\n\n ------------------------------------" +
				"--------------ERROR INFO------------" +
				"--------------------------------------"
				+ getMessage(e), e);
		ResponseResult r;
		if (e instanceof ServiceException) {
			r = ResponseResult.fail(StringUtils.isBlank(((ServiceException) e).getAlertMessage()) ? e.getMessage() : ((ServiceException) e).getAlertMessage());
		} else if (e instanceof IllegalArgumentException){
			r = ResponseResult.fail(e.getMessage());
		} else {
			r = ResponseResult.fail(null);
		}
		return r;
	}
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseResult paramErrorHandler(MethodArgumentNotValidException e) {
		BindingResult exceptions = e.getBindingResult();
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
                FieldError fieldError = (FieldError) errors.get(0);
                log.error("invalid parameter, Caused by " + fieldError.getDefaultMessage(), e);
                return ResponseResult.fail(fieldError.getDefaultMessage());
            }
        }
		return ResponseResult.fail(ResultEnum.INVALID_PARAMS.getMsg());
	}
	
	private String getMessage(Exception e) {
		StringWriter sw = new StringWriter();
		try (PrintWriter pw = new PrintWriter(sw)) {
			pw.flush();
			sw.flush();
		}
		return sw.toString();
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseResult handlerMissingServletRequestParameterException(MissingServletRequestParameterException e) {
		log.debug(e.getParameterName() + "不能为空", e);
		return ResponseResult.fail(e.getParameterName() + "不能为空");
	}

	@ExceptionHandler(BindException.class)
	public ResponseResult handlerBindException(BindException e) {
		log.debug(e.getAllErrors().get(0).getDefaultMessage(), e);
		return ResponseResult.fail(e.getAllErrors().get(0).getDefaultMessage());
	}

	@ExceptionHandler(MultipartException.class)
	public ResponseResult handleError1(MultipartException e) {
		log.error("文件解析失败", e);
		return ResponseResult.fail("文件解析失败");
	}

}
