package com.example;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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

    private void setUpUsers() {
        if (userRepository.count() > 0) {
            return;
        }
        userRepository.save(User.builder().firstName("Taro")
                .lastName("Yamada")
                .email("taro.yamada@example.com")
                .password(passwordEncoder.encode("password123"))
                .zipcode("123-4567")
                .prefecture("Tokyo")
                .municipalities("Shibuya")
                .address("Shibuya 1-1-1")
                .telephone("090-1234-5678")
                .build());

        userRepository.save(User.builder().firstName("Hanako")
                .lastName("Suzuki")
                .email("hanako.suzuki@example.com")
                .password(passwordEncoder.encode("password123"))
                .zipcode("765-4321")
                .prefecture("Osaka")
                .municipalities("Naniwa")
                .address("Naniwa 2-2-2")
                .telephone("080-8765-4321").build());
    }

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
        breedRepository.save(Breed.builder().name("Munchkin").build());
        breedRepository.save(Breed.builder().name("Scottish Fold").build());
        breedRepository.save(Breed.builder().name("Sphynx").build());
        breedRepository.save(Breed.builder().name("Russian Blue").build());
        breedRepository.save(Breed.builder().name("Siamese").build());
        breedRepository.save(Breed.builder().name("Maine Coon").build());
        breedRepository.save(Breed.builder().name("Persian").build());
        breedRepository.save(Breed.builder().name("Abyssinian").build());
        breedRepository.save(Breed.builder().name("Norwegian Forest Cat").build());
        breedRepository.save(Breed.builder().name("Bengal").build());
        breedRepository.save(Breed.builder().name("etc").build());


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
        colorRepository.save(Color.builder().name("Pink").build());
    }

    @Autowired
    private ItemRepository itemRepository;

    private void setUpItem() {
        if (itemRepository.count() > 0) {
            return;
        }
        itemRepository.save(Item.builder()
                .description("柴犬は活発で忠実、賢い犬種です。勇敢で自立心が強く、しつけが比較的容易です。小型犬ながらも運動能力が高く、独特の鳴き声が特徴です。日本原産の犬種で、その美しい姿と忠実な性格から日本国内外で人気があります。")
                .price(300000)
                .image("shiba_inu.jpg")
                .gender("male")
                .birthDay(LocalDate.of(2023, 1, 1))
                .breed(breedRepository.findByName("Shiba Inu"))
                .color(colorRepository.findByName("Brown")).build());
        itemRepository.save(Item.builder()
                .description("ラブラドール・レトリバーは快活で友好的、知識が豊富で活発な犬種です。知的で訓練しやすく、家庭犬や介助犬として広く用いられています。優れた嗅覚と水泳能力を持ち、穏やかで家族に対する愛情深い性格が特徴です。")
                .price(250000)
                .image("labrador.jpg")
                .gender("female")
                .birthDay(LocalDate.of(2023, 2, 1))
                .breed(breedRepository.findByName("Labrador Retriever"))
                .color(colorRepository.findByName("Golden")).build());

        itemRepository.save(Item.builder()
                .description("ジャーマン・シェパードは、賢く忠実で勇敢な警察犬として知られる犬種です。知的で訓練しやすく、家庭での番犬や捜索犬としても適しています。")
                .price(280000)
                .image("german_shepherd.jpg")
                .gender("male")
                .birthDay(LocalDate.of(2023, 3, 1))
                .breed(breedRepository.findByName("German Shepherd"))
                .color(colorRepository.findByName("Black")).build());

        itemRepository.save(Item.builder()
                .description("ゴールデン・レトリバーは友好的で忠実、知識が豊富で社交的な犬種です。優れた家庭犬として知られ、子供や他のペットとの相性が良いです。穏やかで愛情深く、頭の良さと繊細な性格が魅力です。美しいゴールデンの被毛と笑顔が特徴的です。")
                .price(270000)
                .image("golden_retriever.jpg")
                .gender("female")
                .birthDay(LocalDate.of(2023, 4, 1))
                .breed(breedRepository.findByName("Golden Retriever"))
                .color(colorRepository.findByName("Golden")).build());

        itemRepository.save(Item.builder()
                .description("ブルドックは頑丈で力強く、穏やかで愛情深い犬種です。マスキュリンでありながらも家族を大切にし、独特の容姿とゆったりとした歩調が特徴です。短く平たい顔、ひきしまった体つきが特徴で、その見た目とは裏腹に甘えん坊な一面も持っています。")
                .price(260000)
                .image("bulldog.jpg")
                .gender("male")
                .birthDay(LocalDate.of(2023, 5, 1))
                .breed(breedRepository.findByName("Bulldog"))
                .color(colorRepository.findByName("White")).build());

        itemRepository.save(Item.builder()
                .description("プードルは知的で活発、賢く訓練しやすい犬種です。美しいカールした被毛とスマートな容姿が特徴で、様々なサイズで飼育されています。絶え間ない動きと活気に満ち、高い知能と愛情深い性格で家族との絆を築きます。パフォーマンスやショーでの成功も多いです。")
                .price(240000)
                .image("poodle.jpg")
                .gender("female")
                .birthDay(LocalDate.of(2023, 6, 1))
                .breed(breedRepository.findByName("Poodle"))
                .color(colorRepository.findByName("White")).build());

        itemRepository.save(Item.builder()
                .description("ビーグルは愛嬌があり快活で社交的、好奇心旺盛な犬種です。嗅覚が優れており、その能力を活かした捜索犬としても知られています。可愛らしい顔つきと耳、そして活発な性格が特徴で、子供や他の犬とも仲良く遊ぶことができます。スヌーピーのモデルになった犬種です！")
                .price(230000)
                .image("beagle.jpg")
                .gender("male")
                .birthDay(LocalDate.of(2023, 7, 1))
                .breed(breedRepository.findByName("Beagle"))
                .color(colorRepository.findByName("Golden")).build());

        itemRepository.save(Item.builder()
                .description("チワワは小型で活発、勇敢で警戒心が強い犬種です。家族に対しては非常に忠実で、独特の鳴き声が特徴です。小さい体格ながらも大胆で勇気があり、可愛らしい容姿と活発な性格が愛されます。")
                .price(220000)
                .image("chihuahua.jpg")
                .gender("female")
                .birthDay(LocalDate.of(2023, 8, 1))
                .breed(breedRepository.findByName("Chihuahua"))
                .color(colorRepository.findByName("Black")).build());

        itemRepository.save(Item.builder()
                .description("ダックスフンドは体格が長く、勇敢で自信に満ちた犬種です。狩猟犬としての能力があり、その優れた臭覚が特徴です。小型犬ながらもエネルギッシュで活発、愛嬌があり家族に対して愛情深い性格です。")
                .price(210000)
                .image("dachshund.jpg")
                .gender("male")
                .birthDay(LocalDate.of(2023, 9, 1))
                .breed(breedRepository.findByName("Dachshund"))
                .color(colorRepository.findByName("Black")).build());

        itemRepository.save(Item.builder()
                .description("スコティッシュフォールドは特徴的な折れ耳が特徴で、温和で穏やかな性格です。人懐っこく、家族との時間を大切にする傾向があります。可愛らしい折れ耳と丸い顔つきが特徴であり、そのかわいらしい容姿と穏やかな性格が人気の理由です。")
                .price(320000)
                .image("scottishfold.jpg")
                .gender("male")
                .birthDay(LocalDate.of(2023, 11, 12))
                .breed(breedRepository.findByName("Scottish Fold"))
                .color(colorRepository.findByName("White")).build());

        itemRepository.save(Item.builder()
                .description("メインクイーンは大柄で力強く、友好的で社交的な性格を持つ猫種です。知恵があり、しばしば「犬のような」性格と形容されます。独特の外見と大きな体躯、フレンドリーで愛情深い性格が特徴であり、家族や他のペットとの親密な関係を築きます。")
                .price(254000)
                .image("mainecoon.jpg")
                .gender("male")
                .birthDay(LocalDate.of(2023, 10, 8))
                .breed(breedRepository.findByName("Maine Coon"))
                .color(colorRepository.findByName("Brown")).build());

        itemRepository.save(Item.builder()
                .description("ペルシャは長毛で美しい容姿を持つ、穏やかで温和な性格の猫種です。静かで愛情深く、飼い主に忠実です。豪華な被毛と大きな目、穏やかな性格が魅力であり、その美しい外見と落ち着いた性格が人気を集めます。")
                .price(350000)
                .image("persian.png")
                .gender("female")
                .birthDay(LocalDate.of(2024, 5, 4))
                .breed(breedRepository.findByName("Persian"))
                .color(colorRepository.findByName("Gray")).build());

        itemRepository.save(Item.builder()
                .description("アビシニアンは活発で好奇心旺盛、賢く社交的な性格を持つ猫種です。遊び好きで知的であり、活発なライフスタイルを好みます。細身でグレースフルな体つきと短い被毛、好奇心旺盛で遊ぶことが大好きな性格が特徴です。")
                .price(322000)
                .image("abyssinian.jpg")
                .gender("female")
                .birthDay(LocalDate.of(2024, 5, 12))
                .breed(breedRepository.findByName("Abyssinian"))
                .color(colorRepository.findByName("Brown")).build());

        itemRepository.save(Item.builder()
                .description("シャムは細身でエレガント、賢く活発な性格の猫種です。飼い主に対して忠実で、コミュニケーションを重視します。美しいブルーの目とショートヘアの被毛、活発で社交的な性格が特徴であり、愛らしい声でコミュニケーションをとることが知られています。")
                .price(180000)
                .image("siamese.jpg")
                .gender("male")
                .birthDay(LocalDate.of(2024, 4, 13))
                .breed(breedRepository.findByName("Siamese"))
                .color(colorRepository.findByName("White")).build());

        itemRepository.save(Item.builder()
                .description("ノルウェージャンフォレストキャットは 大柄で力強く、愛情深く忍耐強い性格の猫種です。冒険好きで、活動的なライフスタイルを好みます。高い適応能力と自然に溶け込む能力、美しい長毛と大きな体躯が特徴であり、寒い環境に適応する能力が高いです。")
                .price(210000)
                .image("norwegianforestcat.jpg")
                .gender("female")
                .birthDay(LocalDate.of(2024, 3, 13))
                .breed(breedRepository.findByName("Norwegian Forest Cat"))
                .color(colorRepository.findByName("Gray")).build());

        itemRepository.save(Item.builder()
                .description("ベンガルは野性的な外見と穏やかで社交的な性格を持つ猫種です。活発で好奇心旺盛、遊びと冒険を愛します。美しい豹柄模様の被毛とアクティブな性格が特徴であり、知的で愛情深い一面も持ち合わせています。")
                .price(210000)
                .image("bengal.jpg")
                .gender("male")
                .birthDay(LocalDate.of(2023, 6, 2))
                .breed(breedRepository.findByName("Bengal"))
                .color(colorRepository.findByName("Brown")).build());

        itemRepository.save(Item.builder()
                .description("穏やかで静か、知的で親密な性格を持つ猫種です。被毛は短く密でシルバーグレーの色合いが美しいです。美しい被毛とエレガントな容姿、静かで親しみやすい性格が特徴であり、飼い主との絆を大切にします。")
                .price(210000)
                .image("russianblue.jpg")
                .gender("female")
                .birthDay(LocalDate.of(2022, 2, 27))
                .breed(breedRepository.findByName("Russian Blue"))
                .color(colorRepository.findByName("Gray")).build());

        itemRepository.save(Item.builder()
                .description("穏やかで静か、知的で親密な性格を持つ猫種です。被毛は短く密でシルバーグレーの色合いが美しいです。美しい被毛とエレガントな容姿、静かで親しみやすい性格が特徴であり、飼い主との絆を大切にします。")
                .price(210000)
                .image("sphynx.jpg")
                .gender("male")
                .birthDay(LocalDate.of(2024, 5, 11))
                .breed(breedRepository.findByName("Sphynx"))
                .color(colorRepository.findByName("Pink")).build());

        itemRepository.save(Item.builder()
                .description("体は小柄で、丸い顔と大きな目が特徴です。短毛で柔らかい被毛を持ち、グレーと白の模様が綺麗に分かれています。画像のように立ち上がった姿勢が印象的です。メムキャットは非常に遊び好きで、特に動くおもちゃや光に反応することが多いです。家の中を走り回って遊ぶのが大好きですこの猫の一番の魅力は、その可愛らしい外見です。特に立ち上がった姿勢は、多くの人の心を引きつけるでしょう。。")
                .price(800)
                .image("nekomeme.jpg")
                .gender("male")
                .birthDay(LocalDate.of(2024, 4, 10))
                .breed(breedRepository.findByName("etc"))
                .color(colorRepository.findByName("Cream")).build());
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
                .description("Orderにあらかじめ追加するためのitemのサンプル")
                .price(210000)
                .image("dachshund.png")
                .gender("male")
                .birthDay(LocalDate.of(2023, 9, 1))
                .breed(breedRepository.findByName("Dachshund"))
                .color(colorRepository.findByName("Black")).build());

        // オプションを追加
        val options = List.of(
                optionRepository.findByName("エサA"),
                optionRepository.findByName("おもちゃA")
        );

        orderItemRepository.save(OrderItem.builder()
                .item(item1)
                .order(order1)
                .options(options)
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
