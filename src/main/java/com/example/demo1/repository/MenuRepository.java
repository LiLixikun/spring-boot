package com.example.demo1.repository;

import com.example.demo1.dataobject.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository <Menu,Integer>{
}
