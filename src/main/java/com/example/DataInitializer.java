package com.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.domain.Breed;
import com.example.domain.Color;
import com.example.domain.Item;
import com.example.domain.Option;
import com.example.domain.OptionGroup;
import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderStatus;
import com.example.domain.TimeRange;
import com.example.domain.User;
import com.example.repositories.UserRepository;
import com.example.repository.BreedRepository;
import com.example.repository.ColorRepository;
import com.example.repository.ItemRepository;
import com.example.repository.OptionGroupRepository;
import com.example.repository.OptionRepository;
import com.example.repository.OrderItemRepository;
import com.example.repository.OrderRepository;

import lombok.val;

/**
 * データの初期化.
 *
 * @author char5742
 */
@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BreedRepository breedRepository;

    private void setUpBreed() {
        if (breedRepository.count() > 0) {
            return;
        }
        breedRepository.save(Breed.builder().name("Shiba Inu").build());
        breedRepository.save(Breed.builder().name("Labrador Retriever").build());
        breedRepository.save(Breed.builder().name("German Shepherd").build());
        breedRepository.save(Breed.builder().name("Golden Retriever").build());
        breedRepository.save(Breed.builder().name("Bulldog").build());
        breedRepository.save(Breed.builder().name("Poodle").build());
        breedRepository.save(Breed.builder().name("Beagle").build());
        breedRepository.save(Breed.builder().name("Chihuahua").build());
        breedRepository.save(Breed.builder().name("Dachshund").build());
    }

    @Autowired
    private ColorRepository colorRepository;

    private void setUpColor() {
        if (colorRepository.count() > 0) {
            return;
        }
        colorRepository.save(Color.builder().name("Black").build());
        colorRepository.save(Color.builder().name("White").build());
        colorRepository.save(Color.builder().name("Brown").build());
        colorRepository.save(Color.builder().name("Golden").build());
        colorRepository.save(Color.builder().name("Gray").build());
        colorRepository.save(Color.builder().name("Red").build());
        colorRepository.save(Color.builder().name("Cream").build());
        colorRepository.save(Color.builder().name("Fawn").build());
        colorRepository.save(Color.builder().name("Blue").build());
    }

    @Autowired
    private ItemRepository itemRepository;

    private void setUpItem() {
        if (itemRepository.count() > 0) {
            return;
        }
        itemRepository.save(Item.builder()
                .description("Friendly Shiba Inu")
                .price(300000)
                .image("shiba_inu.png")
                .gender("male")
                .birthDay(LocalDate.of(2023, 1, 1))
                .breed(breedRepository.findByName("Shiba Inu"))
                .color(colorRepository.findByName("Black")).build());
        itemRepository.save(Item.builder()
                .description("Loyal Labrador")
                .price(250000)
                .image("labrador.png")
                .gender("female")
                .birthDay(LocalDate.of(2023, 2, 1))
                .breed(breedRepository.findByName("Labrador Retriever"))
                .color(colorRepository.findByName("Black")).build());

        itemRepository.save(Item.builder()
                .description("Brave German Shepherd")
                .price(280000)
                .image("german_shepherd.png")
                .gender("male")
                .birthDay(LocalDate.of(2023, 3, 1))
                .breed(breedRepository.findByName("German Shepherd"))
                .color(colorRepository.findByName("Black")).build());

        itemRepository.save(Item.builder()
                .description("Gentle Golden Retriever")
                .price(270000)
                .image("golden_retriever.png")
                .gender("female")
                .birthDay(LocalDate.of(2023, 4, 1))
                .breed(breedRepository.findByName("Golden Retriever"))
                .color(colorRepository.findByName("Golden")).build());

        itemRepository.save(Item.builder()
                .description("Playful Bulldog")
                .price(260000)
                .image("bulldog.png")
                .gender("male")
                .birthDay(LocalDate.of(2023, 5, 1))
                .breed(breedRepository.findByName("Bulldog"))
                .color(colorRepository.findByName("White")).build());

        itemRepository.save(Item.builder()
                .description("Smart Poodle")
                .price(240000)
                .image("poodle.png")
                .gender("female")
                .birthDay(LocalDate.of(2023, 6, 1))
                .breed(breedRepository.findByName("Poodle"))
                .color(colorRepository.findByName("Brown")).build());

        itemRepository.save(Item.builder()
                .description("Energetic Beagle")
                .price(230000)
                .image("beagle.png")
                .gender("male")
                .birthDay(LocalDate.of(2023, 7, 1))
                .breed(breedRepository.findByName("Beagle"))
                .color(colorRepository.findByName("Black")).build());

        itemRepository.save(Item.builder()
                .description("Tiny Chihuahua")
                .price(220000)
                .image("chihuahua.png")
                .gender("female")
                .birthDay(LocalDate.of(2023, 8, 1))
                .breed(breedRepository.findByName("Chihuahua"))
                .color(colorRepository.findByName("Brown")).build());

        itemRepository.save(Item.builder()
                .description("Long Dachshund")
                .price(210000)
                .image("dachshund.png")
                .gender("male")
                .birthDay(LocalDate.of(2023, 9, 1))
                .breed(breedRepository.findByName("Dachshund"))
                .color(colorRepository.findByName("Black")).build());
    }

    private void setUpUsers() {
        if (userRepository.count() > 0) {
            return;
        }
        User user1 = User.builder().firstName("Taro")
                .lastName("Yamada")
                .email("taro.yamada@example.com")
                .password(passwordEncoder.encode("password123"))
                .zipcode("123-4567")
                .prefecture("Tokyo")
                .municipalities("Shibuya")
                .address("Shibuya 1-1-1")
                .telephone("090-1234-5678")
                .build();
        User user2 = User.builder().firstName("Hanako")
                .lastName("Suzuki")
                .email("hanako.suzuki@example.com")
                .password(passwordEncoder.encode("password123"))
                .zipcode("765-4321")
                .prefecture("Osaka")
                .municipalities("Naniwa")
                .address("Naniwa 2-2-2")
                .telephone("080-8765-4321").build();
        // お気に入りリストの作成(testUserそれぞれに2つのお気に入り商品を追加)
        List<Item> allItems = itemRepository.findAll();
        List<UUID> user1Favorites = new ArrayList<>();
        List<UUID> user2Favorites = new ArrayList<>();
        user1Favorites.add(allItems.get(0).getId());
        user1Favorites.add(allItems.get(1).getId());
        user1.setFavoriteItems(user1Favorites);
        userRepository.save(user1);
        user2Favorites.add(allItems.get(2).getId());
        user2Favorites.add(allItems.get(3).getId());
        user2.setFavoriteItems(user2Favorites);
        userRepository.save(user2);

//        前のやつ
//        userRepository.save(User.builder().firstName("Taro")
//                .lastName("Yamada")
//                .email("taro.yamada@example.com")
//                .password(passwordEncoder.encode("password123"))
//                .zipcode("123-4567")
//                .prefecture("Tokyo")
//                .municipalities("Shibuya")
//                .address("Shibuya 1-1-1")
//                .telephone("090-1234-5678")
//                .build());
//
//        userRepository.save(User.builder().firstName("Hanako")
//                .lastName("Suzuki")
//                .email("hanako.suzuki@example.com")
//                .password(passwordEncoder.encode("password123"))
//                .zipcode("765-4321")
//                .prefecture("Osaka")
//                .municipalities("Naniwa")
//                .address("Naniwa 2-2-2")
//                .telephone("080-8765-4321").build());
    }

    @Autowired
    private OptionGroupRepository optionGroupRepository;

    private void setUpOptionGroup() {
        if (optionGroupRepository.count() > 0) {
            return;
        }
        optionGroupRepository.save(OptionGroup.builder()
                .name("food")
                .inputType("radio")
                .build());
        optionGroupRepository.save(OptionGroup.builder()
                .name("toy")
                .inputType("checkbox")
                .build());
        optionGroupRepository.save(OptionGroup.builder()
                .name("toilet")
                .inputType("radio")
                .build());
        optionGroupRepository.save(OptionGroup.builder()
                .name("cage")
                .inputType("radio")
                .build());
        optionGroupRepository.save(OptionGroup.builder()
                .name("collar")
                .inputType("radio")
                .build());
        optionGroupRepository.save(OptionGroup.builder()
                .name("bed")
                .inputType("radio")
                .build());
        optionGroupRepository.save(OptionGroup.builder()
                .name("care")
                .inputType("checkbox")
                .build());
    }

    @Autowired
    private OptionRepository optionRepository;

    private void setUpOptions() {
        if (optionRepository.count() > 0) {
            return;
        }
        // エサオプション
        optionRepository.save(Option.builder()
                .name("なし")
                .price(0)
                .optionGroup(optionGroupRepository.findByName("food"))
                .build());
        optionRepository.save(Option.builder()
                .name("エサA")
                .price(500)
                .optionGroup(optionGroupRepository.findByName("food"))
                .build());
        optionRepository.save(Option.builder()
                .name("エサB")
                .price(600)
                .optionGroup(optionGroupRepository.findByName("food"))
                .build());
        optionRepository.save(Option.builder()
                .name("エサC")
                .price(700)
                .optionGroup(optionGroupRepository.findByName("food"))
                .build());

        // おもちゃオプション
        optionRepository.save(Option.builder()
                .name("おもちゃA")
                .price(1500)
                .optionGroup(optionGroupRepository.findByName("toy"))
                .build());
        optionRepository.save(Option.builder()
                .name("おもちゃB")
                .price(2000)
                .optionGroup(optionGroupRepository.findByName("toy"))
                .build());
        optionRepository.save(Option.builder()
                .name("おもちゃC")
                .price(2500)
                .optionGroup(optionGroupRepository.findByName("toy"))
                .build());

        // トイレオプション
        optionRepository.save(Option.builder()
                .name("なし")
                .price(0)
                .optionGroup(optionGroupRepository.findByName("toilet"))
                .build());
        optionRepository.save(Option.builder()
                .name("トイレA")
                .price(2500)
                .optionGroup(optionGroupRepository.findByName("toilet"))
                .build());
        optionRepository.save(Option.builder()
                .name("トイレB")
                .price(3000)
                .optionGroup(optionGroupRepository.findByName("toilet"))
                .build());
        optionRepository.save(Option.builder()
                .name("トイレC")
                .price(3500)
                .optionGroup(optionGroupRepository.findByName("toilet"))
                .build());

        // ケージオプション
        optionRepository.save(Option.builder()
                .name("なし")
                .price(0)
                .optionGroup(optionGroupRepository.findByName("cage"))
                .build());
        optionRepository.save(Option.builder()
                .name("ケージA")
                .price(8000)
                .optionGroup(optionGroupRepository.findByName("cage"))
                .build());
        optionRepository.save(Option.builder()
                .name("ケージB")
                .price(10000)
                .optionGroup(optionGroupRepository.findByName("cage"))
                .build());
        optionRepository.save(Option.builder()
                .name("ケージC")
                .price(12000)
                .optionGroup(optionGroupRepository.findByName("cage"))
                .build());

        // 首輪オプション
        optionRepository.save(Option.builder()
                .name("なし")
                .price(0)
                .optionGroup(optionGroupRepository.findByName("collar"))
                .build());
        optionRepository.save(Option.builder()
                .name("首輪A")
                .price(1000)
                .optionGroup(optionGroupRepository.findByName("collar"))
                .build());
        optionRepository.save(Option.builder()
                .name("首輪B")
                .price(1500)
                .optionGroup(optionGroupRepository.findByName("collar"))
                .build());
        optionRepository.save(Option.builder()
                .name("首輪C")
                .price(1400)
                .optionGroup(optionGroupRepository.findByName("collar"))
                .build());

        // ベッドオプション
        optionRepository.save(Option.builder()
                .name("なし")
                .price(0)
                .optionGroup(optionGroupRepository.findByName("bed"))
                .build());
        optionRepository.save(Option.builder()
                .name("ベッドA")
                .price(3000)
                .optionGroup(optionGroupRepository.findByName("bed"))
                .build());
        optionRepository.save(Option.builder()
                .name("ベッドB")
                .price(3500)
                .optionGroup(optionGroupRepository.findByName("bed"))
                .build());
        optionRepository.save(Option.builder()
                .name("ベッドC")
                .price(4000)
                .optionGroup(optionGroupRepository.findByName("bed"))
                .build());

        // お手入れ用品オプション
        optionRepository.save(Option.builder()
                .name("ブラシ")
                .price(500)
                .optionGroup(optionGroupRepository.findByName("care"))
                .build());
        optionRepository.save(Option.builder()
                .name("シャンプー")
                .price(800)
                .optionGroup(optionGroupRepository.findByName("care"))
                .build());
        optionRepository.save(Option.builder()
                .name("爪切り")
                .price(600)
                .optionGroup(optionGroupRepository.findByName("care"))
                .build());
    }

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;


    private void setUpOrders() {
        if (orderRepository.count() > 0) {
            return;
        }
        Order order1 = orderRepository.save(Order.builder()
                .userId(userRepository.findByEmail("taro.yamada@example.com").getId())
                .status(OrderStatus.BEFORE_ORDER)
                .totalPrice(320000)
                .orderDate(LocalDate.parse("2024-06-20"))
                .destinationName("Taro Yamada")
                .destinationEmail("taro.yamada@example.com")
                .destinationZipcode("123-4567")
                .destinationPrefecture("Tokyo")
                .destinationMunicipalities("Shibuya")
                .destinationAddress("Shibuya 1-1-1")
                .destinationTel("090-1234-5678")
                .deliveryDate(LocalDate.of(2024, 6, 27))
                .timeRange(TimeRange.RANGE_8_10)
                .paymentMethod("Credit Card")
                .build());

        Order order2 = orderRepository.save(Order.builder()
                .userId(userRepository.findByEmail("hanako.suzuki@example.com").getId())
                .status(OrderStatus.BEFORE_ORDER)
                .totalPrice(270000)
                .orderDate(LocalDate.parse("2024-06-20"))
                .destinationName("Hanako Suzuki")
                .destinationEmail("hanako.suzuki@example.com")
                .destinationZipcode("765-4321")
                .destinationPrefecture("Osaka")
                .destinationMunicipalities("Naniwa")
                .destinationAddress("Naniwa 2-2-2")
                .destinationTel("080-8765-4321")
                .deliveryDate(LocalDate.of(2024, 6, 27))
                .timeRange(TimeRange.RANGE_10_12)
                .paymentMethod("Credit Card")
                .build());

        val item1 = itemRepository.save(Item.builder()
                .description("Item1")
                .price(210000)
                .image("dachshund.png")
                .gender("male")
                .birthDay(LocalDate.of(2023, 9, 1))
                .breed(breedRepository.findByName("Dachshund"))
                .color(colorRepository.findByName("Black")).build());

        orderItemRepository.save(OrderItem.builder()
                .item(item1)
                .order(order1)
                .build());

    }

    @Override
    public void run(String... args) throws Exception {
        setUpUsers();
        setUpBreed();
        setUpColor();
        setUpItem();
        setUpOptionGroup();
        setUpOptions();
        setUpOrders();
    }
}
