<template>
  <div id="Welcome">
    <h1>Welcome to Khala</h1>
    <h1 v-if="nameInput === ''">{{defaultText}}</h1>
    <h1 v-else>My name is: {{nameInput}}</h1>
    <b-form inline>
      <div class="welcomeInput">
        <b-input id="nameInput" type="text" v-model="nameInput"></b-input>
        <b-button v-on:click.prevent="sendName(nameInput)" class="send" :disabled="nameInput === ''">
          <b-icon-triangle-fill rotate="90" variant="success" font-scale="1.5">
          </b-icon-triangle-fill>
        </b-button>
      </div>
    </b-form>
  </div>
</template>

<script>
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

export default {
  name: "Welcome",
  data() {
    return {
      defaultText: "Tell us your name",
      nameInput: ""
    }
  },
  methods: {
    sendName: function (name) {
      this.$http.post('http://localhost:9000/posting', {
        title: `newUser`,
        username: name,
      }).then(function (data) {
        console.log(data)
        console.log(data.body)
      })
    }
  },
}
</script>

<style scoped>
.send {
  border: none;
  background: none;
}
.welcomeInput{
  margin: auto;
}
</style>