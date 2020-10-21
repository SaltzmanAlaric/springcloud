package com.study.service;

import com.study.common.service.BaseService;
import com.study.dao.CityMapper;
import com.study.entity.City;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService implements BaseService<CityMapper, City> {

    @Resource
    private CityMapper mapper;

    @Override
    public CityMapper getMapper() {
        return mapper;
    }

    public List<City> getCityWithTree() {

        List<City> cities = mapper.selectList(null);
        return cities.stream()
                //过滤得到所有顶级节点
                .filter((city)-> city.getPid() == 0)
                //遍历每个顶级节点,为其设置子节点
                .peek((city)->{
                    //设置子节点
                    city.setChildren(getChildren(city,cities));
                })
                //比较sort属性,默认升序,即sort越小,显示位置越前
                .sorted(Comparator.comparingInt(City::getSort))
                //封装最终结果并返回
                .collect(Collectors.toList());
    }

    /**
     * 递归查询子节点
     * @param root 当前节点
     * @param all 所有节点
     * @return 返回当前节点下的所有子节点
     */
    private List<City> getChildren(City root, List<City> all){
        return all.stream()
                .filter((city)-> root.getId().equals(city.getPid()))
                .peek((city)-> city.setChildren(getChildren(city,all)))
                .sorted(Comparator.comparingInt(City::getSort))
                .collect(Collectors.toList());
    }

    /**
     * 根据查询条件查询交通区域列表
     *
     * @param query 查询条件
     * @return 交通区域列表
     */
//    @SortAop(typeClass = City.class)
//    public List<City> selectCities(@Nullable City query) {
//        return mapper.selectCities(query);
//    }

    /**
     * 根据查询条件查询权限 分页
     *
     * @param query 查询条件
     * @return 权限 分页
     */
//    @SortAop(typeClass = City.class)
//    public PageInfo<City> selectCitiesByPage(int pageNum, int pageSize, @Nullable CityQuery query) {
//        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> selectCities(query));
//    }

}
