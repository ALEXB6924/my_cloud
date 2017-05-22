//package com.alex.cloud.web.helper;
//
//import com.alex.cloud.service.RoleService;
//import com.alex.cloud.service.UserService;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
//import org.springframework.stereotype.Component;
//
///**
// * Created by alexandru_bobernac on 12/13/16.
// */
//@Component
//public class DBInitializer {
//
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private RoleService roleService;
////    @Autowired
////    private SessionFactory sessionFactory;
////
////    public boolean checkIfTableIsEmpty(){
////        Session session = sessionFactory.openSession();
////        return session.createQuery("select 1 from 'role'").setMaxResults(1).list().isEmpty();
////    }
//
//    public void fillTables(){
//
////        if (checkIfTableIsEmpty()) {
//            roleService.save("admin");
//            roleService.save("user");
//
//            userService.save("admin", "admin", "admin", true);
//            userService.save("user", "user", "user", true);
////        }
//    }
//}
