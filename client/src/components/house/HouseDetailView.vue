<template>
  <div class="row align-items-center justify-content-center" style="width: 100%">
    <div class="col-lg-5 col-md-6 order-2 order-md-1">
      <div class="widget" style="width: 100%">
        <h4 class="widget-title"><span>아파트 상세정보</span></h4>
        <div class="order-2 order-lg-3 align-items-center justify-content-center">
          <h5 style="text-align: center">
            {{ house.dongName }} {{ house.jibun }}번지 {{ house.aptName }} - {{ house.floor }}층
          </h5>
          <div class="mt-4" style="margin: 100 auto">
            <div class="mb-3 ml-2">
              <label for="character" class="font-weight-bold">면적/타입 : </label>
              {{ house.area }}㎡ / {{ house.type }}타입
            </div>
            <div class="mb-3 ml-2">
              <label for="character" class="font-weight-bold">해당층 : </label>
              {{ house.floor }}층
            </div>
            <div class="mb-3 ml-2"><label for="character" class="font-weight-bold">융자금 : </label> 없음</div>
            <div class="mb-3 ml-2">
              <label for="character" class="font-weight-bold">난방(방식/연료) : </label> 개별난방/도시가스
            </div>
            <div class="mb-3 ml-2">
              <label for="character" class="font-weight-bold">입주가능일 : </label>
              즉시입주 협의가능
            </div>
            <div class="mb-3 ml-2">
              <label for="character" class="font-weight-bold">준공날짜 : </label>
              {{ house.buildYear }}년
            </div>
            <div class="mb-3 ml-2">
              <label for="character" class="font-weight-bold">거래금액 : </label>
              {{ house.dealAmount }}만원
            </div>
            <div class="mb-3 ml-2">
              <label for="character" class="font-weight-bold">최근 거래날짜 : </label> {{ house.dealYear }}년
              {{ house.dealMonth }}월 {{ house.dealDay }}일
            </div>
          </div>
          <button class="btn btn-primary mr-3 ml-3" id="regist" @click="moveHandler">이전</button>
        </div>
      </div>
    </div>
    <div class="col-lg-5 col-md-6 mb-4 mb-md-0 order-1 order-md-2">
      <div class="image-wrapper">
        <kakao-map></kakao-map>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import KakaoMap from '@/components/KakaoMap.vue';
const HouseStore = 'HouseStore';
export default {
  components: {
    KakaoMap,
  },
  data() {
    return {
      no: '',
    };
  },
  computed: {
    // 감시하고 싶은 state..
    ...mapGetters(HouseStore, ['house']),
  },
  created() {
    //DOM객체와 연결되었고 Vue의 속성들도 초기화 된 상태
  },
  methods: {
    moveHandler() {
      this.$router.push({ name: 'HouseView' });
    },
  },
};
</script>

<style></style>
