package ant.kitchens.utils;


import ant.kitchens.enums.ResultEnum;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by wolf   2018/10/26
 */

@lombok.Data
public class R   {

    private static final long serialVersionUID = 1L;

    private Status status= new Status();

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Data data ;

    public R() {
    }

    public R(ResultEnum result) {
        this.status=new Status(result);
    }

    public static R error() {
        return error(ResultEnum.UNKNOW_ERROR.getCode(),ResultEnum.UNKNOW_ERROR.getMessage() );
    }

    public static R error(String msg) {
        return error(ResultEnum.ERROR.getCode(), msg);
    }

    public static R error(int code, String msg) {
        Status status = new Status(code,msg);
        R r = new R();
        r.setStatus(status);
        return r;
    }
    public static R error(Status status) {
        R r = new R( );
        r.setStatus(status);
        return r;
    }
    public static R error(ResultEnum result) {
        Status status = new Status(result);
        R r = new R();
        r.setStatus(status);
        return r;
    }

    public static R success(String msg) {
        R r = new R();
        r.setStatus(new Status( ResultEnum.SUCCESS.getCode(), msg));
        return  r ;
    }
    //默认 【code:1】【msg:成功】
    public static R success() {
        return new R();
    }

    public  R  put(String key, Object value) {

        Data data= Optional.ofNullable( this.getData()).orElseGet( ()->new Data());
        data.put(key, value);
        this.setData(data);
        return  this;
    }

    public  R  put(Map<String, Object> map) {
        Data data = new Data(map);
        this.setData(data);
        return  this;
    }




    /*数据集*/
    class Data extends HashMap<String, Object> {

        private static final long serialVersionUID = 1L;

        public Data() {}

        public Data(String key, Object value) {
            put( key,value);
        }

        public Data(Map<String, Object> map) {
            put(map);
        }

        public  Data put(Map<String, Object> map) {

            this.putAll(map);
            return this;
        }

        public Data put(String key, Object value) {
            super.put(key, value);
            return this;
        }
    }

    /*状态集*/
    @lombok.Data
    public static class Status {

        /** 错误码. */
        private Integer code;

        /** 提示信息. */
        private String msg;

        public Status(Integer code, String msg){
            this.code=code;
            this.msg=msg;
        }
        /** 提示信息默认返回 code:1  msg：成功. */
        public Status(){
            this.code= ResultEnum.SUCCESS.getCode();
            this.msg=ResultEnum.SUCCESS.getMessage();
        }

        public Status(ResultEnum result){
            this.code= result.SUCCESS.getCode();
            this.msg=result.SUCCESS.getMessage();
        }


    }

}

