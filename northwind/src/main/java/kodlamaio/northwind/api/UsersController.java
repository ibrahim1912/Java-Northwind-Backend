package kodlamaio.northwind.api;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.northwind.business.abstracts.UserService;
import kodlamaio.northwind.core.entities.User;
import kodlamaio.northwind.core.utilities.results.ErrorDataResult;
import org.springframework.validation.FieldError;

@RestController
@RequestMapping(value = "/api/users")
public class UsersController {

	private UserService userService;

	@Autowired
	public UsersController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping(value = "/add")
	public ResponseEntity<?> add(@Valid @RequestBody User user) {
		return ResponseEntity.ok(this.userService.add(user));
	}
	
	//c# bu type of diye yapılır
	//validation hataları
	//bütün metotlar burdan geççcek
	//bu sistemde şu türde bir hata olursa bu metodu devreye sok
	//anatasyonla yapılcak aop
	//ezberlenmesi gereken kod değil bir kere yazılır mantıgı bil
	//spring boot validation dökümanında var
	@ExceptionHandler(MethodArgumentNotValidException.class)   //sistemde şu türden bir hata olursa MethodArgumentNotValidException
	@ResponseStatus(HttpStatus.BAD_REQUEST)                          //bad request olarak sarmalla
	public ErrorDataResult<Object> handleValidationException
	(MethodArgumentNotValidException exceptions){
		Map<String, String> validationErrors = new HashMap<String, String>();   // hash map yani dictionary oluştur
		for(FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {  //bütün hataları gez o dictionary i ekle 
			validationErrors.put(fieldError.getField(),fieldError.getDefaultMessage());
		}
		
		ErrorDataResult<Object> errors                                            //errordataresult ın içine koy
		= new ErrorDataResult<Object>(validationErrors,"Doğrulama hataları");
		return errors;                                                            //ve bu hataları döndür
	}
}

//bttün primitive classların base object tir
//aop hata yönetimi
//metotların üstüne global exception handler yazlır
//her metota ayrı ayrı try catch uygulamaktansa 1 tane yazılır onun içine alınır