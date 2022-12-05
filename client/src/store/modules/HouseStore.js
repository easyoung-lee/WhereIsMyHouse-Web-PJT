import http from '@/api/http';

const HouseStore = {
  namespaced: true,
  state: {
    houses: [],
    house: [],
    aptName: [],
    pgno: [],
    no: [],
    sido: [],
    gugun: [],
    dong: [],
  },
  getters: {
    houses(state) {
      return state.houses;
    },
    house(state) {
      return state.house;
    },
    aptName(state) {
      return state.aptName;
    },
    pgno(state) {
      return state.pgno;
    },
    no(state) {
      return state.no;
    },
    sido(state) {
      return state.sido;
    },
    gugun(state) {
      return state.gugun;
    },
    dong(state) {
      return state.dong;
    },
  },
  mutations: {
    setHouses(state, payload) {
      console.log('[mutations]..................................houses :', payload);
      state.houses = payload;
    },
    setHouse(state, payload) {
      console.log('[mutations]..................................house :', payload);
      state.house = payload;
    },
    setAptName(state, payload) {
      console.log('[mutations]..................................questions :', payload);
      state.aptName = payload;
    },
    setPgno(state, payload) {
      console.log('[mutations]..................................questions :', payload);
      state.pgno = payload;
    },
    setNo(state, payload) {
      console.log('[mutations]..................................questions :', payload);
      state.no = payload;
    },
    setSido(state, payload) {
      console.log('[mutations]..................................questions :', payload);
      state.sido = payload;
    },
    setGugun(state, payload) {
      console.log('[mutations]..................................questions :', payload);
      state.gugun = payload;
    },
    setDong(state, payload) {
      console.log('[mutations]..................................questions :', payload);
      state.dong = payload;
    },
  },
  actions: {
    getAptDatas({ commit }, payload) {
      console.log('[actions]..................................getAptDatas 조회 조건:', payload);
      http
        .get(
          `house?pgno=${payload.pgno}&sido=${payload.sido}&gugun=${payload.gugun}&dong=${payload.dong}&aptName=${payload.aptName}`
        )
        .then(({ data }) => {
          console.log('[actions]..................................getAptDatas :', data);
          commit('setHouses', data);
          commit('setPgno', payload.pgno);
          commit('setSido', payload.sido);
          commit('setGugun', payload.gugun);
          commit('setDong', payload.dong);
          commit('setAptName', payload.aptName);
        })
        .catch(({ response }) => {
          console.log('[actions]..................................getAptDatas 2 :', data);
          alert(response.data);
        });
    },
    getAptData({ commit }, payload) {
      console.log('[actions]..................................getAptData 조회 조건:', payload);
      http
        .get(`house/${payload}`)
        .then(({ data }) => {
          console.log('[actions]..................................getAptData 1 :', data);
          commit('setHouse', data);
        })
        .catch(({ response }) => {
          console.log('[actions]..................................getAptData 2 :', data);
          alert(response.data);
        });
    },
    initAptDatas({ commit }) {
      commit('setHouses', []);
    },
  },
};

export default HouseStore;
