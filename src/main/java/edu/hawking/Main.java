package edu.hawking;

import edu.hawking.pojo.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    String s1;
    String s2;
    public Main() {
    }

    public static void main(String[] args) throws ParseException {
//        Set<User> users = new HashSet<>();
//        users.add(new User(222L, "aaa"));
//        for (User user : users) {
//            System.out.println(user.toString());
//        }
//        System.out.println("----------------------------");
//        User user = new User(222L, "aaa");
//        users.add(user);
//        for (User u : users) {
//            System.out.println(u.toString());
//            System.out.println(u.hashCode());
//        }
//       List<User> userList =  users.stream().collect(Collectors.toList());
//        System.out.println(userList.get(0).equals(userList.get(1)));
        long currT = new Date().getTime();
        System.out.println("currt: "+ currT);
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-04-29 08:58:50");
        long datel = date.getTime();
        System.out.println("date: "+ datel);
        long lag = 1000 * 60 * 15;
        System.out.println(currT-datel<lag);
    }
}
