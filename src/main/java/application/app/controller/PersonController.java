package application.app.controller;

import application.app.converters.NumberConverter;
import application.app.math.SimpleMath;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

import static application.app.converters.NumberConverter.convertToDouble;
import static application.app.converters.NumberConverter.isNumeric;

@RestController
public class PersonController {

    private final AtomicLong counter = new AtomicLong();
    @GetMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(@PathVariable("numberOne") String numberOne,
                      @PathVariable("numberTwo") String numberTwo) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new Exception("Os valores informados não são numéricos.");
        }

        return math.sum(NumberConverter.convertToDouble(numberOne),
                NumberConverter.convertToDouble(numberTwo));
    }
}
