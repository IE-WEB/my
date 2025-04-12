<template>
  <div>
    <iframe
        :src="indexUrl"
        style="width: 100vw; height: 100vh; border: none;"
        ref="iframeRef"
        @load="onIframeLoad"
    ></iframe>
  </div>
</template>

<script setup>
import { ref } from 'vue';

// 假设 index.html 在 public 目录下，使用相对路径
const indexUrl = ref('/HTML/index.html');
const iframeRef = ref(null);

// 处理 iframe 加载完成的事件，可用于调整样式等操作
const onIframeLoad = () => {
  try {
    // 这里可以添加加载完成后的逻辑，比如获取 iframe 内容等
    const iframeContent = iframeRef.value.contentWindow.document;
    // 例如可以修改 iframe 内元素的样式
    const elements = iframeContent.querySelectorAll('h1');
    elements.forEach((element) => {
      element.style.color = 'blue';
    });
  } catch (error) {
    console.error('访问 iframe 内容时出错，可能是跨域问题:', error);
  }
}
</script>