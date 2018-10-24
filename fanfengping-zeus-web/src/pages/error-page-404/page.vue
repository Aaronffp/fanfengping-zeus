<template>
  <div class="page">
    <p class="page_title">It looks like I'm lost, can you take me home ?</p>
    <el-button class="d2-mt" @click="$router.replace({ path: '/' })">
      Go Home
    </el-button>
  </div>
</template>

<style lang="scss" scoped>
@import '~@/assets/style/public.scss';
.page {
  background: linear-gradient(to bottom, rgba(255,255,255,0.15) 0%, rgba(0,0,0,0.15) 100%), radial-gradient(at top center, rgba(255,255,255,0.40) 0%, rgba(0,0,0,0.40) 120%) #989898;
  background-blend-mode: multiply,multiply;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  .page_title {
    font-size: 20px;
    color: #FFF;
  }
}
</style>
