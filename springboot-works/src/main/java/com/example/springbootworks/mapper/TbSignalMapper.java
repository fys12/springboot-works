package com.example.springbootworks.mapper;

import com.example.springbootworks.domain.TbSignal;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Administrator
* @description 针对表【tb_signal(电池信号表)】的数据库操作Mapper
* @createDate 2025-05-18 07:01:02
* @Entity com.example.springbootworks.domain.TbSignal
*/
@Mapper
public interface TbSignalMapper extends BaseMapper<TbSignal> {

}




