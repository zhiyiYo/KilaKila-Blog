import './assets/css/index.css'
import 'element-plus/es/components/message/style/css'
import 'element-plus/es/components/message-box/style/css'
import "mavon-editor/dist/css/index.css";

import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import VueMavonEditor from "mavon-editor";
import FontAwesomeIcon from "./utils/fontawesome-icons";

const app = createApp(App);
app.use(store)
    .use(router)
    .use(VueMavonEditor)
    .component("font-awesome-icon", FontAwesomeIcon)
    .mount("#app");
