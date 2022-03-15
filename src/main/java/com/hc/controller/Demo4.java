package com.hc.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.hc.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Demo4 {


    public static List<User> findList(List<User> list, Predicate<User> predicate) {
        List<User> mapList = new ArrayList<>();
        for (User r : list) {
            if (predicate.test(r)) {
                mapList.add(r);
            }
        }
        return mapList;
    }

    public static <T,R> List<R> map(List<T> list, Function<T, R> function){
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(function.apply(t));
        }
        return result;
    }
    
    
    


    public static void main(String[] args) {
        String t=null;
//        User user=new User();
//        user.setAge(12);
//        user.setName("123");
//        User user1=new User();
//        user1.setAge(13);
//        user1.setName("113");
//        List<User> users=new ArrayList<>();
//        users.add(user);
//        users.add(user1);
//        List<User> list = findList(users, s -> false);
//        list.forEach(e->{
//            System.out.println(e.toString());
//        });
        System.out.println(t.isEmpty());
    }

}
