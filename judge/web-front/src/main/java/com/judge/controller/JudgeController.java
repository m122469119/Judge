package com.judge.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.judge.biz.JudgeBiz;
import com.judge.po.Judge;
import com.judge.utils.JsonUtils;
import com.judge.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class JudgeController {    // 自动注入UserService
    @Autowired
    private JudgeBiz judgeBiz;

    @RequestMapping(value = "/newJudge" , method = RequestMethod.POST)
    public void newJudge(String data, HttpServletRequest request,HttpServletResponse response){
        JSONObject resultObj = new JSONObject();
        JSONArray judge_json = JSON.parseArray(data);
        List<Judge> judgeList = new ArrayList<Judge>();
        try{
            for (int i = 0; i < judge_json.size(); i++) {
                Judge judge = new Judge();
                judge.setjAffairId(judge_json.getJSONObject(i).getInteger("jAffairId"));
                judge.setjEvaluatorId(judge_json.getJSONObject(i).getInteger("jEvaluatorRoleId"));
                judge.setjProjectId(judge_json.getJSONObject(i).getInteger("jProjectId"));
                judge.setjEvaluatorId(judge_json.getJSONObject(i).getInteger("jEvaluatorId"));
                judge.setjEvaluatedId(judge_json.getJSONObject(i).getInteger("jEvaluatedId"));
                judge.setjAtitude(judge_json.getJSONObject(i).getInteger("jAtitude"));
                judge.setjQualityEfficient(judge_json.getJSONObject(i).getInteger("jQualityEfficient"));
                judge.setjComplishment(judge_json.getJSONObject(i).getInteger("jComplishment"));
                judge.setjReason(judge_json.getJSONObject(i).getString("jReason"));
                judge.setDatetime(new Date());
                judgeList.add(judge);
            }
            judgeBiz.newJudgeList(judgeList);
        }catch (Exception e){
            resultObj.put("code", "1");
            resultObj.put("desc", "error:"+e.getMessage());
            ResponseUtils.renderJson(response, JsonUtils.toJson(resultObj));
            return;
        }
        resultObj.put("code", "0");
        resultObj.put("desc", "success");
        ResponseUtils.renderJson(response, JsonUtils.toJson(resultObj));
        return;
    }
}
