<template>
    <router-view />
</template>

<style>
::-webkit-scrollbar {
    height: 14px;
    width: 4px;
}
::-webkit-scrollbar-thumb {
    background-color: rgba(153, 153, 153, 0.8);
    border-radius: 10px;
}
::-webkit-scrollbar-track {
    border-radius: 10px;
}

body {
    overflow: overlay;
    background-color: #f3f6f7;
}

:root {
    --theme-color: #1892ff;
}
</style>