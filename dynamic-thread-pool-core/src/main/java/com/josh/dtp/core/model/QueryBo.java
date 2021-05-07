package com.josh.dtp.core.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author : Josh
 * @date : 2020-08-14 16:02
 */
@Data
@Accessors(chain = true)
public class QueryBo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 客户端标识
     */
    private String appId;

    /**
     * 集群标识
     */
    private String clusterId;

    /**
     * 客户端ip
     */
    private String ip;
}
