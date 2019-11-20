package com.ly.factory.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.factory.domain.Component;
import com.ly.factory.domain.vo.Skill;
import com.ly.factory.mapper.ComponentMapper;
import com.ly.factory.utils.PageResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author Tarry
 * @create 2019/11/15 9:47
 */
@Service
public class ComponentService {

    @Autowired
    private ComponentMapper componentMapper;

    /**
     * 新增组件
     * @param component
     */
    public void insertComponent(Component component) {
        Date date = new Date();
        component.setCreateTime(date);
        component.setUpdateTime(date);
        componentMapper.insert(component);
    }

    /**
     * 修改组件
     * @param component
     */
    public void updateComponent(Component component) {
        component.setUpdateTime(new Date());
        componentMapper.updateByPrimaryKey(component);
    }

    public PageResult<Component> queryListComponent(String name, Integer type, Date startDate, Date endDate, Integer currentPage, Integer pageSize, Integer pageTotal) {
        //分页
        PageHelper.startPage(currentPage,pageSize);

        //过滤条件
        Example example = new Example(Component.class);
        Example.Criteria criteria = example.createCriteria();
        //根据名字过滤
        if (StringUtils.isNoneBlank(name)){
            criteria.andLike("title","%"+name+"%");
        }
        //根据类型过滤
        if (type != null){
            criteria.andEqualTo("typeId",type);
        }
        //根据时间过滤
        if (startDate != null && endDate != null){
            criteria.andBetween("createTime",startDate,endDate);
        }
        //默认按时间排序
        example.setOrderByClause("create_time DESC");

        List<Component> componentList = componentMapper.selectByExample(example);

        //解析分页结果
        PageInfo<Component> componentPageInfo = new PageInfo<Component>(componentList);

        return new PageResult<Component>(componentPageInfo.getTotal(), componentList);
    }

    public List<Component> querySelectComponent() {
        List<Component> list = componentMapper.queryIdAndTitle();
        return list;
    }

    /**
     * 根据id获取组件的title
     * @param id
     * @return
     */
    public String queryByid(Integer id){
        Component component = componentMapper.selectByPrimaryKey(id);
        return component.getTitle();
    }

    /**
     * 查询组件列表，返回id，title，typeTitle
     * @return
     */
    public List<Component> querySelectComponent2() {
       return componentMapper.selectListAndTypeTitle();
    }

    /**
     * 注册页面获取职能列表
     * @return
     */
    public List<Skill> querySkillSelect() {
       return componentMapper.querySkillSelect();
    }
}
