package com.tianyisoft.showdoc.mapper;

import com.tianyisoft.showdoc.entity.Directory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DirectoryMapper {
    @Select("select * from directories where id = #{id}")
    Directory findById(Integer id);

    List<Directory> findByPid(Integer pid);
}
