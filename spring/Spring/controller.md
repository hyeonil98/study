# @Controller 와 @RestController의 차이

Spring에서 컨트롤러를 지정해주기 위한 Annotation 에는 @Controller 와 @RestController가 있습니다,
이 2개의 사용법에 대해서 알아보도록 하겠습니다.
----
## @Controller
@Controller는 Spring MVC 어플리케이션에서 view를 반환하기 위해 사용됩니다,
다음은 Spring MVC Container에서 View를 반환하는 과정입니다.


![controller1](https://github.com/TwoEther/study/assets/101616106/739058d2-9e9e-4794-aee7-5faa25a4c2b2)

1. Client는 URL 형식으로 서비스에 요청을 보냅니다.
2. DispatcherServlet이 요청을 받아 처리할 대상을 찾습니다.
3. HandlerAdapter가 요청을 Controller로 위임하고 Controller는 요청을 처리한 후 ViewName을 반환합니다.
4. DispatcherServlet은 ViewResolver를 통해 ViewName에 해당하는 View를 찾아 반환합니다.
5. 만일 위 단계에서 Data를 반환하는 경우 @ResponseBody를 사용하여 Json 형식의 데이터를 반환받을 수 있습니다.

## @RestController
@RestController는 @Controller에 @ResponseBody가 추가된 Annotation입니다, @ResponseBody가 사용되므로 당연하게 객체 데이터를 
반환합니다, 주로 REST API를 개발할 때 사용되며 객체를 ResponseEntity로 감싸 반환합니다.

![controller2](https://github.com/TwoEther/study/assets/101616106/a5eab257-0448-47df-bdc6-57c7fe905072)


