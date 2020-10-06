export default {
    URL: 'http://localhost:8080/api',
    IMAGE_STORE:'http://localhost:8080/images/',
    ROUTES: {
    
      // accounts
      myaccount: '/user/token',
      signup: '/user',
      login: '/user/login',
      checkreduplication: '/user',
      updateuser: '/user/edit',
      uploaduserimageA: '/user',
      uploaduserimageB: '/image',
      deleteuserimageA:'/user',
      deleteuserimageB:'/image',

      // categorys
      itemcategory:'/category/item',
      departmentcategory:'/category/department',

      //item
      postitem:'/item',
      getallitem:'/item',
      getdetailitem:'/item',
      deletedetailitem:'/item',
      updateitemstatus:'/item/:itemId/status',
      updateitem:'/item/info',
    }
}