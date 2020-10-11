import SERVER from '@/api/api'


const StateReverse= Object.freeze({ 판매중: "S", 거래중: "I", 거래완료: "C" });

export default{
    install(Vue){

        //이메일 정규식
        Vue.prototype.$favoriteItem = function  (data){
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
          
        //좋아요 이미지
        Vue.prototype.$favoriteImage = function(favorite){
            if(favorite == true)
              return SERVER.IMAGE_STORE +  "icons8-heart-48.png";
            return SERVER.IMAGE_STORE +  "icons8-noheart-48.png";
          }

        
        //
        Vue.prototype.$itemStatusReverse = function(value){
            if(value == "판매중")
              return StateReverse.판매중
            if(value == "거래중")
              return StateReverse.거래중
            if(value == "거래완료")
             return StateReverse.거래완료
          };
          

    }
}