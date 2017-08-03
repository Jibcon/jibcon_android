network 통신에 필요한 클래스 모음입니다

더 생각나시면 코멘트 부탁드려요!//의준//4.8
DeviceInfo
-DeviceAddr : 디바이스의 IP주소..?
-DeviceOnOffState : 디바이스의 On/Off state
-Devicetype : ex) 디바이스의 종류/ 선풍기 에어컨 전구 등등
                  res파일에 디바이스별 아이콘 저장 -> 디바이스 타입에 따라 맞는 아이콘 선택

HouseInfo
-HouseType : 컨테이너 자취방 원룸 등등
-HouseLocation : 집콘 만들기 할 때 생성되는 주소
-HouseName : 집콘 이름
-HouseUsers : UserInfo의 ArrayList 한 공간의 사용자 그룹


UserInfo :
-UserToken : 로그인시 생성되는 토큰 저장
-UserImgProfile : 유저 프로필 이미지
-UserEmailProfile : 유저 이메일
