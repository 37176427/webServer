package com.heasy.common.enums;

import lombok.Getter;

/**
 * 描述 ：
 * 作者 ：WYH
 * 时间 ：2019/12/6 13:16
 **/
@Getter
public enum FileUnitEnum {

    B(0, "B"),
    KB(1, "K"),
    MB(2, "M"),
    GB(3, "G"),
    TB(3, "T");


    private int id;
    private String unit;

    private FileUnitEnum(int id, String unit) {
        this.id = id;
        this.unit = unit;
    }

    public static String getUnitById(int id) {
        FileUnitEnum[] values = FileUnitEnum.values();
        for (FileUnitEnum unitEnum : values) {
            if (id == unitEnum.id) {
                return unitEnum.unit;
            }
        }
        return "";
    }
}
