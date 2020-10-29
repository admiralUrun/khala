<template>
      <b-row :id="'m-' + id" class="message">
        <b-col lg="11" class="messageContent">
          <b-row>
            {{author}}
          </b-row>
          <b-row>
            {{message}}
          </b-row>
        </b-col>
        <div class="align-text-bottom">
            {{wasSent}}
        </div>
      </b-row>
</template>

<script>
export default {
  name: "Message",
  props: {
    id:{
      type: Number,
      required: true
    },
    author:{
      type: String,
      required: true
    },
    message: {
      type: String,
      required: true
    },
    wasSent: {
      type: String,
      required: true
    }
  }
}
</script>

<style scoped>
.message {
  max-width: 1000px;
  color: azure;
  background-color: #1c5cab;
  border: 1px solid #151826;
  border-radius: 10px;
  margin-bottom: 5px;
}

.messageContent {
  margin-left: 2px;
}

</style>