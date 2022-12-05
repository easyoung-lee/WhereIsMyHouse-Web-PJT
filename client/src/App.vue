<template>
  <div id="app">
    <body>
      <nav-header></nav-header>
      <ban-header></ban-header>
      <router-view />
      <main-footer></main-footer>
    </body>
  </div>
</template>

<script>
import NavHeader from '@/components/NavHeader.vue';
import MainFooter from '@/components/MainFooter.vue';
import BanHeader from '@/components/BanHeader.vue';

export default {
  name: 'App',
  components: {
    NavHeader,
    MainFooter,
    BanHeader,
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
