package com.example.springbootworks.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 电池信号表
 * @TableName tb_signal
 */
@Data
public class SignalDTO implements Serializable {
    /**
     * 自增主键
     */
    @TableId(type = IdType.AUTO)
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

    /**
     * 信号上报时间
     */
    private LocalDateTime signalTime;

    /**
     * 是否已处理预警：0=未处理，1=已处理
     */
    private Integer processed;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SignalDTO other = (SignalDTO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCarId() == null ? other.getCarId() == null : this.getCarId().equals(other.getCarId()))
            && (this.getMx() == null ? other.getMx() == null : this.getMx().equals(other.getMx()))
            && (this.getMi() == null ? other.getMi() == null : this.getMi().equals(other.getMi()))
            && (this.getIx() == null ? other.getIx() == null : this.getIx().equals(other.getIx()))
            && (this.getIi() == null ? other.getIi() == null : this.getIi().equals(other.getIi()))
            && (this.getSignalTime() == null ? other.getSignalTime() == null : this.getSignalTime().equals(other.getSignalTime()))
            && (this.getProcessed() == null ? other.getProcessed() == null : this.getProcessed().equals(other.getProcessed()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCarId() == null) ? 0 : getCarId().hashCode());
        result = prime * result + ((getMx() == null) ? 0 : getMx().hashCode());
        result = prime * result + ((getMi() == null) ? 0 : getMi().hashCode());
        result = prime * result + ((getIx() == null) ? 0 : getIx().hashCode());
        result = prime * result + ((getIi() == null) ? 0 : getIi().hashCode());
        result = prime * result + ((getSignalTime() == null) ? 0 : getSignalTime().hashCode());
        result = prime * result + ((getProcessed() == null) ? 0 : getProcessed().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", carId=").append(carId);
        sb.append(", mx=").append(mx);
        sb.append(", mi=").append(mi);
        sb.append(", ix=").append(ix);
        sb.append(", ii=").append(ii);
        sb.append(", signalTime=").append(signalTime);
        sb.append(", processed=").append(processed);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}