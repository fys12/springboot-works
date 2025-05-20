package com.example.springbootworks.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 预警规则表
 * @TableName tb_warn_rule
 */
@TableName(value ="tb_warn_rule")
@Data
public class TbWarnRule implements Serializable {
    /**
     * 规则编号
     */
    @TableId
    private Integer ruleId;

    /**
     * 电池类型：1=三元电池，2=铁锂电池
     */
    private Integer batteryType;

    /**
     * 规则名称
     */
    private String ruleName;

    /**
     * 规则类型：1=电压差，2=电流差
     */
    private Integer ruleType;

    /**
     * 规则条件JSON数组，例：[{"min":5,"max":null,"level":0},{"min":3,"max":5,"level":1}]
     */
    private String conditions;

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
        TbWarnRule other = (TbWarnRule) that;
        return (this.getRuleId() == null ? other.getRuleId() == null : this.getRuleId().equals(other.getRuleId()))
            && (this.getBatteryType() == null ? other.getBatteryType() == null : this.getBatteryType().equals(other.getBatteryType()))
            && (this.getRuleName() == null ? other.getRuleName() == null : this.getRuleName().equals(other.getRuleName()))
            && (this.getRuleType() == null ? other.getRuleType() == null : this.getRuleType().equals(other.getRuleType()))
            && (this.getConditions() == null ? other.getConditions() == null : this.getConditions().equals(other.getConditions()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRuleId() == null) ? 0 : getRuleId().hashCode());
        result = prime * result + ((getBatteryType() == null) ? 0 : getBatteryType().hashCode());
        result = prime * result + ((getRuleName() == null) ? 0 : getRuleName().hashCode());
        result = prime * result + ((getRuleType() == null) ? 0 : getRuleType().hashCode());
        result = prime * result + ((getConditions() == null) ? 0 : getConditions().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", ruleId=").append(ruleId);
        sb.append(", batteryType=").append(batteryType);
        sb.append(", ruleName=").append(ruleName);
        sb.append(", ruleType=").append(ruleType);
        sb.append(", conditions=").append(conditions);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}