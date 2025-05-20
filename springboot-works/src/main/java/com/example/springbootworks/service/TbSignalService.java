package com.example.springbootworks.service;

import com.example.springbootworks.domain.TbSignal;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springbootworks.dto.SignalDTO;

import java.util.List;

/**
* @author Administrator
* @description 针对表【tb_signal(电池信号表)】的数据库操作Service
* @createDate 2025-05-18 07:01:03
*/
public interface TbSignalService extends IService<TbSignal> {

    void saveOrUpdateSignal(SignalDTO signalDTO);

    List<TbSignal> getSignalByCarId(Integer carId,String targetMonth);

    void markProcessed(Long id);
}
