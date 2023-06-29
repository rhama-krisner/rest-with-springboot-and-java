package application.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {

    @GetMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(@PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new Exception("Os valores informados não são numéricos.");
        }

        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }
    private Double convertToDouble(String strNumber) {
        if (strNumber == null) {
            throw new IllegalArgumentException("A string de número é nula.");
        }

        String number = strNumber.replaceAll(",", ".");
        if (isNumeric(number)) {
            return Double.parseDouble(number);
        }

        throw new IllegalArgumentException("A string de número não é um valor numérico válido.");
    }

    private boolean isNumeric(String strNumber) {
        if (strNumber == null) {
            return false;
        }
        return strNumber.matches("-?\\d+(\\.\\d+)?");
    }

}
