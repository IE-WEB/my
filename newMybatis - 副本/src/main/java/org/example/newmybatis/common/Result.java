package org.example.newmybatis.common;

public class Result {
    private String code;
    private String message;
    private Object data;

    public static Result error(){
        Result success = Result.success();
        success.setMessage("系统错误");
        success.setCode("500");
        return  success;
    }

    public static Result success(Object data) {
        Result success = Result.success();
        success.setData(data);
        return success;
    }

    public static Result success(){
        Result result = new Result();
        result.setCode("200");
        result.setMessage("success");
        result.data = null;
        return result;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Result() {
    }

    public Result(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
