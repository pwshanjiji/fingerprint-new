package com.xml.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xml.entity.Clock;



public interface ClockMapper {
	
	List<Clock> findList(Clock clock);
	
	Integer delete(@Param("id") Integer id);
	
	Clock findById(@Param("id") Integer id);
	
	Integer insert(Clock clock);
	
	Integer update(Clock clock);

}
