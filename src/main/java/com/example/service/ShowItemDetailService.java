
package com.example.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repository.ItemRepository;
import com.example.repository.OptionGroupRepository;

import lombok.val;

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

    public Map<String, ?> getDetail(UUID id) {
        val optionalItem = itemRepository.findById(id);
        val item = optionalItem.orElse(null);
        val optionGroupList = optionGroupRepository.findAll();
        val map = new HashMap<String, Optional<?>>();
        map.put("item", Optional.ofNullable(item));
        map.put("optionGroup", Optional.of(optionGroupList));
        return map;
    }
}
