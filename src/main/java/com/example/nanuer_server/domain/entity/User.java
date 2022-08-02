package com.example.nanuer_server.domain.entity;

import com.example.nanuer_server.domain.BaseTimeEntity;
import com.example.nanuer_server.dto.User.UserInfoDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.management.relation.Role;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name="user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@ToString(callSuper = true) // 부모 클래스의 toString 불러오는 어노테이션. 붙이면 createdAt 하고 updatedAt 데이터 정상적으로 나옴.
@EqualsAndHashCode(callSuper = true) // 부모클래스의 equalsAndHashCode 불러오는 어노테이션.
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(nullable = false)
    private String email;

    private String name;

    @Column(nullable = false)
    private String password;

    private String nickName;

    private String phone;

    private String birth;

    @Column
    private String profileImg;

    private String university;

    private String userStatus;

    @ColumnDefault("0")
    private int userScore;

    //mapped 이름 수정
    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch=FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private UserRole role;

    /*
    public static UserEntity createUser(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userEntity.getId());
        userEntity.setName(userEntity.getName());
        userEntity.setNickName(userEntity.getNickName());
        userEntity.setEmail(userEntity.getEmail());
        userEntity.setPhone(userEntity.getPhone());
        userEntity.setBirth(userEntity.getBirth());
        userEntity.setProfileImg(userEntity.getProfileImg());
        userEntity.setUserStatus(userEntity.getUserStatus());
        userEntity.setUserScore(userEntity.getUserScore());
        userEntity.setArea(userEntity.getArea());
        //String password = passwordEncoder.encode(memberDto.getPassword());
        //userEntity.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        return userEntity;
    }*/

    public void status(String status){
        this.userStatus  = status;

    }
    public UserInfoDto toDto(){
        UserInfoDto userInfoDto = UserInfoDto.builder()
                .password(password)
                .name(name)
                .nickName(nickName)
                .email(email)
                .phone(phone)
                .birth(birth)
                .profileImg(profileImg)
                .university(university)
                .userStatus(userStatus)
                .role(role)
                .postEntities(posts)
                .build();
        return userInfoDto;
    }

}