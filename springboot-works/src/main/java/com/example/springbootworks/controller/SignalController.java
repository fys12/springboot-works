package com.example.springbootworks.controller;

import com.example.springbootworks.domain.TbSignal;
import com.example.springbootworks.dto.SignalDTO;
import com.example.springbootworks.service.TbSignalService;
import com.example.springbootworks.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 信号上报查询接口
 */
@RestController
@RequestMapping("/api/signal")
public class SignalController {

    @Autowired
    private TbSignalService signalService;

    /**
     * 信号上报、修改
     * @param signalDTO
     * @return
     */
    @PostMapping("/upload")
    public Result uploadSignal(@RequestBody @Validated SignalDTO signalDTO) {
        signalService.saveOrUpdateSignal(signalDTO);
        return Result.success("信号上报成功");
    }

    /**
     * 查询电池信号状态
     * @param
     * @return
     */
    @GetMapping("/getSignalByCarId")
    public Result getSignalByCarId(@RequestParam("carId") Integer carId,@RequestParam("month") String targetMonth) {
        List<TbSignal> voList = signalService.getSignalByCarId(carId,targetMonth);
        return Result.success("查询成功", voList);
    }


    /**
     * 删除电池信号
     */
    @DeleteMapping("/deleteSignal")
    public Result deleteSignal(@RequestParam("id") Long id) {
        signalService.removeById(id);
        return Result.success("删除成功");
    }
}
