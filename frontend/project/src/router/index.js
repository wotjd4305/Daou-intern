import Vue from 'vue'
import VueRouter from 'vue-router'

//Accounts
import Login from '@/views/accounts/Login'
import Signup from '@/views/accounts/Signup'
import Userinfo from '@/views/accounts/Userinfo'

//Boards
import Home from '@/views/boards/Home'
import Board from '@/views/boards/Board'
import BoardDetail from '@/views/boards/BoardDetail'
import BoardWrite from '@/views/boards/BoardWrite'

//Chat Test
import Chat from '@/views/chat/Chat'



//PageNotFound
import PageNotFound from '@/views/PageNotFound'


Vue.use(VueRouter)

const routes = [

  //유저
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/signup',
    name: 'Signup',
    component: Signup
  },
  {
    path: '/userinfo',
    name: 'Userinfo',
    component: Userinfo
  },

  //게시판 홈
  {
    path: '/home',
    name: 'Home',
    component: Home
  },
  {
    path: '/board',
    name: 'Board',
    component: Board  
  },
  {
    path: '/board/detail/:itemId',
    name: 'BoardDetail',
    component: BoardDetail
  },
  {
    path: '/board/write',
    name: 'BoardWrite',
    component: BoardWrite, 
  }
  ,
  //Chat Test
  {
    path : '/chat',
    name : 'Chat',
    component: Chat
  },

  //PageNotFound
  {
    path: '*',
    name: 'PageNotFound',
    component: PageNotFound
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})


router.beforeEach((to, from, next) => {
  const publicPages = ['Login', 'Signup'] // 로그인 해야하는 페이지
  const authPages = ['Login', 'Signup']  // 로그인 안되어 있어야하는 페이지
  const authRequired = !publicPages.includes(to.name) // 로그인 필요하면 true
  const unauthRequired = authPages.includes(to.name)
  const isLoggedIn = Vue.$cookies.isKey('auth-token')

  if (unauthRequired && isLoggedIn){
    next('/home')
  }
  
  if (authRequired && !isLoggedIn) {
    next({ name: 'Login' })
  } else {
    next()
  }
})

export default router
