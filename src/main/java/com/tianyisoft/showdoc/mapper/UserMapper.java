package com.tianyisoft.showdoc.mapper;

import com.tianyisoft.showdoc.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {
    @Select("select * from users")
    List<User> list();

    @Select("select * from users where id = #{id}")
    User find(Integer id);

    @Insert("insert into users (username, password, createdAt, updatedAt) values (#{username}, #{password}, #{createdAt}, #{updatedAt})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int create(User user);

    int update(Map<String, Object> map);

    @Delete("delete from users where id = #{id}")
    int delete(Integer id);

    @Select("select * from users where username = #{username}")
    User findByUsername(String username);
}
