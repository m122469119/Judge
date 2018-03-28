package com.judge.controller;

import com.alibaba.fastjson.JSONObject;
import com.judge.biz.OrgnizationBiz;
import com.judge.po.Orgnization;
import com.judge.utils.JsonUtils;
import com.judge.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
public class OrgnizationController {    // 自动注入UserService
    @Autowired
    private OrgnizationBiz orfbiz;

    @RequestMapping(value = "/searchUsersByproid")
    public void searchUsersByproid(int projectId,HttpServletRequest request,HttpServletResponse response){
        JSONObject resultObj = new JSONObject();
        resultObj.put("data", orfbiz.searchUsersByproid(projectId));
        resultObj.put("code", "0");
        resultObj.put("desc", "success");
        ResponseUtils.renderJson(response, JsonUtils.toJson(resultObj));
        return;
    }

    @RequestMapping(value = "/searchUsersByproidAndroleid")
    public void searchUsersByproidAndroleid(int projectId,int roleId,HttpServletRequest request,HttpServletResponse response){
        JSONObject resultObj = new JSONObject();
        resultObj.put("data", orfbiz.searchUsersByproidAndroleid(projectId,roleId));
        resultObj.put("code", "0");
        resultObj.put("desc", "success");
        ResponseUtils.renderJson(response, JsonUtils.toJson(resultObj));
        return;
    }
}
