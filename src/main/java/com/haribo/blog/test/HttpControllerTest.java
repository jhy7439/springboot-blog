package com.haribo.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


// 사용자가 요청 -> 응답(HTML 파일)
// @Controller

// 사용자가 요청 -> 응답(Data)
@RestController
public class HttpControllerTest {

	private static final String TAG = "HttpControllerTest: ";
	
	// http://localhost:8000/blog/http/lombok
	@GetMapping("/http/lombok")
	public String lombokTest() {
		//Member m1 = new Member(1, "ssar", "1234", "email");
		Member m = Member.builder().username("ssar").password("1234").email("ssar@email.com").build();
		System.out.println(TAG+"getter : "+ m.getUsername());
		m.setUsername("Haribo");
		System.out.println(TAG+"setter : "+ m.getUsername());
		return "lombok test 완료";
	}
	
	// 인터넷 브라우저 요청은  get요청만 가능하다
	// http://localhost:8080/http/get (select)
	@GetMapping("/http/get")
	public String getTest(Member m) {//id=1&username=ssar&password=1234&emali=ssar@nate.com // MessageConverter (스프링부트)
		return "get 요청 : "+m.getId()+", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail();
	}

	// http://localhost:8080/http/post (insert)
	@PostMapping("/http/post") // text/plain, application/json
	public String postTest(@RequestBody Member m) { // MessageConverter (스프링부트)
		return "post 요청 : "+m.getId()+", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail();
		//return "post 요청 : "+text;
	}
	
	// http://localhost:8080/http/put (update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		
		return "put 요청 :"+m.getId()+", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail();          
	}
	
	// http://localhost:8080/http/delete (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		
		return "delete 요청";
	}
}
