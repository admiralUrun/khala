<template>
  <b-container id="chat" fluid>
    <b-col>
      <b-row>
        <header>
          <h1>
            {{ name }}
          </h1>
        </header>
      </b-row>
        <div class="placeForMessages container-fluid">
            <Message v-for="message in getAllMessages" :key="message.id"
                     :id="message.id"
                     :author="message.author"
                     :message="message.message"
                     :was-sent="message.wasSent"
            ></Message>
        </div>
      <b-row>
        <b-col xl="11">
          <b-textarea id="" v-model="message"></b-textarea>
        </b-col>
        <b-col sm="1">
          <b-button v-on:click.prevent="sendMessage(message)" class="send" :disabled="message === ''">
            <b-icon-triangle-fill variant="primary" font-scale="1.5">
            </b-icon-triangle-fill>
          </b-button>
        </b-col>
      </b-row>
    </b-col>
    <b-col></b-col>
  </b-container>
</template>

<script>
import Message from "@/components/Message.vue";
import {mapGetters} from 'vuex'

export default {
  name: "Chat",
  props: {
    name: {
      type: String,
      required: true
    }
  },
  components: {
    'Message': Message
  },
  data() {
    return {
      message: '',
    }
  },
  computed: mapGetters(['getAllMessages']),
  methods: {
    sendMessage: function (m) {
      function timeFormat(time) {
        if(time < 10) return `0` + time
        else return time.toString()
      }
      const currentDate = new Date()
      const hours = currentDate.getHours()
      const min = currentDate.getMinutes()
      const time  = timeFormat(hours) + ':' + timeFormat(min)
      this.message = ''
      this.$socket.send(JSON.stringify({
            feat: this.$store.getters.getFeat,
            message: {
              id: null,
              author: this.$store.getters.getUsername,
              message: m,
              wasSent: time
            },
          }))
    }
  },
  // beforeMount() {
  //   console.log(this.$route.query.user)
  //   this.$store.commit('setUsername', this.$route.query.user)
  // }
}
// TODO: Move .send to Global CSS or creat new component
</script>

<style scoped>
.send {
  border: none;
  background: none;
}

.placeForMessages {
  overflow: scroll;
  min-height: 100px;
  max-height: 600px;
  margin-bottom: 5px;
}
</style>