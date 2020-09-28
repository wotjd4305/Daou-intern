<template>
  <div>
  
     유저이름: 
        <input
          v-model="userName"
          type="text"
        >

    <div
      v-for="(item, idx) in recvList"
      :key="idx"
    >
      <strong>{{ item.userName }}</strong> :
      <div class="my_arrow_box mr-5 mt-2"> {{ item.content }} </div>
      <div class="other_arrow_box ml-5 mt-2"> {{ item.content }} </div>
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

export default {
  name: 'Chat',
  data() {
    return {
      userName: "",
      message: "",
      recvList: []
    }
  },
  created() {
    // App.vue가 생성되면 소켓 연결을 시도합니다.
    this.connect()
  },
  methods: {
    sendMessage (e) {
      if(e.keyCode === 13 && this.userName !== '' && this.message !== ''){
        this.send()
        this.message = ''
      }
    },
    sendMessageBtn () {
      if(this.userName !== '' && this.message !== ''){
        this.send()
        this.message = ''
      }
    },   
    send() {
      console.log("Send message:" + this.message);
      if (this.stompClient && this.stompClient.connected) {
        const msg = { 
          userName: this.userName,
          content: this.message 
        };
        this.stompClient.send("/receive", JSON.stringify(msg), {});
      }
    },    
    connect() {
      const serverURL = "http://localhost:8080/api"
      let socket = new SockJS(serverURL);
      this.stompClient = Stomp.over(socket);
      console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`)
      this.stompClient.connect(
        {},
        frame => {
          // 소켓 연결 성공
          this.connected = true;
          console.log('소켓 연결 성공', frame);
          // 서버의 메시지 전송 endpoint를 구독합니다.
          // 이런형태를 pub sub 구조라고 합니다.
          this.stompClient.subscribe("/send", res => {
            console.log('구독으로 받은 메시지 입니다.', res.body);

            // 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
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