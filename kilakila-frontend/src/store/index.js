import { createStore } from "vuex";
import { getAdminInfo } from '../api/user'
import { getArticleCount } from '../api/article'


// 管理员
let adminAbout = {
	namespaced: true,
	state: {
		adminInfo: {
			nickName: "",
			signature: "",
			avatar: require("@/assets/image/avatar.jpg"),
			githubUrl: "https://github.com/zhiyiYo",
		},
		articleCountInfo: {
			article: 0,
			category: 0,
			tag: 0,
		}
	},
	actions: {
		getAdminInfo(context) {
			return new Promise((resolve) => {
				getAdminInfo().then(data => {
					context.commit('updateAdminInfo', data)
					resolve(data)
				})
			})
		},
		getArticleCount(context) {
			return new Promise((resolve) => {
				getArticleCount().then(data => {
					context.commit('updateArticleCountInfo', data)
					resolve(data)
				})
			})
		},
	},
	mutations: {
		updateAdminInfo(state, adminInfo) {
			delete adminInfo.avatar
			Object.assign(state.adminInfo, adminInfo)
		},
		updateArticleCountInfo(state, articleCountInfo) {
			Object.assign(state.articleCountInfo, articleCountInfo)
		},
	},
}


// 网站
let websiteAbout = {
	namespaced: true,
	state: {
		startDate: "2022-01-01"
	},
	actions: {},
	mutations: {},
}

export default createStore({
	modules: { adminAbout, websiteAbout },
});
