<template>
  <div id="map"></div>
  <!-- <div class="button-group">
      <button @click="displayMarker(markerPositions2)">marker set 2</button>
    </div> -->
</template>

<script>
import { mapGetters } from 'vuex';
const BookmarkStore = 'BookmarkStore';

export default {
  name: 'BookmarkKakaoMap',
  data() {
    return {
      markerPositions1: [],
      markerPositions2: [
        [37.499590490909185, 127.0263723554437],
        [37.499427948430814, 127.02794423197847],
        [37.498553760499505, 127.02882598822454],
        [37.497625593121384, 127.02935713582038],
        [37.49629291770947, 127.02587362608637],
        [37.49754540521486, 127.02546694890695],
        [37.49646391248451, 127.02675574250912],
      ],
      markers: [],
      infowindow: null,
    };
  },
  computed: {
    // 감시하고 싶은 state..
    ...mapGetters(BookmarkStore, ['houses']),
  },
  watch: {
    houses: function () {
      this.markerPositions1 = [];
      this.houses.forEach((house) => {
        this.markerPositions1.push([house.lat, house.lng]);
      });
      this.displayMarker(this.markerPositions1);
    },
  },
  mounted() {
    // if (window.kakao && window.kakao.maps) {
    //   this.initMap();
    // } else {
    const script = document.createElement('script');
    /* global kakao */
    script.onload = () =>
      kakao.maps.load(() => {
        this.initMap();
      });
    script.src = '//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=c456fa250620e61ec4c150b04030a8f6';
    document.head.appendChild(script);
    // }
  },
  updated() {
    // this.initMap();
  },
  methods: {
    initMap() {
      const container = document.getElementById('map');
      // if (this.houses == []) {
      //   const options = {
      // center: new kakao.maps.LatLng(this.houses[0].lat, this.houses[0].lng),
      //     level: 8,
      //   };
      // } else {
      const options = {
        center: new kakao.maps.LatLng(37.541, 126.986),
        level: 8,
      };
      // }

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
  width: 600px;
  height: 450px;
}

.button-group {
  margin: 10px 0px;
}

button {
  margin: 0 3px;
}
</style>
