import { createStore } from "vuex";
import { getAdminInfo } from '../api/user'
import { getArticleCount } from '../api/article'
import { getCategoryList } from '../api/category'
import { getTagList } from '../api/tag'


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


// 分类
let categoryAbout = {
	namespaced: true,
	state: {
		categories: []
	},
	actions: {
		getCategories(context) {
			return new Promise((resolve) => {
				getCategoryList().then(data => {
					context.commit('updateCategories', data)
					resolve()
				})
			})
		}
	},
	mutations: {
		updateCategories(state, data) {
			state.categories = data
		}
	}
}

// 标签
let tagAbout = {
	namespaced: true,
	state: {
		tags: []
	},
	actions: {
		getTags(context) {
			return new Promise((resolve) => {
				getTagList().then(data => {
					context.commit('updateTags', data)
					resolve()
				})
			})
		}
	},
	mutations: {
		updateTags(state, data) {
			state.tags = data
		}
	}
}

export default createStore({
	modules: { adminAbout, websiteAbout, categoryAbout, tagAbout },
});
