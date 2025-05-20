package com.example.springbootworks.service;

import com.example.springbootworks.domain.TbWarnInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springbootworks.dto.WarnRequest;
import com.example.springbootworks.vo.WarnResult;

import java.util.List;

/**
* @author Administrator
* @description 针对表【tb_warn_info(预警信息表)】的数据库操作Service
* @createDate 2025-05-18 07:01:03
*/
public interface TbWarnInfoService extends IService<TbWarnInfo> {

    List<WarnResult> checkWarnings(List<WarnRequest> requests);
}
