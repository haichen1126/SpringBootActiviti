/**
 *
 */
package com.hc.config;

import io.swagger.annotations.ApiModelProperty;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * @author 宋千义
 * <p>Description:返回类型</p>
 * 2018年7月31日
 */
public class ResponseBean<E> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2283416827691016207L;
    //返回状态码
    @ApiModelProperty("返回状态码")
    private int code;
    //返回消息
    @ApiModelProperty("返回消息")
    private String msg;
    // 返回记录条数
    @ApiModelProperty("返回记录条数")
    private Long count;
    //返回数据
    @ApiModelProperty("返回数据")
    private Object data;
    //返回数据1
    @ApiModelProperty("返回数据1")
    private Object data1;
    //返回数据2
    @ApiModelProperty("返回数据2")
    private Object data2;

    public Object getData2() {
        return data2;
    }

    public void setData2(Object data2) {
        this.data2 = data2;
    }

    //列表树结构层级
    @ApiModelProperty("总数")
    private int total;
    //列表树结构数据
    @ApiModelProperty("页数")
    private Object rows;

    public ResponseBean() {
        super();
    }

    public ResponseBean(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseBean(int code, String msg, Long count, Object data) {
        super();
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData1() {
        return data1;
    }

    public void setData1(Object data1) {
        this.data1 = data1;
    }


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Object getRows() {
        return rows;
    }

    public void setRows(Object rows) {
        this.rows = rows;
    }

    public ResponseBean(int code, String msg, Object data, Object data1) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.data1 = data1;
    }

    public ResponseBean(int code, String msg, Object data, Object data1, Object data2) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.data1 = data1;
        this.data2 = data2;
    }

    public ResponseBean(int code, String msg, int total, Object rows) {
        super();
        this.code = code;
        this.msg = msg;
        this.total = total;
        this.rows = rows;
    }

    public static <T> ResponseBean<T> success(T data) {
        return new ResponseBean(HttpServletResponse.SC_OK, "success", data);
    }

    public static <T> ResponseBean<T> fail(T errorData) {
        return new ResponseBean(HttpServletResponse.SC_OK, "error", errorData);
    }

    public static <T> ResponseBean<T> exception(T errorData) {
        return new ResponseBean(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "error", errorData);
    }


}
