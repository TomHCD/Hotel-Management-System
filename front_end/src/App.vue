<template>
  <div id="app" v-title data-title="NJUSE酒店管理系统">
    <a-locale-provider :locale="zh_CN">
    <transition name="fade-transform" mode="out-in">
      <router-view/>
    </transition>
    </a-locale-provider>
  </div>
</template>
<script>
  import zh_CN from 'ant-design-vue/lib/locale-provider/zh_CN';
  import moment from 'moment';
  import 'moment/locale/zh-cn';

  moment.locale('zh-cn');
export default {
  data() {
    return {
      zh_CN,
    };
  },
  components: {
    
  }
}
</script>
<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  width: 100%;
  padding: 20px 100px 144px;
  background: #f0f2f5 url('assets/background.svg') repeat 100%;
  min-height: 800px
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}

/* fade-transform */
.fade-transform-leave-active,
.fade-transform-enter-active {
  transition: all .5s;
}

.fade-transform-enter {
  opacity: 0;
  transform: translateX(-30px);
}

.fade-transform-leave-to {
  opacity: 0;
  transform: translateX(30px);
}
</style>
