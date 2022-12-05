<template>
  <div>
    <div id="map"></div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
const HouseStore = 'HouseStore';

export default {
  name: 'KakaoMap',
  data() {
    return {
      markerPositions1: [],
      markers: [],
      infowindow: null,
    };
  },
  computed: {
    // 감시하고 싶은 state..
    ...mapGetters(HouseStore, ['house']),
  },
  mounted() {
    console.log('mounted.............................: ', this.house);
    const script = document.createElement('script');
    /* global kakao */
    script.onload = () =>
      kakao.maps.load(() => {
        console.log(this.house);
        this.initMap();
        this.markerPositions1 = [[this.house.lat, this.house.lng]];
        this.displayMarker(this.markerPositions1);
      });
    script.src = '//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=c456fa250620e61ec4c150b04030a8f6';
    document.head.appendChild(script);
  },
  methods: {
    initMap() {
      const container = document.getElementById('map');
      const options = {
        center: new kakao.maps.LatLng(this.house.lat, this.house.lng),
        level: 5,
      };

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);
    },
    displayMarker(markerPositions) {
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }

      const positions = markerPositions.map((position) => new kakao.maps.LatLng(...position));

      if (positions.length > 0) {
        this.markers = positions.map(
          (position) =>
            new kakao.maps.Marker({
              map: this.map,
              position,
            })
        );

        const bounds = positions.reduce((bounds, latlng) => bounds.extend(latlng), new kakao.maps.LatLngBounds());

        this.map.setBounds(bounds);
      }
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#map {
  width: 450px;
  height: 400px;
}

.button-group {
  margin: 10px 0px;
}

button {
  margin: 0 3px;
}
</style>
