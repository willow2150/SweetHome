<template>
  <div class="container" style="width: 100%">
    <h4>News</h4>
    <div class="row justify-content-center">
      <div class="col-12">
        <el-carousel height="300px">
          <el-carousel-item>
            <img class="d-block" src="img/News1.jpg" alt="First slide" />
            <div class="carousel-caption d-none d-md-block">
              <h5>
                ‘부동산 거래절벽 심화’…서울 아파트 거래량 ‘사상 최저’ 지속
              </h5>
            </div>
          </el-carousel-item>
          <el-carousel-item>
            <img class="d-block" src="img/News2.jpg" alt="First slide" />
            <div class="carousel-caption d-none d-md-block">
              <h5>
                부동산 규제완화에도 주택거래 ‘꽁꽁’… ‘공시가 역전’ 현상까지
              </h5>
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
