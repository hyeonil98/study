# HTTP의 동작원리

----
## HTTP
HTTP(Hypertext Transfer Protocol)는 클라이언트와 서버 간 통신을 위한 통신 규칙 세트 또는 프로토콜입니다. 
사용자가 웹 사이트를 방문하면 사용자 브라우저가 웹 서버에 HTTP 요청을 전송하고 웹 서버는 HTTP 응답으로 응답합니다. 웹 서버와 사용자 브라우저는 데이터를 일반 텍스트로 교환합니다. 간단히 말해 HTTP 프로토콜은 네트워크 통신을 작동하게 하는 기본 기술입니다. 이름에서 알 수 있듯이 HTTPS(Hypertext Transfer Protocol Secure)는 HTTP의 확장 버전 또는 더 안전한 버전입니다. HTTPS에서는 브라우저와 서버가 데이터를 전송하기 전에 안전하고 암호화된 연결을 설정합니다.

HTTP는 OSI 7계층 중에 네트워크 계층입니다, 따라서 여러 유형의 요청과 응답을 정의합니다.

### HTTP 요청 및 응답 플로우
사용자가 www.google.co.kr 사이트를 접속했을때를 가정해 봅시다.

사용자가 브라우저에 특정 URL을 요청합니다, 이때 URL 주소중 도메인 부분을 DNS에서 검색합니다, 
여기서 DNS란 www.google.co.kr 같이 사람의 입장에서 만든 URL을 129.123.52.1 같은 IP주소로 변환하는 서비스라고 간단히 정의할 수 있습니다,
이 같은 작업을 수행하는 서버를 DNS Server라고 칭합니다.

해당하는 IP 주소를 DNS Server에서 찾았다면 Client는 HTTP Request 행, HTTP Header, data를 포함한 Request를 전송 프로토콜을 거쳐 해당 IP로 송신합니다.
HTTP Header는 많은 필드로 구성되어 있습니다, 이 중 중요한 몇가지 필드만 보겠습니다.

1. General
요청과 응답에 모두 사용되는 필드
※ HTTP/1.1 부터는 General header로 구체적인 분류를 하지 않습니다.
```text
General Header
    Request URL: https://www.google.co.kr/
    Request Method: GET
    Status Code: 200 OK
    Remote Address: 172.217.25.163:443
    Referrer Policy: origin
```
* Request URL : 요청 주소
* Request Method : 요청 방식(POST, GET 등)
* Status Code : 응답 상태 코드
* Remote Address : 서비스 접근 주소
* Referrer Policy : 참조자 정책

2. Request Header
클라이언트에 대한 부가적인 정보 확인용 필드
```text
    authority: www.google.co.kr
    method: GET
    path: /
    scheme: https
    Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7
    Accept-Encoding: gzip, deflate, br, zstd
    Accept-Language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7
    Cache-Control: max-age=0
    Cookie: SID=g.a000kAj_ImbKqk1dlFTLf2aKtV9DDaeAzJb8duDzLXNxYOuVOKuoOj8bCRhUvQY_vfDimDnTTAACgYKAX8SARcSFQHGX2MijevKTm6iuyGPywvQOk5bsRoVAUF8yKqh9DCFwaKPX1uqQlQQO5B-0076; __Secure-1PSID=g.a000kAj_ImbKqk1dlFTLf2aKtV9DDaeAzJb8duDzLXNxYOuVOKuojCmk33QHGzPfEaKtnDg1tAACgYKAaISARcSFQHGX2Mi7RH22nf4s4WXIRozKnzDWhoVAUF8yKopnHPt7Nu9LiCCM2zv6eOG0076; __Secure-3PSID=g.a000kAj_ImbKqk1dlFTLf2aKtV9DDaeAzJb8duDzLXNxYOuVOKuoz9l6TS1cgUnLdh97-IRKuAACgYKAXkSARcSFQHGX2Mie-LxRYMG-h31SGQnJVB-kBoVAUF8yKqD4mR5LgPTG0rceO-iSvF30076; HSID=AsVnORaKXq4Rz02si; SSID=AWGa3M-RDj7sNBwhF; APISID=KR9vFHKCZ_31h9KJ/A-8K5K1acPxzWP85I; SAPISID=YbW--eaesCkwQDYK/AUDsK4F7EqYx-T5ZH; __Secure-1PAPISID=YbW--eaesCkwQDYK/AUDsK4F7EqYx-T5ZH; __Secure-3PAPISID=YbW--eaesCkwQDYK/AUDsK4F7EqYx-T5ZH; SEARCH_SAMESITE=CgQIopsB; receive-cookie-deprecation=1; AEC=AQTF6HwX87pkwnCB9X0UevePErU7_nZBpqvSKZLf7KRHogIQjHJr_KmvNhI; NID=514=qTo7WClqxTDUB-Hh9Fsc7fwMDOnzdi0Na1LW92CQsxGWr80bG7c0W75IsLEYA9ezBmtE4PRKIajnZCszXn26X7hN_wcoeeHeM782aD4iWIavJZ81cooFgT5_qfade51N6w2asJGuc1gEP0fu5RxEGt_6wAWgOViwkxGspp-j8WoGdsqftR5b7iC7m4q-4LMxsagktzl5r_i7UGQzIAJwo0vD4LG7bn4zxOtCdVw8ScXyLr6MoD6EDyGUuYP0t4mknxpTj5YHF-eh6rwq3lCyQiw3tGhI_ygC; 1P_JAR=2024-06-02-11; vidbh=1; svidbh=1
    Priority: u=0, i
    Sec-Ch-Ua: "Google Chrome";v="125", "Chromium";v="125", "Not.A/Brand";v="24"
    Sec-Ch-Ua-Arch: "x86"
    Sec-Ch-Ua-Bitness:"64"
```

* Connection : 연결 관리 모델 옵션 정보
* Sec-* : 요청 모드의 메타 데이터 정보

그 외의 Response Header 가 있습니다.

다시 넘어가 자세한 송신 과정을 살펴보면 다음과 같습니다.
![HTTP](https://github.com/TwoEther/study/assets/101616106/518a46f9-e923-4c10-ba2f-9cb5ee914ccd)

동작 방식
<TCP 소켓 오픈>

TCP 연결을 하는 과정에서 3-way handshake로 연결을 설정합니다, 만일 HTTPS 라면
TLS handshake 과정을 통해 세션키를 생성합니다

연결 종료

서버와의 세션이 종료되면 4-way handshake로 연결을 종료합니다.


