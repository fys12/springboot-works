package com.example.springbootworks.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springbootworks.domain.TbVehicleInfo;
import com.example.springbootworks.service.TbVehicleInfoService;
import com.example.springbootworks.mapper.TbVehicleInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【tb_vehicle_info(车辆信息表)】的数据库操作Service实现
* @createDate 2025-05-18 07:01:03
*/
@Service
public class TbVehicleInfoServiceImpl extends ServiceImpl<TbVehicleInfoMapper, TbVehicleInfo>
    implements TbVehicleInfoService{

    @Override
    public TbVehicleInfo getByCarId(Integer carId) {
        QueryWrapper<TbVehicleInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("car_id", carId);
        return this.getOne(wrapper);
    }
}




