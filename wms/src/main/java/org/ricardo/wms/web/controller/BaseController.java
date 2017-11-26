package org.ricardo.wms.web.controller;

import org.ricardo.wms.util.JsonResult;

public class BaseController {
    protected JsonResult success() {
        return success("操作成功");
    }

    protected JsonResult success(String msg) {
        JsonResult result = new JsonResult();
        result.setSuccess(true);
        result.setMsg(msg);
        return result;
    }
    protected JsonResult failed(){
        return failed("操作失败");
    }

    protected JsonResult failed(String msg) {
        return new JsonResult(msg);
    }
}
