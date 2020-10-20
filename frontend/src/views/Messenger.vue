<template>
  <div>
    <chat
        :name="'Chat for All'"
        :author="user"
        ref="chat"
    ></chat>
  </div>
</template>

<script>
import Chat from "@/components/Chat";
export default {
  name: "Massager",
  components: {
    chat: Chat
  },
  data() {
    return {
      user: this.$route.params.user
    }
  }
}
</script>

<style scoped>

</style>