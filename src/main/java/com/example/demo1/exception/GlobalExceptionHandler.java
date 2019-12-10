package com.example.demo1.exception;

import com.example.demo1.result.ResultVO;
import com.example.demo1.result.eunms.ResultEnum;
import com.example.demo1.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    /**
     * 顶级的异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ResultVO handleException(HttpServletRequest request, Exception e) {
        log.error("【异常拦截】" + "[" + request.getRequestURI() + "]" + "接口出现错误," + e.getMessage());
        return ResultVOUtil.returnFail(ResultEnum.SERVER_ERROR.getCode(), e.getMessage());
    }


    /**
     * 404
     *
     * @param e
     * @return
     */
    @ExceptionHandler({NoHandlerFoundException.class})
    @ResponseBody
    public ResultVO handleNoHandlerFoundException(HttpServletRequest request, NoHandlerFoundException e) {
        log.error("【页面不存在异常拦截】" + "[" + request.getRequestURI() + "]" + "接口出现错误," + e.getMessage());
        return ResultVOUtil.returnFail(ResultEnum.NO_HANDLER.getCode(),ResultEnum.NO_HANDLER.getMessage());
    }


    /**
     * 400
     *
     * @param e
     * @return
     */
    @ExceptionHandler({ServletException.class})
    @ResponseBody
    public ResultVO handleServletException(HttpServletRequest request, ServletException e) {
        log.error("【接口错误异常拦截】" + "[" + request.getRequestURI() + "]" + "接口出现错误," + e.getMessage());
        return ResultVOUtil.returnFail(ResultEnum.FAIL.getCode(),ResultEnum.FAIL.getMessage());
    }

    /**
     * 自定义的异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler({SpringException.class})
    @ResponseBody
    public ResultVO serviceExceptionHandler(HttpServletRequest request, SpringException e) {
        log.error("【自定义异常拦截】" + "[" + request.getRequestURI() + "]" + "接口出现错误," + e.getMessage());
        return ResultVOUtil.returnFail(e.getCode(), e.getMsg());
    }

    /**
     * 缺少servlet请求参数抛出的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseBody
    public ResultVO handleMissingServletRequestParameterException(HttpServletRequest request, MissingServletRequestParameterException e) {
        log.error("【缺少servlet请求参数异常拦截】" + "[" + request.getRequestURI() + "]" + "接口出现错误," + e.getMessage());
        return ResultVOUtil.returnFail(ResultEnum.MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION.getCode(), e.getMessage());
    }

    /**
     * 请求参数不能正确读取解析时，抛出的异常，比如传入和接受的参数类型不一致
     *
     * @param e
     * @return
     */
    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseBody
    public ResultVO handleHttpMessageNotReadableException(HttpServletRequest request, HttpMessageNotReadableException e) {
        log.error("【请求参数不能正确读取解析异常拦截】" + "[" + request.getRequestURI() + "]" + "接口出现错误," + e.getMessage());
        return ResultVOUtil.returnFail(ResultEnum.HTTP_MESSAGE_NOT_READABLE_EXCEPTION.getCode(), e.getMessage());
    }

    /**
     * 请求参数无效抛出的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public ResultVO handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        String message = getBindResultMessage(result);
        log.error("【请求参数无效异常拦截】" + "[" + request.getRequestURI() + "]" + "接口出现错误," + message);
        return ResultVOUtil.returnFail(ResultEnum.METHOD_ARGUMENT_NOT_VALID_EXCEPTION.getCode(), message);
    }

    private String getBindResultMessage(BindingResult result) {
        FieldError error = result.getFieldError();
        String field = error != null ? error.getField() : "空";
        String code = error != null ? error.getDefaultMessage() : "空";
        return String.format("%s:%s", field, code);
    }

    /**
     * 方法请求参数类型不匹配异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResultVO handleMethodArgumentTypeMismatchException(HttpServletRequest request, MethodArgumentTypeMismatchException e) {
        log.error("【请求参数不能正确读取解析异常拦截】" + "[" + request.getRequestURI() + "]" + "接口出现错误," + e.getMessage());
        return ResultVOUtil.returnFail(ResultEnum.METHOD_ARGUMENT_TYPE_MISMATCH_EXCEPTION.getCode(), e.getMessage());
    }

    /**
     * javax.validation:validation-api 校验参数抛出的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseBody
    public ResultVO handleServiceException(HttpServletRequest request, ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String message = violation.getMessage();
        log.error("【javax.validation:validation-api 校验参数异常拦截】" + "[" + request.getRequestURI() + "]" + "接口出现错误," + message);
        return ResultVOUtil.returnFail(ResultEnum.CONSTRAIN_VIOLATION_EXCEPTION.getCode(), message);
    }

    /**
     * javax.validation 下校验参数时抛出的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler({ValidationException.class})
    @ResponseBody
    public ResultVO handleValidationException(HttpServletRequest request, ValidationException e) {
        log.error("【javax.validation 下校验参数异常拦截】" + "[" + request.getRequestURI() + "]" + "接口出现错误," + e.getMessage());
        return ResultVOUtil.returnFail(ResultEnum.VALIDATION_EXCEPTION.getCode(), e.getMessage());
    }

    /**
     * 不支持该请求方法时抛出的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseBody
    public ResultVO handleHttpRequestMethodNotSupportedException(HttpServletRequest request, HttpRequestMethodNotSupportedException e) {
        log.error("【请求方法不支持异常拦截】" + "[" + request.getRequestURI() + "]" + "接口出现错误," + e.getMessage());
        return ResultVOUtil.returnFail(ResultEnum.HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION.getCode(), e.getMessage());
    }

    /**
     * 不支持当前媒体类型抛出的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    @ResponseBody
    public ResultVO handleHttpMediaTypeNotSupportedException(HttpServletRequest request, HttpMediaTypeNotSupportedException e) {
        log.error("【不支持当前媒体类型异常拦截】" + "[" + request.getRequestURI() + "]" + "接口出现错误," + e.getMessage());
        return ResultVOUtil.returnFail(ResultEnum.HTTP_MEDIA_TYPE_NOT_SUPPORTED_EXCEPTION.getCode(), e.getMessage());
    }

}
