package com.tianyisoft.showdoc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tianyisoft.showdoc.entity.Directory;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DirectoryMapper extends BaseMapper<Directory> {
    List<Directory> findByPid(Integer pid);

    @Insert("insert into directories (name, pid, created_at, updated_at) values (#{name}, #{pid}, #{createdAt}, #{updatedAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int create(Directory directory);

    @Select("select * from directories where id = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "pid", column = "pid"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
    })
    Directory findById(Integer id);

    int update(Directory directory);

    int delete(List<Integer> id);
}
