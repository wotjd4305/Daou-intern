import SERVER from '@/api/api'


const StateReverse= Object.freeze({ onSale: "S", Sailing : "I", Completed: "C" });

export default{
    install(Vue){

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

        //역 필터
        Vue.prototype.$itemStatusReverse = function(value){
            if(value == "판매중")
              return StateReverse.onSale
            if(value == "거래중")
              return StateReverse.Sailing
            if(value == "거래완료")
             return StateReverse.Completed
          };
          

    }
}