package com.ohgiraffers.crud.menu.model.dao;

import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/* configuration.addMapper(MenuMapper.class);
*  getMapper(MenuMapper.class) 와 같은 어노테이션 */
@Mapper

public interface MenuMapper {
    List<MenuDTO> findAllMenu();
}
