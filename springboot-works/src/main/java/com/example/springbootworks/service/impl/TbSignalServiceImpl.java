package com.example.springbootworks.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springbootworks.domain.TbSignal;
import com.example.springbootworks.dto.SignalDTO;
import com.example.springbootworks.service.TbSignalService;
import com.example.springbootworks.mapper.TbSignalMapper;
import com.example.springbootworks.utils.DynamicTableNameUtil;
import com.example.springbootworks.utils.RedisUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
* @author Administrator
* @description 针对表【tb_signal(电池信号表)】的数据库操作Service实现
* @createDate 2025-05-18 07:01:03
*/
@Service
public class TbSignalServiceImpl extends ServiceImpl<TbSignalMapper, TbSignal> implements TbSignalService{
    @Resource
    private TbSignalMapper signalMapper;

    @Resource
    private RedisUtils redisUtils;

    public void saveOrUpdateSignal(SignalDTO dto) {
        // 分表名生成：tb_signal_yyyyMM
        String tableName = "tb_signal_" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMM"));
        DynamicTableNameUtil.setTableName(tableName);
        TbSignal entity = new TbSignal();
        BeanUtils.copyProperties(dto, entity);
        entity.setProcessed(0);
        entity.setSignalTime(LocalDateTime.now());
        if (Objects.isNull(entity.getId())) {
            signalMapper.insert(entity);
        } else {
            signalMapper.updateById(entity);
        }
        // 清除Redis缓存
        redisUtils.del("signal:carId:" + dto.getCarId());
        DynamicTableNameUtil.clear();
    }

    public List<TbSignal> getSignalByCarId(Integer carId, String targetMonth) {
        // 生成带月份的缓存键（例如 signal:carId:123:202404）
        String cacheKey = "signal:carId:" + carId + ":" + targetMonth;

        // 先查 Redis 缓存
        List<TbSignal> cacheData = redisUtils.getList(cacheKey, TbSignal.class);
        if (!CollectionUtils.isEmpty(cacheData)) {
            return cacheData;
        }

        // 设置动态表名（例如 tb_signal_202404）
        DynamicTableNameUtil.setTableName("tb_signal_" + targetMonth);
        try {
            // 查询数据库（分表插件会自动替换表名）
            QueryWrapper<TbSignal> query = new QueryWrapper<TbSignal>()
                    .eq("car_id", carId);
            List<TbSignal> dbData = signalMapper.selectList(query);
            if (!CollectionUtils.isEmpty(dbData)) {
                // 更新缓存
                redisUtils.setList(cacheKey, dbData, 30, TimeUnit.MINUTES);
            }
            return dbData;
        } finally {
            DynamicTableNameUtil.clear(); // 确保清除 ThreadLocal
        }
    }

    @Override
    public void markProcessed(Long id) {
        TbSignal signal = new TbSignal();
        signal.setId(id);
        signal.setProcessed(1);

        // 删除手动拼接表名的代码（分表插件会自动处理）
        UpdateWrapper<TbSignal> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);

        signalMapper.update(signal, updateWrapper);
    }
}





