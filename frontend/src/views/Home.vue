<template>
  <div id="app">
    <h1> {{ title }} </h1>
    <welcome></welcome>
  </div>
</template>

<script>
import Welcome from "@/components/Welcome.vue";

export default {
  name: 'App',
  components: {
    'welcome': Welcome,
  },
  data() {
    return {
      title: "Khala"
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>

