package com.ly.factory.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.factory.domain.Component;
import com.ly.factory.domain.Params;
import com.ly.factory.mapper.ParamsMapper;
import com.ly.factory.utils.PageResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author Tarry
 * @create 2019/11/15 16:20
 */
@Service
public class ParamsService {
    @Autowired
    private ParamsMapper paramsMapper;

    @Autowired
    private ComponentService componentService;
    /**
     * 新增参数模板
     * @param params
     */
    public void insertParams(Params params) {
        Date date = new Date();
        params.setCreateTime(date);
        params.setUpdateTime(date);

        paramsMapper.insert(params);
    }

    /**
     * 条件查询参数模板
     * @param name
     * @param type
     * @param startDate
     * @param endDate
     * @param currentPage
     * @param pageSize
     * @param pageTotal
     * @return
     */
    public PageResult<Params> queryListParams(String name, Integer type, Date startDate, Date endDate, Integer currentPage, Integer pageSize, Integer pageTotal) {
        //分页
        PageHelper.startPage(currentPage,pageSize);

        //过滤条件
        Example example = new Example(Params.class);
        Example.Criteria criteria = example.createCriteria();
        //根据名字过滤
        if (StringUtils.isNoneBlank(name)){
            criteria.andLike("title","%"+name+"%");
        }
        //根据类型过滤
        if (type != null){
            criteria.andEqualTo("componentId",type);
        }
        //根据时间过滤
        if (startDate != null && endDate != null){
            criteria.andBetween("createTime",startDate,endDate);
        }
        //默认按时间排序
        example.setOrderByClause("create_time DESC");

        List<Params> paramsList = paramsMapper.selectByExample(example);

        //解析分页结果
        PageInfo<Params> paramsPageInfo = new PageInfo<Params>(paramsList);


        for (Params params : paramsList) {
            params.setComponentTitle(componentService.queryByid(params.getComponentId()));
        }



        return new PageResult<Params>(paramsPageInfo.getTotal(), paramsList);

    }

    /**
     * 修改参数模板
     * @param params
     */
    public void updateParams(Params params) {
        params.setUpdateTime(new Date());
        paramsMapper.updateByPrimaryKey(params);

    }


    /**
     * 根据组件id，查询对应的参数模板
     * @param id
     * @return
     */
    public List<Params> queryListByComponentId(Integer id) {
        List<Params> list = paramsMapper.queryListByComponentId(id);
        return list;
    }
}
