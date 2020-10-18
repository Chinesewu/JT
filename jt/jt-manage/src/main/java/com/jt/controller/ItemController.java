package com.jt.controller;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.service.ItemService;
import com.jt.vo.EasyUITable;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController //由于ajax调用 采用JSON串返回
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	/**
	 * url: http://localhost:8091/item/query?page=1&rows=20
	 * 请求参数:  page=1&rows=20
	 * 返回值结果: EasyUITable
	 */
	@RequestMapping("/query")
	public EasyUITable findItemByPage(Integer page, Integer rows){

		return itemService.findItemByPage(page,rows);
	}
	/**
	 * 	完成商品入库操作，如果成功应该返回vo对象
	 * 	url：/item/save
	 * 	参数：整个的表单的数据
	 *  返回值：SysResult对象
	 */
	@RequestMapping("/save")
	public SysResult saveItem(Item item, ItemDesc itemDesc){
		//try {
			itemService.saveItem(item,itemDesc);
			return SysResult.success();
		//}catch (Exception e){
		//	e.printStackTrace();
		//	return SysResult.fail();
		//}
	}

	/**
	 * 	商品修改操作
	 * 	1. url：
	 * 	2. 参数
	 * 	3. 返回值： SysResult
	 * @return
	 */
	@RequestMapping("/update")
	public SysResult updateItem(Item item,ItemDesc itemDesc){
		itemService.updatedItem(item,itemDesc);
		return SysResult.success();
	}
	/**
	 * 	商品删除操作
	 * 	1. url： http://localhost:8091/item/delete
	 * 	2. 参数
	 * 	3. 返回值：无
	 */
	@RequestMapping("/delete")
	public SysResult deleteItem(Long[] ids){
		itemService.deleteItem(ids);
		return SysResult.success();
	}


	/**
	 * 实现商品的下架
	 * url地址: http://localhost:8091/item/updateStatus/2    status=2
	 * 			http://localhost:8091/item/updateStatus/1   status=1
	 * 利用RestFul风格实现通用的操作.
	 * 参数:   ids: 1474391997,1474391996,1474391995
	 * 返回值:  VO对象
	 */
	@RequestMapping("/updateStatus/{status}")
	public SysResult updateStatus(@PathVariable Integer status, Long[] ids){

		itemService.updateStatus(status,ids);
		return SysResult.success();
	}
/**
 * 	业务：动态获取商品详情的信息
 * 	Url：/desc/{id}
 * 	参数：itemId restFul方式获取
 * 	返回值：系统vo对象
 */
@RequestMapping("/query/item/desc/{itemId}")
public SysResult findItemDescById(@PathVariable Long itemId){
	ItemDesc itemDesc=itemService.findItemDescById(itemId);
	return SysResult.success(itemDesc);
}

	
	
	
}
