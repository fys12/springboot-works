package com.example.springbootworks.mapper;

import com.example.springbootworks.domain.TbVehicleInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Administrator
* @description 针对表【tb_vehicle_info(车辆信息表)】的数据库操作Mapper
* @createDate 2025-05-18 07:01:03
* @Entity com.example.springbootworks.domain.TbVehicleInfo
*/
@Mapper
public interface TbVehicleInfoMapper extends BaseMapper<TbVehicleInfo> {

}




