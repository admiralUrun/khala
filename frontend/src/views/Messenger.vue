<template>
  <div>
    <chat
        :name="'Chat for All'"
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
  }
}
</script>

<style scoped>

</style>