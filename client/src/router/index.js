import Vue from 'vue';
import VueRouter from 'vue-router';
// Home
import HomeView from '@/views/HomeView';
// House
import HouseView from '@/views/HouseView';
import HouseListUpView from '@/components/house/HouseListUpView';
import HouseListDownView from '@/components/house/HouseListDownView';
import HouseDetailView from '@/components/house/HouseDetailView';
// Bookmark
import BookmarkView from '@/views/BookmarkView';
import BookmarkUpView from '@/components/bookmark/BookmarkUpView';
import BookmarkDownView from '@/components/bookmark/BookmarkDownView';
// Qna
import QnaView from '@/views/QnaView';
import QnaListLeftView from '@/components/qna/QnaListLeftView';
import QnaListRightView from '@/components/qna/QnaListRightView';
import QnaRegistView from '@/components/qna/QnaRegistView';
import QnaDetailView from '@/components/qna/QnaDetailView';
import QnaModifyView from '@/components/qna/QnaModifyView';

import store from '@/store';

Vue.use(VueRouter);

const onlyAuthUser = async (to, from, next) => {
  const checkUserInfo = store.getters['UserStore/checkUserInfo'];
  const checkToken = store.getters['UserStore/checkToken'];
  let token = sessionStorage.getItem('access-token');
  console.log('로그인 처리 전', checkUserInfo, token);

  if (checkUserInfo != null && token) {
    console.log('토큰 유효성 체크하러 가자!!!!');
    await store.dispatch('UserStore/getUserInfo', token);
  }
  if (!checkToken || checkUserInfo === null) {
    alert('로그인이 필요한 페이지입니다..');
    // next({ name: "login" });
    router.push({ name: 'login' });
  } else {
    console.log('로그인 했다!!!!!!!!!!!!!.');
    next();
  }
};

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
  },
  {
    path: '/user',
    name: 'user',
    component: () => import('@/views/UserView'),
    redirect: '/user/login',
    children: [
      {
        path: 'join',
        name: 'join',
        component: () => import('@/components/user/UserRegister'),
      },
      {
        path: 'login',
        name: 'login',
        component: () => import('@/components/user/UserLogin'),
      },
      {
        path: 'mypage',
        name: 'mypage',
        beforeEnter: onlyAuthUser,
        component: () => import('@/components/user/UserMyPage'),
      },
    ],
  },
  {
    path: '/house',
    name: 'HouseView',
    component: HouseView,
    redirect: '/house/houselistup',
    children: [
      {
        path: 'houselistup',
        name: 'HouseListUpView',
        component: HouseListUpView,
        redirect: '/house/houselistup/houselistdown',
        children: [
          {
            path: 'houselistdown',
            name: 'HouseListDownView',
            component: HouseListDownView,
          },
        ],
      },
      {
        path: 'housedetail',
        name: 'HouseDetailView',
        component: HouseDetailView,
      },
    ],
  },
  {
    path: '/bookmark',
    name: 'BookmarkView',
    component: BookmarkView,
    redirect: '/bookmark/bookmarkup',
    children: [
      {
        path: 'bookmarkup',
        name: 'BookmarkUpView',
        component: BookmarkUpView,
        redirect: '/bookmark/bookmarkup/bookmarkdown',
        children: [
          {
            path: 'bookmarkdown',
            name: 'BookmarkDownView',
            component: BookmarkDownView,
          },
        ],
      },
    ],
  },
  {
    path: '/qna',
    name: 'QnaView',
    component: QnaView,
    redirect: '/qna/qnalistleft',
    children: [
      {
        path: 'qnalistleft',
        name: 'QnaListLeftView',
        component: QnaListLeftView,
        redirect: '/qna/qnalistleft/qnalistright',
        children: [
          {
            path: 'qnalistright',
            name: 'QnaListRight',
            component: QnaListRightView,
          },
        ],
      },
      {
        path: 'qnadetail',
        name: 'QnaDetailView',
        component: QnaDetailView,
      },
      {
        path: 'qnaregist',
        name: 'QnaRegistView',
        component: QnaRegistView,
      },
      {
        path: 'qnamodify',
        name: 'QnaModifyView',
        component: QnaModifyView,
      },
    ],
  },
  {
    path: '/notice',
    name: 'notice',
    component: () => import('@/views/NoticeView'),
    redirect: '/notice/Noticelist',
    children: [
      {
        path: 'Noticelist',
        name: 'Noticelist',
        component: () => import('@/components/notice/NoticeListView'),
      },
      {
        path: 'Noticedetail',
        name: 'Noticedetail',
        component: () => import('@/components/notice/NoticeDetailView'),
      },
      {
        path: 'Noticeregist',
        name: 'Noticeregist',
        beforeEnter: onlyAuthUser,
        component: () => import('@/components/notice/NoticeRegistView'),
      },
    ],
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
  scrollBehavior(to, from, savedPosition) {
    return { x: 0, y: 0 };
  },
});

export default router;
