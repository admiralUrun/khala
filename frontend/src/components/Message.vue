<template>
  <div :id="'m-' + id">
      <b-row>
        <b-col sm="10">
          <b-row>
            {{author}}
          </b-row>
          <b-row>
            {{message}}
          </b-row>
        </b-col>

        <b-col sm="1">
          <b-row>
            !!!
          </b-row>
          <b-row>
            {{wasSent}}
          </b-row>
        </b-col>
      </b-row>
  </div>
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
    },
    delivered: {
      type: Boolean,
      required: true
    }
  }
}
</script>

<style scoped>

</style>