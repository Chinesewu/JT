package com.jt;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.dao.UserDao;
import com.jt.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class SpringbootDemo2MybatisPlusApplicationTests {
	@Autowired
	private UserDao userDao;
	@Test
	void contextLoads() {
	}
	/**
	 * 业务：查询全部信息
	 */
	@Test
	public void findAll(){
		List<User> list=userDao.selectList(null);
		System.out.println(list);
	}

	/**
	 * 业务: 查询id=11的用户信息   主键...
	 */
	@Test
	public void findById(){
		User user=userDao.selectById(11);
		System.out.println(user);
	}
	/**
	 * 业务: 查询name属性为"小乔"的数据
	 * sql:  select * from user where name="小乔";
	 * 对象的方式  >  sql方式
	 */
	@Test
	public void findByName(){
		QueryWrapper<User> queryWrapper=new QueryWrapper<>();
		queryWrapper.eq("name", "小乔");
		List<User> list=userDao.selectList(queryWrapper);
		System.out.println(list);
	}

	/**
	 * 业务: 查询name属性为"小乔"的数据 并且 age >=18岁
	 * sql:  select * from user where name="小乔" and age>=18;
	 *
	 * 大于   >  gt| 小于 <  lt   |
	 * 大于等于  >= ge  |  小于等于 le
	 */
	@Test
	public void findByNameAndAge(){
		QueryWrapper<User> queryWrapper=new QueryWrapper<>();
		queryWrapper.eq("name", "小乔").ge("age", 18);
		List<User> list=userDao.selectList(queryWrapper);
		System.out.println(list);
	}
	/**
	 * 业务: 查询name中包含 "精"的用户,并且sex为女
	 * 业务: 查询name中包含 以精结尾的数据,并且sex为女
	 * sql:  select * from user where name like "%精%" and sex="女";
	 */
	@Test
	public void findByNames(){
		QueryWrapper<User> queryWrapper=new QueryWrapper<>();
		queryWrapper.likeRight("name", "精");
		List<User> list=userDao.selectList(queryWrapper);
		System.out.println(list);
	}
	/**
	 * 需求: 查询user表中的数据 要求按照年龄降序排列,如果年龄相同按照id降序排列
	 */
	@Test
	public void select01(){
		QueryWrapper<User> queryWrapper=new QueryWrapper<>();
		queryWrapper.orderByDesc("age","id");
		List<User> userList = userDao.selectList(queryWrapper);
		System.out.println(userList);
	}
	/**
	 * 需求: 查询name属性为null的数据.
	 * where name is null
	 */
	@Test
	public void testSelect07(){
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.isNull("name");
		List<User> userList = userDao.selectList(queryWrapper);
		System.out.println(userList);
	}
	/**
	 * 查询name="小乔" age=17 性别=女的用户
	 * 如果传递的是对象.会根据对象中不为null的属性充当where条件.
	 */
	@Test
	public void testSelect08(){
		User user = new User();
		user.setName("小乔").setAge(17).setSex("女");
		QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);
		List<User> userList = userDao.selectList(queryWrapper);
		System.out.println(userList);
	}

	/**
	 * 查询id=1,3,5,10数据.
	 * sql: select * from user where id in (1,3,5,10)
	 */
	@Test
	public void testSelect09(){
		//转化时,注意使用对象的类型
		Integer[] ids = {1,3,5,10};
		List<Integer> idList = Arrays.asList(ids);
		List<User> userList = userDao.selectBatchIds(idList);
		System.out.println(userList);
	}
	/**
	 * 插入特朗普数据
	 */
	@Test
	public void testInsert(){
		User user = new User();
		user.setName("特朗普").setAge(70).setSex("男");
		userDao.insert(user);
	}
	/**
	 * 删除name=null的数据,或者name="特朗普"
	 */
	@Test
	public void testDelete(){
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.isNull("name")
				.or()
				.eq("name", "特朗普");
		userDao.delete(queryWrapper);
	}
	/**
	 * 案例1: 将id=1的数据的年龄改为8000岁.
	 * sql1:  update user set age=8000 where id=1;
	 * 案例2: 要求将name="黑熊精" age=5500.
	 * sql2:  update user set age=5500 where name="黑熊精";
	 */

	@Test
	public void testUpdate(){
		User user = new User();
		user.setId(1);	//主键充当where条件
		user.setAge(8000);
		userDao.updateById(user);

		//参数说明  1.实体对象  封装set条件的值
		//		   2.更新条件构造器
		User temp = new User();
		temp.setAge(5500);
		UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("name", "黑熊精");
		userDao.update(temp,updateWrapper);
	}

}
