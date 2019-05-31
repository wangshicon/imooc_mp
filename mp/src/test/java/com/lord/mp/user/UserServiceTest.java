package com.lord.mp.user;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lord.mp.MpApplicationTests;
import com.lord.mp.entity.User;
import com.lord.mp.service.UserService;

public class UserServiceTest extends MpApplicationTests {

    @Autowired
    private UserService userService;

    // 此处会报错，因为数据库存在多条记录
    @Test
    public void getOne() {
        User one = userService.getOne(Wrappers.<User>lambdaQuery().gt(User::getAge, 25));
        System.out.println(one);
    }

    @Test
    public void insertBatch() {
        User user1 = new User();
        user1.setName("小可爱");
        user1.setAge(28);
        user1.setManagerId(1088248166370832385L);

        User user2 = new User();
        user2.setName("大傻瓜");
        user2.setAge(19);
        user2.setManagerId(1088248166370832385L);

        List<User> userList = Arrays.asList(user1, user2);
        boolean flag = userService.saveBatch(userList);
        System.out.println("是否成功：" + flag);
    }

    @Test
    public void insertOrUpdateBatch() {
        User user1 = new User();
        user1.setName("李莫愁");
        user1.setAge(28);

        User user2 = new User();
        user2.setId(1134354221018144774L);
        user2.setName("张三丰");
        user2.setAge(20);
        user2.setManagerId(1088248166370832385L);

        List<User> userList = Arrays.asList(user1, user2);
        boolean flag = userService.saveOrUpdateBatch(userList);
        System.out.println("是否成功：" + flag);
    }

    @Test
    public void selectChain() {
        List<User> userList = userService.lambdaQuery().gt(User::getAge, 25).like(User::getName, "雨").list();
        userList.forEach(System.out::println);
    }

    @Test
    public void updateChain() {
        boolean flag = userService.lambdaUpdate().eq(User::getAge, 25).set(User::getAge, 26).update();
        System.out.println("是否成功：" + flag);
    }

    @Test
    public void removeChain() {
        boolean flag = userService.lambdaUpdate().eq(User::getAge, 20).remove();
        System.out.println("是否成功：" + flag);
    }

}
