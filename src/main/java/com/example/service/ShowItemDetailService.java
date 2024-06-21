
package com.example.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Item;
import com.example.domain.OptionGroup;
import com.example.repository.ItemRepository;
import com.example.repository.OptionGroupRepository;

/**
 * アイテムの詳細を操作するサービスクラス.
 *
 * @author takeru.chugun
 */
@Service
public class ShowItemDetailService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OptionGroupRepository optionGroupRepository;

    public Map<String,?> getDetail(UUID id){
        Optional<Item> optionalItem = itemRepository.findById(id);
        Item item = optionalItem.orElse(null);
        List<OptionGroup> optionGroupList = optionGroupRepository.findAll();
        Map<String,Optional<?>> map = new HashMap<>();
        map.put("item",Optional.ofNullable(item));
        map.put("optionGroup",Optional.of(optionGroupList));
        return map;
    }
}
