<template>
  <router-view></router-view>
</template>

<script>

export default {
name: "Board",
created() {
  this.$router.push({ name: "boardlist" });  
},
};
</script>

<style></style>
