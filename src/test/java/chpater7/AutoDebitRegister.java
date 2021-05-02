package chpater7;

import java.time.LocalDate;

//public class AutoDebitRegister {
//    private CardNumberValidator validator;
//    private AutoDebitInfoRepository repository;
//
//    public AutoDebitRegister(CardNumberValidator validator, AutoDebitInfoRepository repository) {
//        this.validator = validator;
//        this.repository = repository;
//    }
//
//    public RegisterResult registerResult(AutoDebitReq req){
//        CardValidity validity = validator.validate(req.getCardNumber());
//        if (validity != CardValidity.VALID) {
//            return RegisterResult.error(validity);
//        }
//        AutoDebitInfo info = repository.findOne(req.getuserId());
//        if (info != null) {
//            info.changeCardNumber(req.getCardNumber());
//        } else {
//            AutoDebitInfo newInfo = new AutoDebitInfo(req.getUserId(), req.getCardNumber(), LocalDate.now());
//            repository.save(newInfo);
//        }
//
//        return RegisterResult.success();
//    }
//}
