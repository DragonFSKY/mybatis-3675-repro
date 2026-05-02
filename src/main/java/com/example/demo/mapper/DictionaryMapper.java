package com.example.demo.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DictionaryMapper {

  @Select("select count(*) from dictionary")
  int countDictionaries();
}
