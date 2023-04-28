package com.lantu.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统日志信息表
 * </p>
 *
 * @author lina
 * @since 2023-04-23
 */
@TableName("x_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long createBy;

    private LocalDateTime createTime;

    private String remarks;

    private Long updateBy;

    private LocalDateTime updateTime;

    private String area;

    private String browser;

    private String city;

    private String classMethod;

    private String exception;

    private String httpMethod;

    private String isp;

    private String params;

    private String province;

    private String remoteAddr;

    private String requestUri;

    private String response;

    private String sessionId;

    private String title;

    private String type;

    private Long useTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public String getClassMethod() {
        return classMethod;
    }

    public void setClassMethod(String classMethod) {
        this.classMethod = classMethod;
    }
    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }
    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }
    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }
    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }
    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public Long getUseTime() {
        return useTime;
    }

    public void setUseTime(Long useTime) {
        this.useTime = useTime;
    }

    @Override
    public String toString() {
        return "Menu{" +
            "id=" + id +
            ", createBy=" + createBy +
            ", createTime=" + createTime +
            ", remarks=" + remarks +
            ", updateBy=" + updateBy +
            ", updateTime=" + updateTime +
            ", area=" + area +
            ", browser=" + browser +
            ", city=" + city +
            ", classMethod=" + classMethod +
            ", exception=" + exception +
            ", httpMethod=" + httpMethod +
            ", isp=" + isp +
            ", params=" + params +
            ", province=" + province +
            ", remoteAddr=" + remoteAddr +
            ", requestUri=" + requestUri +
            ", response=" + response +
            ", sessionId=" + sessionId +
            ", title=" + title +
            ", type=" + type +
            ", useTime=" + useTime +
        "}";
    }
}
