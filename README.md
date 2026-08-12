## CI / CD => DevOps (Developer + Operation)
 - 개발 + 운영

## CI -> 코드가 정상적으로 통합 
	=> 자동 검증
	=> git push (commit+push) / merge 시 통합
	=> 코드 체크 / 빌드 / 테스트 / 오류검증
	=> deploy.yml의  name, jenkins는 stage
	=> 정상적 수행 => 서버로 전송

## CD -> deplyment
	=> 서버에 배포
	=> 시점: CI가 완성된 경우
	=> jar(war) / docker 이미지
	=> 서버 재실행
	=> 자동화 

# ⚙️ 환경
+ Sts 3.9.18
+ Tomcat 9
+ Java 11
+ git Action

# ▶️ 실행 과정
1. self-hosted로 실행
2. 프로젝트를 main브런치로 push
3. 톰캣 중지
4. 톰캣 webapps에 해당 war파일이나 폴더가 있으면 삭제
5. 현재 push한 프로젝트의 war파일을 webapps로 이동
6. 톰캣 실행
   

# 📂 yml파일
```
name: Java CI with Maven
#이벤트 
on:
  push:
    branches: [ "main" ]

#CI / CD 작업
jobs:
  deploy:
    runs-on: self-hosted
  #모든 명령을 기본 작업 디렉토리 설정
    defaults:
      run: 
        working-directory: .  
    steps:
        - name: Checkout
          uses: actions/checkout@v4
    
        - name: Maven Build
          run: mvn clean package -DskipTests

        - name: Stop Tomcat
          run: |
              /home/sist/apache-tomcat-9.0.120/bin/shutdown.sh || true
              sleep 3
        - name: Remove Old war
          run: |
            rm -rf /home/sist/apache-tomcat-9.0.120/webapps/SpringLastProject.war
            rm -rf /home/sist/apache-tomcat-9.0.120/webapps/SpringLastProject
        - name: Deploy war
          run: |
            cp target/*.war /home/sist/apache-tomcat-9.0.120/webapps/SpringLastProject.war
        - name: Tomcat Start
          env:
            RUNNER_TRACKING_ID: ""
          run: |
             nohup /home/sist/apache-tomcat-9.0.120/bin/startup.sh
             sleep 5
             ps -ef | grep tomcat
```
