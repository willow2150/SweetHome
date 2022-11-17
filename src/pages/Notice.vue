<template>
  <router-view></router-view>
</template>

<script>

export default {
name: "Notice",
created() {
  this.$router.push({ name: "noticelist" });  
},
};
</script>

<style></style>
