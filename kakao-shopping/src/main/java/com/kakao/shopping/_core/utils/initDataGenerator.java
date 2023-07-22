package com.kakao.shopping._core.utils;

import com.kakao.shopping.domain.ProductOption;
import com.kakao.shopping.domain.Product;
import com.kakao.shopping.repository.OptionRepository;
import com.kakao.shopping.repository.ProductRepository;
import com.kakao.shopping.service.ProductService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class initDataGenerator implements ApplicationRunner {
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final OptionRepository optionRepository;
    List<Product> products;
    List<ProductOption> productOptions;

    public initDataGenerator(ProductService productService, ProductRepository productRepository, OptionRepository optionRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
        this.optionRepository = optionRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        createProduct();
        createOption();
    }

    private void createUser() {

    }

    private void createProduct() {
//        this.products = productService.saveProducts(
//                Arrays.asList(
//                        new ProductRequest.Insert("기본에 슬라이딩 지퍼백 크리스마스/플라워에디션 에디션 외 주방용품 특가전", "", "images/1", 1000L),
//                        new ProductRequest.Insert("[황금약단밤 골드]2022년산 햇밤 칼집밤700g외/군밤용/생율", "", "images/2", 2000L),
//                        new ProductRequest.Insert("삼성전자 JBL JR310 외 어린이용/성인용 헤드셋 3종!", "", "images/3", 30000L),
//                        new ProductRequest.Insert("바른 누룽지맛 발효효소 2박스 역가수치보장 / 외 7종", "", "images/4", 4000L),
//                        new ProductRequest.Insert("[더주] 컷팅말랑장족, 숏다리 100g/300g 외 주전부리 모음 /중독성 최고/마른안주", "", "images/5", 5000L),
//                        new ProductRequest.Insert("굳지않는 앙금절편 1,050g 2팩 외 우리쌀떡 모음전", "", "images/6", 15900L),
//                        new ProductRequest.Insert("eoe 이너딜리티 30포, 오렌지맛 고 식이섬유 보충제", "", "images/7", 26800L),
//                        new ProductRequest.Insert("제나벨 PDRN 크림 2개. 피부보습/진정 케어", "", "images/8", 25900L),
//                        new ProductRequest.Insert("플레이스테이션 VR2 호라이즌 번들. 생생한 몰입감", "", "images/9", 797000L),
//                        new ProductRequest.Insert("통영 홍 가리비 2kg, 2세트 구매시 1kg 추가증정", "", "images/10", 8900L),
//                        new ProductRequest.Insert("아삭한 궁채 장아찌 1kg 외 인기 반찬 모음전", "", "images/11", 6900L),
//                        new ProductRequest.Insert("깨끗한나라 순수소프트 30롤 2팩. 무형광, 도톰 3겹", "", "images/12", 28900L),
//                        new ProductRequest.Insert("생활공작소 초미세모 칫솔 12입 2개+가글 증정", "", "images/13", 9900L),
//                        new ProductRequest.Insert("경북 영천 샤인머스켓 가정용 1kg 2수 내외", "", "images/14", 9900L),
//                        new ProductRequest.Insert("[LIVE][5%쿠폰] 홈카페 Y3.3 캡슐머신 베이직 세트", "", "images/15", 1480L)
//                )
//        );
        this.products = productRepository.saveAll(
                List.of(
                        Product.of("기본에 슬라이딩 지퍼백 크리스마스/플라워에디션 에디션 외 주방용품 특가전", "", "images/1", 1000L),
                        Product.of("[황금약단밤 골드]2022년산 햇밤 칼집밤700g외/군밤용/생율", "", "images/2", 2000L),
                        Product.of("삼성전자 JBL JR310 외 어린이용/성인용 헤드셋 3종!", "", "images/3", 30000L),
                        Product.of("바른 누룽지맛 발효효소 2박스 역가수치보장 / 외 7종", "", "images/4", 4000L),
                        Product.of("[더주] 컷팅말랑장족, 숏다리 100g/300g 외 주전부리 모음 /중독성 최고/마른안주", "", "images/5", 5000L),
                        Product.of("굳지않는 앙금절편 1,050g 2팩 외 우리쌀떡 모음전", "", "images/6", 15900L),
                        Product.of("eoe 이너딜리티 30포, 오렌지맛 고 식이섬유 보충제", "", "images/7", 26800L),
                        Product.of("제나벨 PDRN 크림 2개. 피부보습/진정 케어", "", "images/8", 25900L),
                        Product.of("플레이스테이션 VR2 호라이즌 번들. 생생한 몰입감", "", "images/9", 797000L),
                        Product.of("통영 홍 가리비 2kg, 2세트 구매시 1kg 추가증정", "", "images/10", 8900L),
                        Product.of("아삭한 궁채 장아찌 1kg 외 인기 반찬 모음전", "", "images/11", 6900L),
                        Product.of("깨끗한나라 순수소프트 30롤 2팩. 무형광, 도톰 3겹", "", "images/12", 28900L),
                        Product.of("생활공작소 초미세모 칫솔 12입 2개+가글 증정", "", "images/13", 9900L),
                        Product.of("경북 영천 샤인머스켓 가정용 1kg 2수 내외", "", "images/14", 9900L),
                        Product.of("[LIVE][5%쿠폰] 홈카페 Y3.3 캡슐머신 베이직 세트", "", "images/15", 1480L)
                )
        );
    }

    private void createOption() {
//        this.options = productService.saveOptions(
//                Arrays.asList(
//                        new OptionRequest.Insert(products.get(0), "01. 슬라이딩 지퍼백 크리스마스에디션 4종", 10000L),
//                        new OptionRequest.Insert(products.get(0), "02. 슬라이딩 지퍼백 플라워에디션 5종", 10900L),
//                        new OptionRequest.Insert(products.get(0), "고무장갑 베이지 S(소형) 6팩", 9900L),
//                        new OptionRequest.Insert(products.get(0), "뽑아쓰는 키친타올 130매 12팩", 16900L),
//                        new OptionRequest.Insert(products.get(0), "2겹 식빵수세미 6매", 8900L),
//                        new OptionRequest.Insert(products.get(1), "22년산 햇단밤 700g(한정판매)", 9900L),
//                        new OptionRequest.Insert(products.get(1), "22년산 햇단밤 1kg(한정판매)", 14500L),
//                        new OptionRequest.Insert(products.get(1), "밤깎기+다회용 구이판 세트", 5500L),
//                        new OptionRequest.Insert(products.get(2), "JR310 (유선 전용) - 블루", 29900L),
//                        new OptionRequest.Insert(products.get(2), "JR310BT (무선 전용) - 레드", 49900L),
//                        new OptionRequest.Insert(products.get(2), "JR310BT (무선 전용) - 그린", 49900L),
//                        new OptionRequest.Insert(products.get(2), "JR310BT (무선 전용) - 블루", 49900L),
//                        new OptionRequest.Insert(products.get(2), "T510BT (무선 전용) - 블랙", 52900L),
//                        new OptionRequest.Insert(products.get(2), "T510BT (무선 전용) - 화이트", 52900L),
//                        new OptionRequest.Insert(products.get(3), "선택01_바른곡물효소 누룽지맛 2박스", 17900L),
//                        new OptionRequest.Insert(products.get(3), "선택02_바른곡물효소누룽지맛 6박스", 50000L),
//                        new OptionRequest.Insert(products.get(3), "선택03_바른곡물효소3박스+유산균효소3박스", 50000L),
//                        new OptionRequest.Insert(products.get(3), "선택04_바른곡물효소3박스+19종유산균3박스", 50000L),
//                        new OptionRequest.Insert(products.get(4), "01. 말랑컷팅장족 100g", 4900L),
//                        new OptionRequest.Insert(products.get(4), "02. 말랑컷팅장족 300g", 12800L),
//                        new OptionRequest.Insert(products.get(4), "03. 눌린장족 100g", 4900L),
//                        new OptionRequest.Insert(products.get(5), "굳지않는 쑥 앙금 절편 1050g", 15900L),
//                        new OptionRequest.Insert(products.get(5), "굳지않는 흑미 앙금 절편 1050g", 15900L),
//                        new OptionRequest.Insert(products.get(5), "굳지않는 흰 가래떡 1050g", 15900L),
//                        new OptionRequest.Insert(products.get(6), "이너딜리티 1박스", 26800L),
//                        new OptionRequest.Insert(products.get(6), "이너딜리티 2박스+사은품 2종", 49800L),
//                        new OptionRequest.Insert(products.get(7), "제나벨 PDRN 자생크림 1+1", 25900L),
//                        new OptionRequest.Insert(products.get(8),"플레이스테이션 VR2 호라이즌 번들", 839000L),
//                        new OptionRequest.Insert(products.get(8), "플레이스테이션 VR2", 797000L),
//                        new OptionRequest.Insert(products.get(9), "홍가리비2kg(50미이내)", 8900L),
//                        new OptionRequest.Insert(products.get(10), "궁채 절임 1kg", 6900L),
//                        new OptionRequest.Insert(products.get(10), "양념 깻잎 1kg", 8900L),
//                        new OptionRequest.Insert(products.get(10), "된장 깻잎 1kg", 8900L),
//                        new OptionRequest.Insert(products.get(10), "간장 깻잎 1kg", 7900L),
//                        new OptionRequest.Insert(products.get(10), "고추 무침 1kg", 8900L),
//                        new OptionRequest.Insert(products.get(10), "파래 무침 1kg", 9900L),
//                        new OptionRequest.Insert(products.get(11), "01_순수소프트 27m 30롤 2팩", 28900L),
//                        new OptionRequest.Insert(products.get(11), "02_벚꽃 프리미엄 27m 30롤 2팩", 32900L),
//                        new OptionRequest.Insert(products.get(12), "(증정) 초미세모 칫솔 12개 x 2개", 11900L),
//                        new OptionRequest.Insert(products.get(12), "(증정) 잇몸케어 치약 100G 3개 x 2개", 16900L),
//                        new OptionRequest.Insert(products.get(12), "(증정) 구취케어 치약 100G 3개 x 2개", 16900L),
//                        new OptionRequest.Insert(products.get(12), "(증정)화이트케어 치약 100G 3개 x 2개", 19900L),
//                        new OptionRequest.Insert(products.get(12), "(증정) 어린이 칫솔 12EA", 9900L),
//                        new OptionRequest.Insert(products.get(13), "[가정용] 샤인머스켓 1kg 2수내외", 9900L),
//                        new OptionRequest.Insert(products.get(13), "[특품] 샤인머스켓 1kg 1-2수", 12900L),
//                        new OptionRequest.Insert(products.get(13), "[특품] 샤인머스켓 2kg 2-3수", 23900L),
//                        new OptionRequest.Insert(products.get(14), "화이트", 148000L),
//                        new OptionRequest.Insert(products.get(14), "블랙", 14800L)
//                )
//        );
        this.productOptions = optionRepository.saveAll(
                List.of(
                        ProductOption.of(products.get(0), "01. 슬라이딩 지퍼백 크리스마스에디션 4종", 10000L),
                        ProductOption.of(products.get(0), "02. 슬라이딩 지퍼백 플라워에디션 5종", 10900L),
                        ProductOption.of(products.get(0), "고무장갑 베이지 S(소형) 6팩", 9900L),
                        ProductOption.of(products.get(0), "뽑아쓰는 키친타올 130매 12팩", 16900L),
                        ProductOption.of(products.get(0), "2겹 식빵수세미 6매", 8900L),
                        ProductOption.of(products.get(1), "22년산 햇단밤 700g(한정판매)", 9900L),
                        ProductOption.of(products.get(1), "22년산 햇단밤 1kg(한정판매)", 14500L),
                        ProductOption.of(products.get(1), "밤깎기+다회용 구이판 세트", 5500L),
                        ProductOption.of(products.get(2), "JR310 (유선 전용) - 블루", 29900L),
                        ProductOption.of(products.get(2), "JR310BT (무선 전용) - 레드", 49900L),
                        ProductOption.of(products.get(2), "JR310BT (무선 전용) - 그린", 49900L),
                        ProductOption.of(products.get(2), "JR310BT (무선 전용) - 블루", 49900L),
                        ProductOption.of(products.get(2), "T510BT (무선 전용) - 블랙", 52900L),
                        ProductOption.of(products.get(2), "T510BT (무선 전용) - 화이트", 52900L),
                        ProductOption.of(products.get(3), "선택01_바른곡물효소 누룽지맛 2박스", 17900L),
                        ProductOption.of(products.get(3), "선택02_바른곡물효소누룽지맛 6박스", 50000L),
                        ProductOption.of(products.get(3), "선택03_바른곡물효소3박스+유산균효소3박스", 50000L),
                        ProductOption.of(products.get(3), "선택04_바른곡물효소3박스+19종유산균3박스", 50000L),
                        ProductOption.of(products.get(4), "01. 말랑컷팅장족 100g", 4900L),
                        ProductOption.of(products.get(4), "02. 말랑컷팅장족 300g", 12800L),
                        ProductOption.of(products.get(4), "03. 눌린장족 100g", 4900L),
                        ProductOption.of(products.get(5), "굳지않는 쑥 앙금 절편 1050g", 15900L),
                        ProductOption.of(products.get(5), "굳지않는 흑미 앙금 절편 1050g", 15900L),
                        ProductOption.of(products.get(5), "굳지않는 흰 가래떡 1050g", 15900L),
                        ProductOption.of(products.get(6), "이너딜리티 1박스", 26800L),
                        ProductOption.of(products.get(6), "이너딜리티 2박스+사은품 2종", 49800L),
                        ProductOption.of(products.get(7), "제나벨 PDRN 자생크림 1+1", 25900L),
                        ProductOption.of(products.get(8),"플레이스테이션 VR2 호라이즌 번들", 839000L),
                        ProductOption.of(products.get(8), "플레이스테이션 VR2", 797000L),
                        ProductOption.of(products.get(9), "홍가리비2kg(50미이내)", 8900L),
                        ProductOption.of(products.get(10), "궁채 절임 1kg", 6900L),
                        ProductOption.of(products.get(10), "양념 깻잎 1kg", 8900L),
                        ProductOption.of(products.get(10), "된장 깻잎 1kg", 8900L),
                        ProductOption.of(products.get(10), "간장 깻잎 1kg", 7900L),
                        ProductOption.of(products.get(10), "고추 무침 1kg", 8900L),
                        ProductOption.of(products.get(10), "파래 무침 1kg", 9900L),
                        ProductOption.of(products.get(11), "01_순수소프트 27m 30롤 2팩", 28900L),
                        ProductOption.of(products.get(11), "02_벚꽃 프리미엄 27m 30롤 2팩", 32900L),
                        ProductOption.of(products.get(12), "(증정) 초미세모 칫솔 12개 x 2개", 11900L),
                        ProductOption.of(products.get(12), "(증정) 잇몸케어 치약 100G 3개 x 2개", 16900L),
                        ProductOption.of(products.get(12), "(증정) 구취케어 치약 100G 3개 x 2개", 16900L),
                        ProductOption.of(products.get(12), "(증정)화이트케어 치약 100G 3개 x 2개", 19900L),
                        ProductOption.of(products.get(12), "(증정) 어린이 칫솔 12EA", 9900L),
                        ProductOption.of(products.get(13), "[가정용] 샤인머스켓 1kg 2수내외", 9900L),
                        ProductOption.of(products.get(13), "[특품] 샤인머스켓 1kg 1-2수", 12900L),
                        ProductOption.of(products.get(13), "[특품] 샤인머스켓 2kg 2-3수", 23900L),
                        ProductOption.of(products.get(14), "화이트", 148000L),
                        ProductOption.of(products.get(14), "블랙", 14800L)
                )
        );
    }
}
