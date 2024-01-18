package com.loan.app.dto;

import com.loan.app.exception.BaseException;
import com.loan.app.exception.ResultType;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultObject implements Serializable {
    public String code;
    public String desc;

    public ResultObject(ResultType resultType) {
        this.code = resultType.getCode();
        this.desc = resultType.getDesc();
    }

    public ResultObject(ResultType resultType, String message) {
        this.code = resultType.getCode();
        this.desc = message;
    }

    public ResultObject(BaseException ex) {
        this.code = ex.getCode();
        this.desc = ex.getDesc();
    }

    public static ResultObject getSuccess() {
        return new ResultObject(ResultType.SUCCESS);
    }
}
