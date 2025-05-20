package com.example.springbootworks.service;

import com.example.springbootworks.domain.TbVehicleInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Administrator
* @description 针对表【tb_vehicle_info(车辆信息表)】的数据库操作Service
* @createDate 2025-05-18 07:01:03
*/
public interface TbVehicleInfoService extends IService<TbVehicleInfo> {

    TbVehicleInfo getByCarId(Integer carId);
}
