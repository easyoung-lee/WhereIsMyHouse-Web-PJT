import jwtDecode from 'jwt-decode';
import router from '@/router';
import { login, findById, tokenRegeneration, logout, register, update, remove } from '@/api/user';

const userStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: null,
    isValidToken: false,
  },
  getters: {
    checkUserInfo: function (state) {
      console.log('checkUserInfo : ', state.userInfo);
      return state.userInfo;
    },
    checkToken: function (state) {
      return state.isValidToken;
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      console.log('SET_IS_LOGIN........................isLogin :', isLogin);
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_IS_VALID_TOKEN: (state, isValidToken) => {
      state.isValidToken = isValidToken;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
      console.log('userInfo : ', userInfo);
    },
  },
  actions: {
    async userConfirm({ commit }, user) {
      console.log('userConfirm : ', user);
      await login(
        user,
        ({ data }) => {
          if (data.message === 'success') {
            let accessToken = data['access-token'];
            let refreshToken = data['refresh-token'];
            console.log('login success token created!!!! >> ', accessToken, refreshToken);
            commit('SET_IS_LOGIN', true);
            commit('SET_IS_LOGIN_ERROR', false);
            commit('SET_IS_VALID_TOKEN', true);
            sessionStorage.setItem('access-token', accessToken);
            sessionStorage.setItem('refresh-token', refreshToken);
          } else {
            commit('SET_IS_LOGIN', false);
            commit('SET_IS_LOGIN_ERROR', true);
            commit('SET_IS_VALID_TOKEN', false);
            console.log('여긴가?');
          }
        },
        (error) => {
          console.log('userConfirm Error : ', user);
          console.log(error);
        }
      );
    },
    async getUserInfo({ commit, dispatch }, token) {
      let decodeToken = jwtDecode(token);
      console.log('2. getUserInfo() decodeToken :: ', decodeToken);
      console.log('getUserInfo token : ', token);
      console.log('getUserInfo decodeToken.id : ', decodeToken.id);
      await findById(
        decodeToken.id,
        ({ data }) => {
          if (data.message === 'success') {
            commit('SET_USER_INFO', data.userInfo);
            console.log('getUserInfo : ', data.userInfo);
          } else {
            console.log('유저 정보 없음!!!!');
          }
        },
        async (error) => {
          console.log('getUserInfo() error code [토큰 만료되어 사용 불가능.] ::: ', error.response.status);
          commit('SET_IS_VALID_TOKEN', false);
          await dispatch('tokenRegeneration');
        }
      );
    },
    async tokenRegeneration({ commit, state }) {
      console.log('토큰 재발급 >> 기존 토큰 정보 : {}', sessionStorage.getItem('access-token'));
      await tokenRegeneration(
        JSON.stringify(state.userInfo),
        ({ data }) => {
          if (data.message === 'success') {
            let accessToken = data['access-token'];
            console.log('재발급 완료 >> 새로운 토큰 : {}', accessToken);
            sessionStorage.setItem('access-token', accessToken);
            commit('SET_IS_VALID_TOKEN', true);
          }
        },
        async (error) => {
          // HttpStatus.UNAUTHORIZE(401) : RefreshToken 기간 만료 >> 다시 로그인!!!!
          if (error.response.status === 401) {
            console.log('갱신 실패');
            // 다시 로그인 전 DB에 저장된 RefreshToken 제거.
            await logout(
              state.userInfo.id,
              ({ data }) => {
                if (data.message === 'success') {
                  console.log('리프레시 토큰 제거 성공');
                } else {
                  console.log('리프레시 토큰 제거 실패');
                }
                alert('RefreshToken 기간 만료!!! 다시 로그인해 주세요.');
                commit('SET_IS_LOGIN', false);
                commit('SET_USER_INFO', null);
                commit('SET_IS_VALID_TOKEN', false);
                router.push({ name: 'login' });
              },
              (error) => {
                console.log(error);
                commit('SET_IS_LOGIN', false);
                commit('SET_USER_INFO', null);
              }
            );
          }
        }
      );
    },
    async userLogout({ commit }, id) {
      await logout(
        id,
        ({ data }) => {
          if (data.message === 'success') {
            commit('SET_IS_LOGIN', false);
            commit('SET_USER_INFO', null);
            commit('SET_IS_VALID_TOKEN', false);
          } else {
            console.log('유저 정보 없음!!!!');
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async getRegisterUser({ commit }, user) {
      console.log('[actions]....................................getRegisterUser : ', user);
      await register(
        user,
        ({ data }) => {
          alert('반갑습니다! \n회원님이 원하시는 집을 찾으실때까지 노력하겠습니다!');
        },
        (error) => {
          console.log('getRegisterUser Error : ', user);
          console.log(error);
          alert('중복된 아이디로, 사용이 불가능합니다. \n다른 아이디로 다시 시도해주세요!');
        }
      );
    },
    async getUpdateUser({ commit }, user) {
      console.log('[actions]....................................getUpdateUser : ', user);
      await update(
        user,
        ({ data }) => {
          commit('SET_USER_INFO', data);
        },
        (error) => {
          console.log('getUpdateUser Error : ', user);
          console.log(error);
        }
      );
    },
    async getRemoveUser({ commit }, id) {
      console.log('[actions]....................................getRemoveUser : ', id);
      await remove(
        id,
        ({ data }) => {
          console.log('remove................................... : ', data);
          if (data === 'success') {
            commit('SET_IS_LOGIN', false);
            commit('SET_USER_INFO', null);
            commit('SET_IS_VALID_TOKEN', false);
            alert('탈퇴처리가 완료되었습니다.\n저희는 회원님을 언제나 기다립니다');
          } else {
            console.log('유저 정보 없음!!!!');
          }
        },
        (error) => {
          console.log('getRemoveUser Error : ', id);
          console.log(error);
        }
      );
    },
  },
};

export default userStore;
