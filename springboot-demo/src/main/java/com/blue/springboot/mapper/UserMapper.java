package com.blue.springboot.mapper;


import com.blue.springboot.pojo.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.hibernate.validator.constraints.URL;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE username=#{username}")
    public User findUserByUsername(String username);

    @Select("SELECT * FROM user WHERE id=#{id}")
    public User findUserById(Integer id);

    @Insert("INSERT INTO user(username,password,nickname) VALUES (#{username},#{password},#{nickname})")
    void add(String username, String password, String nickname);

    @Update("UPDATE user SET nickname=#{nickname},email=#{email} WHERE id=#{id}")
    void update(User user);

    @Update("UPDATE user SET avatar_url=#{avatarUrl} WHERE id=#{id}")
    void updateAvatar(Integer id, String avatarUrl);

    @Update("UPDATE user SET password=#{md5Pwd} WHERE id=#{id}")
    void updatePwd(Integer id, String md5Pwd);
}
