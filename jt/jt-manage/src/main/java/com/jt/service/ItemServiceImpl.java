package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jt.mapper.ItemDescMapper;
import com.jt.mapper.ItemMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUITable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemDescMapper itemDescMapper;

	/*@Override
	public EasyUITable findItemByPage(Integer page, Integer rows) {
		QueryWrapper<Item> queryWrapper=new QueryWrapper<>();

		return null;
	}*/


	/*@Override
	public EasyUITable findItemByPage(Integer page, Integer rows) {

		//1.手动完成分页操作
		int startIndex=(page-1)*rows;
		//2.数据库记录
		List<Item> itemByPage = itemMapper.findItemByPage(startIndex, rows);
		//3.查询数据库总记录数
		Long total = Long.valueOf(itemMapper.selectCount(null));
		//4.将数据库记录 封装为VO对象
		return new EasyUITable(total, itemByPage);
	}*/
	//尝试使用MP的方式进行分页操作
	@Override
	public EasyUITable findItemByPage(Integer page, Integer rows) {
		QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
		queryWrapper.orderByDesc("updated");
		//暂时只封装了2个数据  页数/条数
		IPage<Item> iPage = new Page<>(page, rows);
		//MP 传递了对应的参数,则分页就会在内部完成.返回分页对象
		iPage = itemMapper.selectPage(iPage,queryWrapper);
		//1.获取分页的总记录数
		Long total = iPage.getTotal();
		
		//2.获取分页的结果
		List<Item> list = iPage.getRecords();
		return new EasyUITable(total, list);
	}

	@Override
	public void saveItem(Item item, ItemDesc itemDesc) {
		//Date date=new Date();
		//完成自动填充功能
		item.setStatus(1);
		//item.setStatus(1).setCreated(date).setUpdated(date);
		itemMapper.insert(item);
		//itemDesc属性有值
		itemDesc.setItemId(item.getId());
		itemDescMapper.insert(itemDesc);
	}

	@Override
	public void updatedItem(Item item, ItemDesc itemDesc) {
		itemMapper.updateById(item);

		itemDesc.setItemId(item.getId());
		itemDescMapper.updateById(itemDesc);
	}
	@Transactional
	@Override
	public void deleteItem(Long[] ids) {
		//1.将数组转化为集合
		List<Long> longList = Arrays.asList(ids);
		itemMapper.deleteBatchIds(longList);
		//2.删除商品详情信息
		itemDescMapper.deleteBatchIds(longList);
	}
	/**
	 * sql: update tb_item set status = #{status},updated={date}
	 * 		where id in (id1,id2,id3)
	 * 	MP机制实现
	 * @param status
	 * @param ids
	 */
	@Override
	public void updateStatus(Integer status, Long[] ids) {
		Item item = new Item();	//封装修改的值
		item.setStatus(status);
		UpdateWrapper<Item> updateWrapper = new UpdateWrapper<>();
		updateWrapper.in("id", Arrays.asList(ids));
		itemMapper.update(item,updateWrapper);
	}

	@Override
	public ItemDesc findItemDescById(Long itemId) {

		return itemDescMapper.selectById(itemId);
	}
}
