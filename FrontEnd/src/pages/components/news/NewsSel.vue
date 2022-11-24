<template>
  <div class="container" style="width: 100%">
    <h4>News</h4>
    <div class="row justify-content-center">
      <div class="col-12">
        <el-carousel height="300px">
          <el-carousel-item>
            <a
              href="https://www.chosun.com/6VSVRAR6DVFKPCXP2L3AD65RVM/"
              target="_blank"
              ><img class="d-block" src="img/News1.jpg" alt="First slide"
            /></a>
            <div class="carousel-caption d-none d-md-block">
              <h5>
                ‘부동산 거래절벽 심화’…서울 아파트 거래량 ‘사상 최저’ 지속
              </h5>
            </div>
          </el-carousel-item>
          <el-carousel-item>
            <a
              href="https://www.msn.com/ko-kr/money/topstories/%EB%B6%80%EB%8F%99%EC%82%B0-%EA%B7%9C%EC%A0%9C%EC%99%84%ED%99%94%EC%97%90%EB%8F%84-%EC%A3%BC%ED%83%9D%EA%B1%B0%EB%9E%98-%EA%BD%81%EA%BD%81-%EA%B3%B5%EC%8B%9C%EA%B0%80-%EC%97%AD%EC%A0%84-%ED%98%84%EC%83%81%EA%B9%8C%EC%A7%80/ar-AA14aSKc"
              target="_blank"
              ><img
                class="d-block"
                src="img/News2.jpg"
                alt="First slide" /><img
            /></a>
            <div class="carousel-caption d-none d-md-block">
              <h5>
                부동산 규제완화에도 주택거래 ‘꽁꽁’… ‘공시가 역전’ 현상까지
              </h5>
            </div>
          </el-carousel-item>
          <el-carousel-item>
            <a
              href="https://www.yna.co.kr/view/AKR20221123083100003?input=1195m"
              target="_blank"
              ><img
                class="d-block"
                src="img/News3.jpg"
                alt="First slide" /><img
            /></a>
            <div class="carousel-caption d-none d-md-block">
              <h5>공시가·재산세 손질…내년 부동산 보유세 2020년으로 되돌린다</h5>
            </div>
          </el-carousel-item>
        </el-carousel>
      </div>
    </div>
  </div>
</template>

<script>
import { Carousel, CarouselItem } from "element-ui";

export default {
  name: "NewsSel",
  components: {
    [Carousel.name]: Carousel,
    [CarouselItem.name]: CarouselItem,
  },
};
</script>

<style></style>
