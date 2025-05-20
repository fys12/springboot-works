package com.example.springbootworks.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.springbootworks.domain.TbSignal;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class WarnMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    private Long id;

    /**
     * 车架编号
     */
    private Integer carId;

    /**
     * 最高电压
     */
    private BigDecimal mx;

    /**
     * 最低电压
     */
    private BigDecimal mi;

    /**
     * 最高电流
     */
    private BigDecimal ix;

    /**
     * 最低电流
     */
    private BigDecimal ii;
//    /**
//     * 信号上报时间
//     */
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private LocalDateTime signalTime;

    // getter / setter
}