package Utils;


import com.hospital.hospital.Models.Response;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)

    public ResponseEntity handleException(Exception ex, HttpServletResponse response) {
        Response response1 = Response.builder().success(false).status(0).message(ex.getMessage()).build();
        int estatus = response.getStatus();
        return new ResponseEntity<>(response1, HttpStatusCode.valueOf(estatus));


    }

}