import Vue from "vue"
import moment from "moment"

const State= Object.freeze({ S: "판매중", I: "거래중", C: "거래완료" });


Vue.filter("comma", (value) => {
  if(value == 0) return "무료나눔♥"
  return String(value).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
});

Vue.filter("calculateTime", (value) => {
  var nowTime = moment();
  var writeTime = moment(value, 'YYYY-MM-DD HH:mm:ss');

  var diffHour = moment.duration(nowTime.diff(writeTime)).asHours();
  var diffDay = moment.duration(nowTime.diff(writeTime)).asDays();
  var diffMin = moment.duration(nowTime.diff(writeTime)).asMinutes();
  
  //60분 이내
  if(diffMin < 60){
      return parseInt(diffMin) + "분 전"
  }
  //24시간 이내
  if(diffHour < 24){
      return parseInt(diffHour) + "시간 전";
  }
  //7일 이내
  if(diffDay < 7){
      return parseInt(diffDay) + "일 전";
  }
  var printTime = new Date();
  return getFormatDate(printTime);
});


Vue.filter("itemStatus", (status) => {
  if(status == 'S')
    return State.S
  if(status == 'I')
    return State.I
  if(status == "C")
   return State.C
});



//날짜 계산
function getFormatDate(date){
  var year = date.getFullYear();              
  var month = (1 + date.getMonth());         
  month = month >= 10 ? month : '0' + month; 
  var day = date.getDate();                  
  day = day >= 10 ? day : '0' + day;          
  return  year + '년' + month + '월' + day + '일';       //'-' 추가하여 yyyy-mm-dd 형태 생성 가능
}
