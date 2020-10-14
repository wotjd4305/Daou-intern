<template>
  <div>
  
    <div
      v-for="(item, idx) in recvList"
      :key="idx"
    >
      <strong class="text-left" v-if="isMyMessage(item.senderId)">나</strong>
      <strong v-if="!isMyMessage(item.senderId)">상대</strong>
      <div v-if="isMyMessage(item.senderId)" class="my_arrow_box mr-5 mt-2"> {{ item.content }} </div>
      <div v-else class="other_arrow_box ml-5 mt-2"> {{ item.content }} </div>
    </div>
    
  

    <!-- 입력 창 -->
    <div class="row ">
      <div style="width:110%" class="align-self-center">
        <input
          v-model="message"
          type="text"
          @keyup="sendMessage"
          placeholder="내용 입력하세요." 
        >
        <button class="ml-1 mt-2 btn search-button" @click="sendMessageBtn">
          전송
        </button>
      </div>
    </div>
    <!--/ 입력 창 -->

  </div>
</template>

<script>
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'

import Swal from 'sweetalert2'
import { mapActions, mapState } from 'vuex'

function ChatRes(chatroomId, currUserId, otherUserId, otherUserName, otherUserImage){

    this.chatroomId = chatroomId;// property
    this.currUserId = currUserId;  // property
    this.otherUserId = otherUserId; // property
    this.otherUserName = otherUserName;  // property
    this.otherUserImage = otherUserImage; // property
}
export default {
  name: 'Chat',
  data() {
    return {
      message: "",
      recvList: [],
      res : [],
      stompClient : "",
    }
  },
  created() {
    this.findMyAccount()
  },
  computed:{
        ...mapState(['myaccount']),
  },
  methods: {
    ...mapActions(['findMyAccount']),
    isMyMessage(msgId){
      return msgId == this.myaccount.userId;
    },
    sendMessage (e) {
      if(e.keyCode === 13 && this.message !== ''){
        this.send()
        this.message = ''
      }
    },
    sendMessageBtn () {
      if(this.message !== ''){
        this.send()
        this.message = ''
      }
    },   
    send() {
      console.log("Send message:" + this.message);
      if (this.stompClient && this.stompClient.connected) {
        var msg = { 
            chatroomId: this.res.chatroomId,
            senderId: this.res.currUserId,
            receiverId: this.res.otherUserId,
            content: this.message,
            sendTime : new Date(),
            receiveTime : null,
        };
        this.stompClient.send("/pub/message", JSON.stringify(msg));
      }
    },    
    connect(receivedRes) {
      const serverURL = "http://localhost:8080/ws"
      let socket = new SockJS(serverURL);
      
      //메시지 중복 제거, 중복연결..
      if(!this.stompClient){
        this.stompClient = Stomp.over(socket);
        console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`)
        this.stompClient.connect(
          {},
          frame => {
            // 소켓 연결 성공
            this.connected = true;
            console.log('소켓 연결 성공', frame);
            this.stompClient.subscribe("/sub/"+ receivedRes.chatroomId , res => {
              console.log('구독으로 받은 메시지 입니다.', res.body);
              this.recvList.push(JSON.parse(res.body))
            });
          },
          error => {
            // 소켓 연결 실패
            console.log('소켓 연결 실패', error);
            this.connected = false;
          }
        );    
      }    
    },
    child(req){
      this.res = new ChatRes(req.chatroomId, req.currUserId, req.otherUserId, req.otherUserName, req.otherUserImage);
      const Toast = Swal.mixin({
              toast: true,
              position: 'top-end',
              showConfirmButton: false,
              timer: 3000,
              timerProgressBar: false,
              onOpen: (toast) => {
                toast.addEventListener('mouseenter', Swal.stopTimer)
                toast.addEventListener('mouseleave', Swal.resumeTimer)
                }
             })
             Toast.fire({
              icon: 'success',
              title: req.otherUserName +"님과의 채팅이 시작되었습니다."
            })

        this.connect(this.res)
    },
  }
}
</script>

<style scoped>
  .search-button {
    background-color: #2682ba;
    font-weight: bold;
    color: #f8f8f8;
    height: 2rem;
    width: 3.5rem;
  }


.my_arrow_box {
	position: relative;
	background: #2682ba;
  border: 1px solid #c2e1f5;
  color:#ffffff;
  border-radius: 5px;

}
.my_arrow_box:after, .arrow_box:before {
	right: 100%;
	top: 50%;
	border: solid transparent;
	content: " ";
	height: 0;
  width: 0;
	position: absolute;
	pointer-events: none;
}

.my_arrow_box:after {
  border-color: rgba(38, 130, 186, 0);
	border-right-color: #2682ba;
	border-width: 5px;
	margin-top: -1px;
}
.my_arrow_box:before {
	border-color: rgba(194, 225, 245, 0);
  border-right-color: #dff0fe;
  
	border-width: 5px;
	margin-top: -1px;
}

.other_arrow_box {
	position: relative;
	background: #dff0fe;
  border: 1px solid #ffffff;
  color:#000000;
  border-radius: 5px;

}
.other_arrow_box:after, .arrow_box:before {
	left: 100%;
	top: 50%;
	border: solid transparent;
	content: " ";
	height: 0;
  width: 0;
	position: absolute;
	pointer-events: none;
}

.other_arrow_box:after {
  border-color: rgba(38, 130, 186, 0);
	border-left-color:#dff0fe;
	border-width: 5px;
	margin-top: -1px;
}
.other_arrow_box:before {
	border-color: rgba(194, 225, 245, 0);
  border-left-color:#ffffff;
  
	border-width: 5px;
	margin-top: -2px;
}
  

</style>