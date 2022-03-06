<template>
    <hr />
</template>

<script>
export default {
    name: "KilaKilaDivider",
};
</script>

<style scoped>
hr {
    position: relative;
    margin: 10px 0;
    border: 2px dashed #bfe4fb;
    width: 100%;
    box-sizing: content-box;
    height: 0;
    overflow: visible;
    box-sizing: border-box;
}

hr::before {
    position: absolute;
    top: -11px;
    left: 5%;
    z-index: 1;
    color: #bfe4fb;
    content: "✂";
    font-size: 21px;
    line-height: 1;
    -webkit-transition: all 1s ease-in-out;
    -moz-transition: all 1s ease-in-out;
    -o-transition: all 1s ease-in-out;
    -ms-transition: all 1s ease-in-out;
    transition: all 1s ease-in-out;
}

hr:hover::before {
    left: calc(95% - 20px);
}
</style>