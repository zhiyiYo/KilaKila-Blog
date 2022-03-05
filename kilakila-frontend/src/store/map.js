import { computed } from 'vue'
import { useStore } from 'vuex'

/**
 * 获取 state 中指定命名空间的数据
 * @param {string} namespace 命名空间
 * @returns stateObj
 */
const mapState = (namespace) => {
    const store = useStore()
    let obj = namespace ? store.state[namespace] : store.state
    return Object.fromEntries(
        Object.keys(obj).map(k => [k, computed(() => obj[k])])
    )
}

/**
 * 获取 getter 中指定命名空间的数据
 * @param {string} namespace 命名空间
 * @returns getterObj
 */
const mapGetters = (namespace) => {
    const store = useStore()
    return Object.fromEntries(
        Object.keys(store.getters).map(
            g => [g, computed(() => store.getters[namespace ? f`${namespace}/${g}` : g])]
        )
    )
}

/**
 * 获取 mutation 中指定命名空间的方法
 * @param {string} namespace 命名空间
 * @returns mutationObj
 */
const mapMutations = (namespace) => {
    const store = useStore()
    return Object.fromEntries(
        Object.keys(store._mutations).map(
            m => [m, value => store.commit(namespace ? f`${namespace}/${m}` : m, value)]
        )
    )
}

/**
 * 获取 mutation 中指定命名空间的方法
 * @param {string} namespace 命名空间
 * @returns actionObj
 */
const mapActions = () => {
    const store = useStore()
    return Object.fromEntries(
        Object.keys(store._actions).map(
            a => [a, value => store.dispatch(namespace ? f`${namespace}/${a}` : a, value)]
        )
    )
}

export { mapState, mapGetters, mapMutations, mapActions }