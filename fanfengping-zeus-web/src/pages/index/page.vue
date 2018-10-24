<template>
  <d2-container class="page">
    <d2-page-cover
      title="I AM ZEUSADMIN"
      sub-title="追求简约美感的后台管理系统集成方案">
      <d2-icon-svg style="width: 120px;" name="d2-admin"/>
      <template slot="footer">
        <div class="page__btn-group">
          <span @click="$open('https://github.com/Aaronffp/fanfengping-zeus.git')">GitHub</span> |
          <span @click="$open('https://github.com/Aaronffp/fanfengping-zeus.git')">使用文档</span> |
          <span @click="$open('http://www.fanfengping.com/')">个人网站</span> |
          <span @click="$open('http://www.cnblogs.com/fengpingfan/')">个人博客</span> |
          <el-popover
            :width="172"
            trigger="hover">
            <p class="d2-mt-0 d2-mb-10">ZEUSProjects</p>
            <img src="./image/qr@2x.png" style="width: 172px;">
            <span slot="reference">微信公众号</span>
            <p
              style="
                font-size: 12px;
                margin-top: 0px;
                margin-bottom: 0px;
              ">
              官方公众号，主要推送前端技术类文章、框架资源、学习教程，以及 D2 系列项目更新信息
            </p>
          </el-popover>
        </div>
      </template>
    </d2-page-cover>
  </d2-container>
</template>

<script>
// import D2HelpBtn from './components/d2-help-btn'
// export default {
//   components: {
//     D2HelpBtn
//   }
// }
</script>

<style lang="scss" scoped>
@import '~@/assets/style/public.scss';
.page {
  .page__btn-group {
    color: $color-text-placehoder;
    font-size: 12px;
    span {
      color: $color-text-sub;
      &:hover {
        color: $color-text-main;
      }
    }
  }
}
</style>
