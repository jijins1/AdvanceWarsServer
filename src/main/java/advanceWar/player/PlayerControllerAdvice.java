package advanceWar.player;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class PlayerControllerAdvice  {
	@ResponseBody
	@ExceptionHandler(PlayerNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	VndErrors userNotFoundExceptionHandler(PlayerNotFoundException ex) {
		return new VndErrors("error", ex.getMessage());
	}
	
	@ResponseBody
	@ExceptionHandler(PlayerAlreadyExistException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	VndErrors userAlreadyExistExceptionHandler(PlayerAlreadyExistException ex) {
		return new VndErrors("error", ex.getMessage());
	}


}
