package com.example.springbootworks.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 车辆信息表
 * @TableName tb_vehicle_info
 */
@TableName(value ="tb_vehicle_info")
@Data
public class TbVehicleInfo implements Serializable {
    /**
     * 车辆识别码，16位唯一字符串
     */
    @TableId
    private String vid;

    /**
     * 车架编号
     */
    private Integer carId;

    /**
     * 电池类型：1=三元电池，2=铁锂电池
     */
    private Integer batteryType;

    /**
     * 总里程（km）
     */
    private Integer totalMileage;

    /**
     * 电池健康状态（%）
     */
    private Integer batteryHealth;

    /**
     * 创建时间
     */
    private Date createTime;

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
        TbVehicleInfo other = (TbVehicleInfo) that;
        return (this.getVid() == null ? other.getVid() == null : this.getVid().equals(other.getVid()))
            && (this.getCarId() == null ? other.getCarId() == null : this.getCarId().equals(other.getCarId()))
            && (this.getBatteryType() == null ? other.getBatteryType() == null : this.getBatteryType().equals(other.getBatteryType()))
            && (this.getTotalMileage() == null ? other.getTotalMileage() == null : this.getTotalMileage().equals(other.getTotalMileage()))
            && (this.getBatteryHealth() == null ? other.getBatteryHealth() == null : this.getBatteryHealth().equals(other.getBatteryHealth()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getVid() == null) ? 0 : getVid().hashCode());
        result = prime * result + ((getCarId() == null) ? 0 : getCarId().hashCode());
        result = prime * result + ((getBatteryType() == null) ? 0 : getBatteryType().hashCode());
        result = prime * result + ((getTotalMileage() == null) ? 0 : getTotalMileage().hashCode());
        result = prime * result + ((getBatteryHealth() == null) ? 0 : getBatteryHealth().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", vid=").append(vid);
        sb.append(", carId=").append(carId);
        sb.append(", batteryType=").append(batteryType);
        sb.append(", totalMileage=").append(totalMileage);
        sb.append(", batteryHealth=").append(batteryHealth);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}