package com.ruoyi.service.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.utils.StringUtils;
import lombok.Data;

/**
 * 模型
 *
 * @author ruoyi
 */
@Data
@TableName("MOD_MODEL")
public class ModModel {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    @Excel(name = "模型名称")
    private String name;

    @Excel(name = "1-有效 0-无效")
    private Integer status;

    public LambdaQueryWrapper<ModModel> toQuery() {
        return new LambdaQueryWrapper<ModModel>()
                .eq(ModModel::getStatus, 1)
                .like(StringUtils.isNotBlank(name), ModModel::getName, name)
                .orderByDesc(ModModel::getId);
    }
}
