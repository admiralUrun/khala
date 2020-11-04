import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

export const store  = new Vuex.Store({
    state: {
        username: '',
        messages: [],
        feat: 0,
        limit: 50
    },
    mutations: {
        setUsername(state, username) {
            state.username = username
        },
        SOCKET_ONMESSAGE(state, data)  {
            const feat = data.feat
            const messages = data.messages
            if(state.messages.length + messages.length >= 50) {
                state.messages = state.messages.slice(messages.length)
            }
            state.feat = feat
            state.messages = state.messages.concat(messages)
        },
        SOCKET_ONOPEN (state, event)  {
            console.log('Opened', event)
        },
        SOCKET_ONCLOSE (state, event)  {
            console.log('Closed', event)
        }
    },
    actions: {
        changeMessages({ commit }, message) {
            commit('changeMessages', message)
        }
    },
    getters: {
        getUsername: state => state.username,
        getAllMessages: state => state.messages,
        getFeat: state => state.feat
    }
})