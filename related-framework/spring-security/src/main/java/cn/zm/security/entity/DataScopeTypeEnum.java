package com.mabang.cloud.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <查询判断枚举>
 * @author 十渊Jermaine jermainenee@yeah.net
 * @version 1.0
 * @date 2022/7/29
*/
@Getter
@AllArgsConstructor
public enum DataScopeTypeEnum {
    /**
     * 查询全部数据
     */
    ALL(0, "全部"),

    /**
     * 自定义
     */
    CUSTOM(1, "自定义"),

    /**
     * 本级及子级
     */
    OWN_CHILD_LEVEL(2, "本级及子级"),

    /**
     * 本级
     */
    OWN_LEVEL(3, "本级"),

    /**
     * 个人（自己）
     */
    OWN(4,"个人");


    /**
     * 类型
     */
    private final int type;

    /**
     * 描述
     */
    private final String description;
}
