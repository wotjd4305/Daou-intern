import SERVER from '@/api/api'

export default{
    install(Vue){

        //이메일 정규식
        Vue.prototype.$emailValidation = function(data){
            var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
            if(regExp.test(data)){
                //통과
                return true;
            }else{
                //올바르지 않음
                return false;
            }
        }


        //유저 이미지 가져오기, url로
        Vue.prototype.$commonUserImage = function(data){
            if(data == null)
              return SERVER.IMAGE_STORE + "icons8-male-user-90.png";
            return SERVER.IMAGE_STORE + data;
          }

        // //아이템 이미지 가져오기, url로
        // Vue.prototype.$commonUserImage = function(data){
        //     if(data == null)
        //       return SERVER.IMAGE_STORE + "icons8-male-user-90.png";
        //     return SERVER.IMAGE_STORE + data;
        // }
          

    }
}