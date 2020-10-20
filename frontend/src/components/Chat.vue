<template>
  <b-container id="chat" fluid>
    <b-col>
      <b-row>
        <header>
          <h1>
            {{name}}
          </h1>
        </header>
      </b-row>
      <b-row class="placeForMessages" >
        <ul>
        <Message v-for="message in chatMessages" :key="message.id"
                 :id="message.id"
                 :author="message.author"
                 :message="message.message"
                 :delivered="message.delivered"
                 :was-sent="message.wasSent"
                 ></Message>
        </ul>
      </b-row>

      <b-row>
        <b-col xl="11">
          <b-textarea v-model="message"></b-textarea>
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
    <div>

    </div>
  </b-container>
</template>

<script>
import Message from "@/components/Message.vue";
export default {
  name: "Chat",
  props: {
    name: {
      type: String,
      required: true
    },
    messages : []
  },
  components: {
    'Message': Message
  },
  data() {
    return {
      message: '',
      connection: null,
      chatMessages: this.messages
    }
  },
  mounted() {
    this.connection = new WebSocket('ws://localhost:9000/startWebsocketConnection')

    this.connection.onopen = function (event) {
      console.log(event)
    }

    this.connection.onmessage = function (message) {
      console.log(message)
    }


  },
  methods:{
    sendMessage: function (message) {
      console.log(message)
      this.connection.send(message)
    },
    addToMessages: function (message) {
      this.chatMessages.push(message)
      console.log(this.chatMessages)
    }
  }
}
// TODO: Move .send to Global CSS or creat new component
</script>

<style scoped>
.send {
  border: none;
  background: none;
}
</style>