package com.example.springbootworks.controller;

import com.example.springbootworks.dto.WarnRequest;
import com.example.springbootworks.service.TbWarnInfoService;
import com.example.springbootworks.vo.Result;
import com.example.springbootworks.vo.WarnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/warn")
public class WarnController {
    @Autowired
    private TbWarnInfoService warnService;

    @PostMapping
    public Result<List<WarnResult>> checkWarn(@RequestBody List<WarnRequest> requests) {
        List<WarnResult> results = warnService.checkWarnings(requests);
        return Result.success("预警查询成功",results);
    }
}