package com.study.common.exception;

import com.study.common.web.ResponseCode;
import com.study.common.web.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 全局异常处理
 */
@RestControllerAdvice("com.study")
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获全局异常,处理所有不可知的异常
     */
    @ExceptionHandler(value = Exception.class)
    public Result handle(Exception e, HttpServletRequest request) {
        e.printStackTrace();
        log.error("url:{}, msg:{}", request.getRequestURL(), e.getMessage());
        return Result.fail(e.getMessage());
    }

    /**
     * 404异常处理器
     *
     * @param e 忽略参数异常
     * @return Result
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public Result notFoundExceptionHandler(NotFoundException e) {
        return Result.result(ResponseCode.NOT_FOUND, null);
    }

    /**
     * 忽略参数异常处理器
     *
     * @param e 忽略参数异常
     * @return Result
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result parameterMissingExceptionHandler(MissingServletRequestParameterException e) {
        return Result.result(ResponseCode.BADREQUEST, "请求参数 " + e.getParameterName() + " 不能为空");
    }

    /**
     * 缺少请求体异常处理器
     *
     * @param e 缺少请求体异常
     * @return Result
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result parameterBodyMissingExceptionHandler(HttpMessageNotReadableException e) {
        return Result.result(ResponseCode.BADREQUEST, "参数体不能为空");
    }

    /**
     * 参数效验异常处理器
     *
     * @param e 参数验证异常
     * @return Result
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result parameterExceptionHandler(MethodArgumentNotValidException e) {
        // 获取异常信息
        BindingResult exceptions = e.getBindingResult();
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (null != exceptions && exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
                FieldError fieldError = (FieldError) errors.get(0);
                return Result.result(ResponseCode.BADREQUEST, fieldError.getDefaultMessage());
            }
        }
        return Result.result(ResponseCode.BADREQUEST, "请求参数不合法!");
    }

    /**
     * 自定义参数错误异常处理器
     *
     * @param e 自定义参数
     * @return Result
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ParamErrorException.class})
    public Result paramExceptionHandler(ParamErrorException e) {
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (!StringUtils.isEmpty(e.getMessage())) {
            return Result.result(ResponseCode.BADREQUEST, e.getMessage());
        }
        return Result.result(ResponseCode.BADREQUEST, "请求参数有误!");
    }

}
