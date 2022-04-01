![ball_avoid_game](https://user-images.githubusercontent.com/92193144/160819770-ee82c46a-489c-44f3-bd1f-fb0a7fd29c69.gif)
# 공피하기 게임 프로젝트 소개 #
# 개발환경 #
  - windows10
  - JAVA
##
# 게임 설명 #
 - 플레이어를 방향키로 이동이 가능하다.
 - 플레이어의 체력이 0이 되면 게임 Thread 가 멈추고 다시시작 문구가 표시된다.
 - 화면 상단에 점수와 플레이어 체력을 실시간으로 확인이 가능하다.
 - SCORE 의 점수가 특정 조건에 만족하면 게임의 난이도가 올라간다.
    - 공의 스피드 증가
    - 공의 갯수 증가
 - 최종 목표 : 최대한 오랫동안 공을 피해서 높은 SCORE 을 얻는다.
---
# 공피하기 게임을 만든 이유 #
- 게임을 만드는 과정을 통해 다양한 기능을 구현할 수 있었다.
- 쓰레드 기법을 이용하여 프로그램을 구현해 보고 싶었다.
- 여러개의 클래스 파일을 생성하고 각 클래스 파일에 대한 역할을 정하면서 코드를 작성하는 과정을 통해 JAVA 언어에 대해 이해가 많이 되었다.
# 구현하면서 어려웠던 점 #
- 플레이어 이미지와 공의 이미지에 대한 충돌판정을 처음 구현해보는거라 많이 어려웠다.
- 구글링 및 다른 개발자의 GIT 소스를 최대한 활용하여 최대한 학습하여 구현하였다.
# 추가하고 싶은 기능들 #
- 게임이 끈난 후 닉네임을 입력하고 높은 점수순으로 기록하는 기능
- 공의 방향을 한쪽이 아닌 다른곳에서도 나오도록 추가
- 화면 중간 중간 체력을 채워주는 아이템 추가
- 게임 프레임을 종료시키게되면 램에서 프로그램이 내려가기 때문에 점수가 저장되지 않는다. DB랑 연동하여 SCORE 를 저장하는 기능을 추가
---
# 미니프로젝트 계산기 #
![ezgif com-gif-maker](https://user-images.githubusercontent.com/92193144/161262493-e0fb831e-205f-4bcd-b35b-918cd6f13de9.gif)
##
# 계산기 만들면서 느낀점 #
- Arraylist 를 이용해서 패널에 버튼을 생성하는 과정을 좀더 확실히 익힐 수 있었다.
- 구현해야할 과정들이 생각보다 많았다.
- 문자열을 활용해서 숫자로 바꾸고 연산하는 과정을 구현하는곳에서 많이 어려웠다.
- 배열과 arrayList 를 사용하면서 더확실하게 이해할 수 있었다.
# 에러 #
- 다음 에러는 숫자버튼 누르지 않고 바로 연산기호를 누르고 = 버튼을 클릭했을시 나타나는 에러이다.
- 무엇때문인지 원인을 계속 알수가 없었는데 연산값이 없는데 다음 코드에서 원인이 생겼다는걸 발견하였다.
![연산기호 에러](https://user-images.githubusercontent.com/92193144/161264903-ff2fffe1-5f12-4086-99d1-daf023d25ca0.png)
### 코드 수정 전 ###
<pre>
	public double Calculate(String inputText) {
		
		SymbolConver(inputText);
		// 사용자가 입력한 숫자 및 연산자들을 가져옴 (문자열)
		
		double pre = 0.0;
		double current = 0;
		String mod = "";
		String error = "0";
		
		for (String s : Num_Str) {
			if(s.contentEquals("+")) {
				mod = "add";
			}else if(s.contentEquals("-")) {
				mod = "sub";
			}else if(s.contentEquals("×")) {
				mod = "mul";
			}else if(s.contentEquals("÷")) {
				mod = "div";
			}else {
				###기호를 누르고 = 버튼을 클릭하게 되면 current 안에 값이 없기때문에 위 에러가 발생
					 current = Double.parseDouble(s); 
				 if(mod == "add") {
						System.out.println(pre);
						System.out.println(current);
						pre += current;
						System.out.println(pre);
					}else if(mod == "sub") {
						pre -= current;
					}else if(mod == "mul") {
						pre *= current;
					}else if(mod == "div") {
							pre /= current;	
					}else {
						pre = current;
					}
					
			}
		
			
		}		
		

			return pre;	
		

	}
</pre>
### 코드 수정 후 ###
<pre>


  	public double Calculate(String inputText) {
		
		SymbolConver(inputText);
		// 사용자가 입력한 숫자 및 연산자들을 가져옴 (문자열)
		
		double pre = 0.0;
		double current = 0;
		String mod = "";
		String error = "0";
		
		for (String s : Num_Str) {
			if(s.contentEquals("+")) {
				mod = "add";
			}else if(s.contentEquals("-")) {
				mod = "sub";
			}else if(s.contentEquals("×")) {
				mod = "mul";
			}else if(s.contentEquals("÷")) {
				mod = "div";
			}else {
				if(!s.isEmpty()) { // 조건식을 추가해서 에러해결
					 current = Double.parseDouble(s);
				}
	
				 if(mod == "add") {
						System.out.println(pre);
						System.out.println(current);
						pre += current;
						System.out.println(pre);
					}else if(mod == "sub") {
						pre -= current;
					}else if(mod == "mul") {
						pre *= current;
					}else if(mod == "div") {
							pre /= current;	
					}else {
						pre = current;
					}
					
			}
		
			
		}		
		

			return pre;	
		

	}
	
</pre>
  


# 개선해야될 사항 #
- 아무값이 없는 상태에서 <- 클릭하게 되면 에러발생 어떻게 처리해야 에러가 발생하지 않을지 좀더 알아봐야할거같다. 해결못함.
- 초기값을 0으로 나오게 한상태에서 사용자가 값을 입력하면 숫자만 바뀌게 하고싶었지만 아직 어떻게 해야할지 감이 안잡힌다.
- 연산기호를 여러개 클릭하지 못하고 하나만 클릭할수 있도록 수정해야한다. 이부분도 아직 어떻게 해야할지 잘 모르겠다. 좀더 다른코드를 보면서 공부해야할거 같다.
- 최대입력숫자를 제한시키면 좋을거 같다.









