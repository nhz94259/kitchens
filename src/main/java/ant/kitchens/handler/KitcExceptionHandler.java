package ant.kitchens.handler;

import ant.kitchens.exception.KitcException;
import ant.kitchens.utils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wolf   2018/10/28
 */
@ControllerAdvice
public class KitcExceptionHandler {

    //通用异常处理
    @ExceptionHandler(value = KitcException.class)
    @ResponseBody
    public R handlerSellerException(KitcException e) {
        return R.error(e.getCode(), e.getMessage());
    }
}
