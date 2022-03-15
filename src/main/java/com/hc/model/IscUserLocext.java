package com.hc.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 同步ISC日志表(员工表)
 * </p>
 *
 * @author hc
 * @since 2021-03-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class IscUserLocext implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ISC人员主键
     */
    @TableId(value = "ISC_ID", type = IdType.AUTO)
    private String iscId;

    /**
     * 人员姓名
     */
    @TableField("RYMC")
    private String rymc;

    /**
     * 所属部门ID
     */
    @TableField("SSBMID")
    private String ssbmid;

    /**
     * 创建时间(YYYY-MM-DD)
     */
    @TableField("CJSJ")
    private Date cjsj;

    /**
     * 同步时间(YYYY-MM-DD)
     */
    @TableField("TBSJ")
    private Date tbsj;

    /**
     * 撤销时间(YYYY-MM-DD)
     */
    @TableField("CXSJ")
    private Date cxsj;

    /**
     * 数据有效性
     */
    @TableField("SFYX")
    private BigDecimal sfyx;

    /**
     * 人员职称
     */
    @TableField("RYZC")
    private String ryzc;

    /**
     * 人员岗位
     */
    @TableField("RYGW")
    private String rygw;

    /**
     * 人员专业
     */
    @TableField("RYZY")
    private String ryzy;

    /**
     * 人员性别
     */
    @TableField("RYXB")
    private String ryxb;

    /**
     * 内线电话
     */
    @TableField("NXDH")
    private String nxdh;

    /**
     * 身份证ID
     */
    @TableField("ID")
    private String id;

    /**
     * 所属单位ID
     */
    @TableField("SSDWID")
    private String ssdwid;

    /**
     * 人员登录名
     */
    @TableField("LOGINNAME")
    private String loginname;

    /**
     * 预留ID1
     */
    @TableField("OLDID")
    private String oldid;

    /**
     * 所属调度组织机构ID
     */
    @TableField("SSDDJGID")
    private String ssddjgid;

    /**
     * 所属调度组织机构
     */
    @TableField("SSDDJGMC")
    private String ssddjgmc;

    /**
     * 更新时间
     */
    private String synDate;

    /**
     * 是否配置首页面
     */
    @TableField("SFPZSYM")
    private String sfpzsym;


}
