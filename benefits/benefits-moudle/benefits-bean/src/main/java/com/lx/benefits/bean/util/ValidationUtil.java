package com.lx.benefits.bean.util;

import org.apache.commons.collections.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.util.Set;


public class ValidationUtil {
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * 校验方法
     * @param t 将要校验的对象
     * @throws ValidationException
     * void
     */
    public static <T> void validate(T t) throws ValidationException {
        Set<ConstraintViolation<T>> set =  validator.validate(t);
        if(CollectionUtils.isNotEmpty(set)){
            StringBuilder validateError = new StringBuilder();
            for(ConstraintViolation<T> val : set){
                validateError.append(val.getMessage() + ";");
            }
            throw new ValidationException(validateError.toString());
        }
    }
}
