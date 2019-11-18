package com.ly.factory.domain.RetDomain;

import com.ly.factory.domain.Params;
import lombok.Data;

import java.util.List;

@Data
public class LingjianLiucheng {
    private String id;
    private Integer componentId;
    private String orgType;
    private String dutyState;
    private String checkState;
    private String paramState;
    private String parentId;
    private List<Params> params;
}
