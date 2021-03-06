package com.jt.service;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUITable;

import java.util.List;

public interface ItemService {

    EasyUITable findItemByPage(Integer page, Integer rows);

    void saveItem(Item item, ItemDesc itemDesc);

    void updatedItem(Item item, ItemDesc itemDesc);

    void deleteItem(Long[] ids);


    void updateStatus(Integer status, Long[] ids);

    ItemDesc findItemDescById(Long itemId);

    List<Item> getItems();
}
