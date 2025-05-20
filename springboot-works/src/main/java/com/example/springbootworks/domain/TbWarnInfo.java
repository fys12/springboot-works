package com.example.springbootworks.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * 预警信息表
 * @TableName tb_warn_info
 */
@TableName(value ="tb_warn_info")
@Data
public class TbWarnInfo implements Serializable {
    /**
     * 预警ID
     */
    @TableId(type = IdType.AUTO)
    private Long warnId;

    /**
     * 车架编号
     */
    private Integer carId;

    /**
     * 规则编号
     */
    private Integer ruleId;

    /**
     * 预警等级：0=最高
     */
    private Integer warnLevel;

    /**
     * 触发预警的信号数据
     */
    private String signalData;

    /**
     * 预警时间
     */
    private LocalDateTime createTime;

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
        TbWarnInfo other = (TbWarnInfo) that;
        return (this.getWarnId() == null ? other.getWarnId() == null : this.getWarnId().equals(other.getWarnId()))
            && (this.getCarId() == null ? other.getCarId() == null : this.getCarId().equals(other.getCarId()))
            && (this.getRuleId() == null ? other.getRuleId() == null : this.getRuleId().equals(other.getRuleId()))
            && (this.getWarnLevel() == null ? other.getWarnLevel() == null : this.getWarnLevel().equals(other.getWarnLevel()))
            && (this.getSignalData() == null ? other.getSignalData() == null : this.getSignalData().equals(other.getSignalData()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getWarnId() == null) ? 0 : getWarnId().hashCode());
        result = prime * result + ((getCarId() == null) ? 0 : getCarId().hashCode());
        result = prime * result + ((getRuleId() == null) ? 0 : getRuleId().hashCode());
        result = prime * result + ((getWarnLevel() == null) ? 0 : getWarnLevel().hashCode());
        result = prime * result + ((getSignalData() == null) ? 0 : getSignalData().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", warnId=").append(warnId);
        sb.append(", carId=").append(carId);
        sb.append(", ruleId=").append(ruleId);
        sb.append(", warnLevel=").append(warnLevel);
        sb.append(", signalData=").append(signalData);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}