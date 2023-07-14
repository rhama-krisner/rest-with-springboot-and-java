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
public class MathController {

    private final AtomicLong counter = new AtomicLong();
    private SimpleMath math = new SimpleMath();


    @GetMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(@PathVariable("numberOne") String numberOne,
                      @PathVariable("numberTwo") String numberTwo) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new Exception("Os valores informados não são numéricos.");
        }

        return math.sum(NumberConverter.convertToDouble(numberOne),
                NumberConverter.convertToDouble(numberTwo));
    }

    @GetMapping("/sub/{numberOne}/{numberTwo}")
    public Double sub(@PathVariable("numberOne") String numberOne,
                      @PathVariable("numberTwo") String numberTwo) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new Exception("Os valores informados não são numéricos.");
        }

        return math.sub(NumberConverter.convertToDouble(numberOne),
                NumberConverter.convertToDouble(numberTwo));

    }

    @GetMapping("/mult/{numberOne}/{numberTwo}")
    public Double mult(@PathVariable("numberOne") String numberOne,
                       @PathVariable("numberTwo") String numberTwo) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new Exception("Os valores informados não são numéricos.");
        }

        return math.mult(NumberConverter.convertToDouble(numberOne),
                NumberConverter.convertToDouble(numberTwo));

    }

    @GetMapping("/div/{numberOne}/{numberTwo}")
    public Double div(@PathVariable("numberOne") String numberOne,
                      @PathVariable("numberTwo") String numberTwo) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new Exception("Os valores informados não são numéricos.");
        }

        return math.div(NumberConverter.convertToDouble(numberOne),
                NumberConverter.convertToDouble(numberTwo));

    }

    @GetMapping("/med/{numberOne}/{numberTwo}")
    public Double med(@PathVariable("numberOne") String numberOne,
                      @PathVariable("numberTwo") String numberTwo) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new Exception("Os valores informados não são numéricos.");
        }

        return math.med(NumberConverter.convertToDouble(numberOne),
                NumberConverter.convertToDouble(numberTwo));

    }

    @GetMapping("/pow/{numberOne}/{numberTwo}")
    public Double pow(@PathVariable("numberOne") String numberOne,
                      @PathVariable("numberTwo") String numberTwo) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new Exception("Os valores informados não são numéricos.");
        }

        return math.pow(NumberConverter.convertToDouble(numberOne),
                NumberConverter.convertToDouble(numberTwo));

    }

    @GetMapping("/sqrt/{numberOne}")
    public Double sqrt(@PathVariable("numberOne") String numberOne) throws Exception {
        if (!isNumeric(numberOne)) {
            throw new Exception("Os valores informados não são numéricos.");
        }

        return math.sqrt(NumberConverter.convertToDouble(numberOne));

    }
}
