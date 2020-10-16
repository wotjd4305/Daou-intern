# project

## Project setup

```
npm install
```

### Compiles and hot-reloads for development

```
npm run serve
```

### Install more library

실행이 안될 시, Console에 찍히는 필요한 라이브러리 설치.

```
npm install 'library name'
```


### Customize configuration

See [Configuration Reference](https://cli.vuejs.org/config/).



## 배포 순서

### <u>**build 파일로 배포하기**</u>

[1] npm install ...

[2] npm run build
 -> dist 파일 생성 확인

[3] dist 파일 압축

[4] sftp로 로컬서버에 전달 후 압축 풀기



### **<u>Git clone으로 배포하기</u>**

[1] sudo apt-get install git

[2] sudo apt-get npm

[3] sudo git clone [ 주소 ]

[4] sudo npm run serve | sudo npm run build(nginx로 build 파일 연동 시 선택)  
  
[배포 참고 자료](https://jay-ji.tistory.com/57)


