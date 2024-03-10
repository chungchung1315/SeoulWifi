## IntelliJ에서 실행해보기

1. 이 저장소를 로컬로 clone
    
    ```java
    git clone https://github.com/chungchung1315/SeoulWifi.git
    ```
    
2. Lombok 어노테이션 활성화
    - IntelliJ Preferences - Build, Execution, Deployment - Compiler - Enable annotation processing
3. Tomcat 실행환경 설정
    - Configuration - Add new - Tomcat Server (Local)
    - Server탭 - Tomcat Server 8.5
    - Deployment탭 - +(Add) - Artifact - SeoulPublicWifi:war - OK
    - Run → 웹 브라우저가 실행된 후, 첫 화면이 보여야 함

## 파일설명

- src/main/webapp
    
    와이파이 정보
    
    - index.jsp - 처음 시작 화면. wifi 목록을 보여줌
    - detail.jsp - wifi 목록 중 하나를 클릭했을 때, 상세 내역을 보여줌
    - load-wifi.jsp - wifi 목록을 서울공공데이터 API를 사용해 불러옴
    - history.jsp - 조회한 이력을 보여줌
    
    북마크 그룹
    
    - bookmark-group.jsp - 북마크 그룹 목록을 보여줌
    - bookmark-group-add.jsp - 북마크 그룹 추가 페이지
    - bookmark-group-edit.jsp - 북마크 그룹 수정 페이지
    - bookmark-group-delete.jsp - 북마크 그룹 제거 페이지
    
    북마크
    
    - bookmark-list.jsp - 북마크 목록 페이지
    - bookmark-add-submit.jsp - 북마크 추가를 처리하는 페이지
    - bookmark-delete.jsp - 북마크 삭제 확인 페이지
    - bookmark-delete-submit.jsp - 북마크 삭제를 처리하는 페이지
    
- src/main/webapp/css
    - style.css - 표 및 문서 스타일 정의
    - loader.css - 로딩 표시 스타일 정의
    
- src/main/webapp/js
    - fetch_location_script.js - 서울공공데이터 API로부터 데이터를 불러오는 javascript 코드
    
- src/main/webapp/comp
    - header.jsp - html, head 태그의 내용을 담은 파일
    - footer.jsp - html 을 닫는 태그의 내용을 담은 파일
    - menu.jsp - 메뉴 네비게이션 부분을 담은 파일