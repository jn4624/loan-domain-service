package com.loan.app.controller;

import com.loan.app.dto.ResponseDTO;
import com.loan.app.dto.ResultObject;

public abstract class AbstractController {
    protected <T> ResponseDTO<T> ok() {
        return ok(ResultObject.getSuccess(), null);
    }

    protected <T> ResponseDTO<T> ok(T data) {
        return ok(ResultObject.getSuccess(), data);
    }

    protected <T> ResponseDTO<T> ok(ResultObject result, T data) {
        ResponseDTO<T> obj = new ResponseDTO<>();
        obj.setData(data);
        obj.setResult(result);
        return obj;
    }
}
