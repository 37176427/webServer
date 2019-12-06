package com.heasy.system.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述 ：
 * 作者 ：WYH
 * 时间 ：2019/12/6 13:15
 **/
@Data
@ToString
public class MyFile implements Serializable {
    private String name;
    private String size;
    private String type;
    private Date date;

}
